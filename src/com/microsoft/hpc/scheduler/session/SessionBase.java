//------------------------------------------------------------------------------
// <copyright file="SessionBase.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      The implementation of the SessionBase Class
// </summary>
//------------------------------------------------------------------------------
/*
JAVA INTEROP LIBRARY FOR WINDOWS HPC SERVER

Copyright (c) Microsoft Corporation.  All rights reserved.

This license governs use of the accompanying software. If you use the
software, you accept this license. If you do not accept the license, do not
use the software.

1. Definitions
The terms "reproduce," "reproduction," "derivative works," and "distribution"
have the same meaning here as under U.S. copyright law.
A "contribution. is the original software, or any additions or changes to
the software.
A "contributor. is any person that distributes its contribution under this
license.
"Licensed patents. are a contributor.s patent claims that read directly on
its contribution.

2. Grant of Rights
(A) Copyright Grant- Subject to the terms of this license, including the
license conditions and limitations in section 3, each contributor grants you
a non-exclusive, worldwide, royalty-free copyright license to reproduce its
contribution, prepare derivative works of its contribution, and distribute
its contribution or any derivative works that you create.
(B) Patent Grant- Subject to the terms of this license, including the license
conditions and limitations in section 3, each contributor grants you a
non-exclusive, worldwide, royalty-free license under its licensed patents to
make, have made, use, sell, offer for sale, import, and/or otherwise dispose
of its contribution in the software or derivative works of the contribution
in the software.

3. Conditions and Limitations
(A) No Trademark License- This license does not grant you rights to use any
contributors' name, logo, or trademarks.
(B) If you bring a patent claim against any contributor over patents that
you claim are infringed by the software, your patent license from such
contributor to the software ends automatically.
(C) If you distribute any portion of the software, you must retain all
copyright, patent, trademark, and attribution notices that are present in
the software.
(D) If you distribute any portion of the software in source code form,
you may do so only under this license by including a complete copy of this
license with your distribution. If you distribute any portion of the software
in compiled or object code form, you may only do so under a license that
complies with this license.
(E) The software is licensed "as-is." You bear the risk of using it. The
contributors give no express warranties, guarantees or conditions. You may
have additional consumer rights under your local laws which this license
cannot change. To the extent permitted under your local laws, the contributors
exclude the implied warranties of merchantability, fitness for a particular
purpose and non-infringement.
(F) Platform Limitation- The licenses granted in sections 2(A) & 2(B) extend
only to the software or derivative works that you create that operate with
Windows HPC Server.
*/
package com.microsoft.hpc.scheduler.session;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

import com.microsoft.hpc.exceptions.SOAFaultCode;
import com.microsoft.hpc.sessionlauncher.SessionInfo;

/**
 * The common part for the session
 * 
 */
public class SessionBase
{
    public static Version NoServiceVersion = new Version(0, 0);

    /**
     * the client version
     */
    private static Version clientVersion = new Version(3, 2);

    /*
     * the Magic Infinite date
     */
    private static Date Infinite = new Date();

    /**
     * the endpoint address
     */
    private String _endpointReference;

    /**
     * The schema for broker
     */
    @SuppressWarnings("unused")
    private final TransportScheme _schema;

    /**
     * the scheduler headnode name
     */
    private final String _headnode;

    private final SessionInfo _info;

    private final int serviceJobId;

    protected CxfBrokerLauncherClient _brokerclient;

    String _username;

    String _password;

    // It can't be constructed outside
    SessionBase(SessionInfo info, String headnode) {
        serviceJobId = info.getId();
        _schema = TransportScheme.valueOf(info.getTransportScheme().get(0));

        for (String uri : info.getBrokerEpr().getValue().getString()) {
            if (uri != null && uri != "") {
                _endpointReference = uri;
                // this will automatically get the priority order
                break;
            }
        }

        this._info = info;
        this._headnode = headnode;
    }

    SessionInfo getInfo() {
        return _info;
    }

    // public ISchedulerJob ServiceJob
    // Java clients are unable to connect to the scheduler

    public int getId() {
        return serviceJobId;
    }

    /**
     * Get the default endpoint reference for the load balancer of this session
     * 
     * @return endpoint address
     */
    public String getEndpointReference() {
        return _endpointReference;
    }

    /**
     * the client version
     * 
     * @return client version number
     */
    public Version getClientVersion() {
        return clientVersion;
    }

    public Version getServerVersion() {
        SessionAttachInfo attachInfo = new SessionAttachInfo(this._headnode, 0,
                this._username, this._password);
        CxfSessionLauncherClient client = new CxfSessionLauncherClient(
                attachInfo);

        try {
            return client.getServerVersion();
        } catch (SessionException e) {
            return null;
        }
    }

    /**
     * the help function to check the sanity of SessionStartInfo before creating
     * session
     * 
     * @param startInfo
     */
    protected static void checkSessionStartInfo(SessionStartInfo startInfo) {
        Utility.throwIfNull(startInfo, "startInfo");
        Utility.throwIfNull(startInfo.getHeadnode(), "startInfo.headnode");

        startInfo.Validate();
    }

    static <T extends SessionBase> SessionBase createSessionInternal(
            SessionStartInfo startInfo, int timeoutMilliseconds, Boolean durable)
            throws SocketTimeoutException, SessionException {
        Date targetTimeout;

        SessionBase.checkCredential(startInfo);

        SessionBase.checkSessionStartInfo(startInfo);
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        if (timeoutMilliseconds != 0)
            targetTimeout = new Date(System.currentTimeMillis()
                    + timeoutMilliseconds);
        else
            targetTimeout = Infinite;

        if (!startInfo.getTransportScheme().contains(
                TransportScheme.Http.getName())) {
            throw new SessionException("Transport scheme not implemented: "
                    + TransportScheme.Http.getName());
        }

        if (NoServiceVersion.equals(startInfo.getServiceVersion())) {
            startInfo.setServiceVersion(null);
        }

        CxfSessionLauncherClient client = new CxfSessionLauncherClient(
                startInfo);

        CxfBrokerLauncherClient brokerLauncher = null;
        Exception innerException = null;
        Version serviceVersion = null;
        client.setTimeout(getTimeout(targetTimeout));

        try {
            int sessionid = 0;
            List<String> eprs = null;

            try {
                SessionBase.checkCredential(startInfo);
                client.UpdateCredential(startInfo.getUsername(),
                        startInfo.getPassword());
                String serviceVersionStr;

                Holder<Integer> id = new Holder<Integer>();
                Holder<String> version = new Holder<String>();
                if (durable) {
                    eprs = client.allocateDurable(startInfo, id, version);
                } else {
                    eprs = client.allocate(startInfo, id, version);
                }
                serviceVersionStr = version.value;
                sessionid = id.value;
                if (serviceVersionStr != null && serviceVersionStr.length() > 0) {
                    serviceVersion = new Version(serviceVersionStr);
                }
            } catch (SessionException ex) {
                if (ex.getErrorCode() == SOAFaultCode.AuthenticationFailure
                        .getCode()) {
                    throw new SecurityException();
                } else
                    throw ex;
            } catch (WebServiceException we) {
                if (we.getCause().getClass() == SocketTimeoutException.class) {
                    throw new SocketTimeoutException(SR.v(
                            "ConnectSessionLauncherTimeout",
                            timeoutMilliseconds));
                } else {
                    throw new SessionException(we);
                }
            } catch (Exception e) {
                throw new SessionException(e);
            }

            if(!startInfo.isUseSessionPool()) {
                if (eprs == null || eprs.size() == 0) {
                    throw new SessionException(SR.v("NoBrokerNodeFound"));
                }
            }
            else {
                if(eprs == null) {
                    SessionAttachInfo attachInfo = new SessionAttachInfo(startInfo.getHeadnode(), sessionid);
                    return attachInternal(attachInfo, timeoutMilliseconds);
                }
            }

            for (String epr : eprs) {
                brokerLauncher = new CxfBrokerLauncherClient(
                        startInfo.getUsername(), startInfo.getPassword(), epr);

                brokerLauncher.setTimeout(getTimeout(targetTimeout));

                try {
                    SessionInfo info;

                    startInfo.setServiceVersion(serviceVersion);
                    if (!durable) {
                        info = brokerLauncher.create(startInfo, sessionid);
                    } else {
                        info = brokerLauncher.createDurable(startInfo,
                                sessionid);
                    }

                    SessionBase session;
                    if (!durable) {
                        session = new Session(info, startInfo.getHeadnode(),
                                startInfo.isShareSession());
                    } else {
                        session = new DurableSession(info,
                                startInfo.getHeadnode());
                    }

                    // fill up the data
                    session._brokerclient = brokerLauncher;
                    session._password = startInfo.getPassword();
                    session._username = startInfo.getUsername();
                    return session;

                } catch (WebServiceException we) {
                    if (we.getCause().getClass() == SocketTimeoutException.class) {
                        throw new SocketTimeoutException(SR.v(
                                "ConectBrokerLauncherTimeout",
                                timeoutMilliseconds));
                    } else {
                        innerException = we;
                    }
                } catch (Exception e) {
                    innerException = e;
                }
            }

            // if the innerException is not null, then the related job should be
            // submited, then need terminate the job here.
            if (innerException != null) {
                try {
                    client.terminate(sessionid);
                } catch (Exception e) {
                    // if terminate the session failed, do nothing
                }
            }
        } finally {
            ;// SafeCloseCommunicateObject...
        }

        throw new SessionException(SR.v("NoBrokerNodeAnswered"), innerException);

    }

    // protected virtual void Dispose(Boolean disposing)
    // no scheduler to dispose
    public void dispose() {
        if (this._brokerclient != null) {
            this._brokerclient.destory();
            this._brokerclient = null;
        }
    }

    protected void finalize() {
        dispose();
    }

    protected void checkDispose() {
        if (this._brokerclient == null)
            throw new IllegalStateException();
    }

    /**
     * Close the session Java Changes: Resource will be released by GC
     * 
     * @throws Exception
     */
    public void close() throws SessionException, SocketTimeoutException {
        close(true);
    }

    /**
     * Close the session
     * 
     * @param purge
     *            if true, will remove the data in the server
     * @throws Exception
     */
    public void close(Boolean purge) throws SessionException,
            SocketTimeoutException {
        close(purge, 0);
    }

    /**
     * Close the session and release the resource
     * 
     * @param purge
     *            if true, we will remove the data from the server
     * @param timeoutMilliseconds
     *            timeout for the purge operation
     * @throws SessionException
     *             , TimeoutException
     */
    public void close(boolean purge, int timeoutMilliseconds)
            throws SessionException, SocketTimeoutException {
        checkDispose();

        if (timeoutMilliseconds < 0)
            throw new IllegalArgumentException(
                    "timeoutMilliseconds is invalid.");

        try {
            if (purge) {
                this._brokerclient.setTimeout(timeoutMilliseconds);
                this._brokerclient.close(getId());
            }
        } catch (WebServiceException we) {
            if (we.getCause().getClass() == SocketTimeoutException.class) {
                throw new SocketTimeoutException(SR.v(
                        "ConnectSessionLauncherTimeout", timeoutMilliseconds));
            } else {
                throw new SessionException(we);
            }
        } catch (Exception ex) {
            throw new SessionException(ex);
        }
    }

    // static internal void SaveCredential(SOATraceSource traceSource,
    // SessionStartInfo info)
    // don't really need to save credentials in the cache

    static <T extends SessionBase> SessionBase attachInternal(
            final SessionAttachInfo attachInfo, long timeoutMilliseconds)
            throws SessionException, SocketTimeoutException {
        Date targetTimeout;

        Utility.throwIfNull(attachInfo, "attachInfo");
        if (attachInfo.getHeadnode() == null || attachInfo.getHeadnode() == "") {
            throw new IllegalArgumentException(SR.v("HeadnodeCantBeNull"));
        }

        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(
                    "timeoutMilliseconds is invalid.");
        }

        if (timeoutMilliseconds == 0) {
            targetTimeout = Infinite;
        } else {
            targetTimeout = new Date(System.currentTimeMillis()
                    + timeoutMilliseconds);
        }

        CxfSessionLauncherClient client = new CxfSessionLauncherClient(
                attachInfo);
        SessionInfo info = null;

        client.setTimeout(getTimeout(targetTimeout));
        try {
            info = client.getInfo(attachInfo.getId());
        } catch (WebServiceException we) {
            if (we.getCause().getClass() == SocketTimeoutException.class) {
                throw new SocketTimeoutException(SR.v(
                        "ConnectSessionLauncherTimeout", timeoutMilliseconds));
            } else {
                throw new SessionException(we);
            }
        } catch (Exception e) {
            throw new SessionException(e);
        }

        if (info.getBrokerLauncherEpr() == null
                || info.getBrokerLauncherEpr().getValue() == "") {
            for (String state : info.getJobState()) {
                if (state.equalsIgnoreCase("Configuring")
                        || state.equalsIgnoreCase("ExternalValidation")
                        || state.equalsIgnoreCase("Queued")
                        || state.equalsIgnoreCase("Running")
                        || state.equalsIgnoreCase("Submitted")
                        || state.equalsIgnoreCase("Validating")) {
                    throw new SessionException(String.format(
                            SR.v("AttachConfiguringSession"),
                            attachInfo.getId()));
                } else {
                    throw new SessionException(String.format(
                            SR.v("AttachNoBrokerSession"), attachInfo.getId()));
                }
            }
        }

        CxfBrokerLauncherClient broker = new CxfBrokerLauncherClient(
                attachInfo.getUsername(), attachInfo.getPassword(), info
                        .getBrokerLauncherEpr().getValue());
        try {
            broker.setTimeout(getTimeout(targetTimeout));
            try {
                broker.attach(info);
            } catch (WebServiceException we) {
                if (we.getCause().getClass() == SocketTimeoutException.class) {
                    throw new SocketTimeoutException(SR.v(
                            "ConectBrokerLauncherTimeout", timeoutMilliseconds));
                } else {
                    throw new SessionException(we);
                }
            } catch (Exception e) {
                throw new SessionException(e);
            }
        } finally {
            ;// safeclose...
        }

        SessionBase session;
        if (!info.isDurable()) {
            session = new Session(info, attachInfo.getHeadnode(), false);
        } else {
            session = new DurableSession(info, attachInfo.getHeadnode());
        }

        // fill up the data
        session._brokerclient = broker;
        session._password = attachInfo.getPassword();
        session._username = attachInfo.getUsername();
        return session;
    }

    /**
     * Get the time span from the targeted time
     * 
     * @param targetTimeout
     * @return
     * @throws TimeoutException
     */
    static int getTimeout(Date targetTimeout) throws SocketTimeoutException {
        if (targetTimeout == Infinite)
            return 0;

        Date currentTime = new Date();
        if (targetTimeout.after(currentTime)) {
            return (int) (targetTimeout.getTime() - currentTime.getTime());
        } else {
            throw new SocketTimeoutException();
        }
    }

    /**
     * Verifies if a username is specified. (This function acts differently than
     * .Net version because there is no way to verify credentials in java)
     * 
     * @param info
     */
    static void checkCredential(SessionStartInfo info) {
        if (info.getUsername() == null || info.getPassword() == null
                || info.getUsername() == "") {
            throw new IllegalArgumentException("Must specify a username");
        }
    }

    /**
     * Class to return the versions for a specific service
     * 
     */
    public static Version[] GetServiceVersions(String headNode,
            String serviceName, String username, String password)
            throws SessionException {
        if (serviceName == null || serviceName.isEmpty())
            throw new IllegalArgumentException(SR.v("ServiceNameCantBeNull"));

        SessionAttachInfo attachInfo = new SessionAttachInfo(headNode, 0,
                username, password);
        CxfSessionLauncherClient client = new CxfSessionLauncherClient(
                attachInfo);
        return client.getServiceVersions(serviceName);
    }
}
