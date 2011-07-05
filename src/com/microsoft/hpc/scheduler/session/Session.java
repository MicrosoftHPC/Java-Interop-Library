//------------------------------------------------------------------------------
// <copyright file="Session.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      The implementation of the Session Class
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
import java.util.List;

import com.microsoft.hpc.sessionlauncher.SessionInfo;

/**
 * The jobs associated with this object will be automatically closed on
 * disposing this object
 * 
 */
public class Session extends SessionBase
{
    /**
     * Whether the jobs will be closed on disposing this session
     */
    private Boolean autoCloseJob;

    /**
     * Initializes a new instance of the Session class
     * 
     * @param info
     *            session info
     * @param headnode
     *            headnode name
     * @param autoClose
     *            if jobs will be closed on disposing
     * @param traceSource
     *            trace source
     */
    Session(SessionInfo info, String headnode, Boolean autoClose) {
        super(info, headnode);
        this.autoCloseJob = !autoClose;
    }

    /**
     * Gets a value indicating whether the job will be automatically closed on
     * disposal of this session
     * 
     * @return the autoCloseJob
     */
    public Boolean getAutoCloseJob() {
        return autoCloseJob;
    }

    /**
     * Sets a value indicating whether the job will be automatically closed on
     * disposal of this session
     * 
     * @param autoCloseJob
     *            the autoCloseJob to set
     * @deprecated Please close the session explict
     */
    public void setAutoCloseJob(Boolean autoCloseJob) {
        this.autoCloseJob = autoCloseJob;
    }

    /**
     * Gets the Net.Tcp endpoint reference for load balancer of this session
     * 
     * @return endpoint address
     * @deprecated Use EndpointReference instead.
     */
    public String netTcpEndpointReference() {
        if (this.getInfo().getTransportScheme().get(0) != "NetTcp") {
            return null;
        } else {
            return generateEndpointAddress(this.getInfo().getBrokerEpr()
                    .getValue().getString(), TransportScheme.NetTCP);
        }
    }

    /**
     * Gets the Http endpoint reference for load balancer of this session
     * 
     * @return endpoint address
     * @depreciated Use EndpointReference instead
     */
    public String httpEndpointReference() {
        if (this.getInfo().getTransportScheme().get(0) != "Http") {
            return null;
        } else {
            return generateEndpointAddress(this.getInfo().getBrokerEpr()
                    .getValue().getString(), TransportScheme.Http);
        }
    }

    /**
     * Synchronous mode of submitting a job and get a ServiceJobSession object
     * 
     * @param startInfo
     *            the session start info for creating the service session
     * @return A service job session object, including the endpoint address and
     *         the two jobs related to this session
     * @throws SessionException
     *             if an exception occurred during session creation
     * @throws TimeoutException
     *             if a timeout occurred during creation
     */
    public static Session createSession(SessionStartInfo startInfo)
            throws SocketTimeoutException, SessionException {
        return createSession(startInfo, 0);
    }

    /**
     * Synchronous mode of submitting a job and get a ServiceJobSession object.
     * If a session object hasn't been returned within a timeout, the method
     * will throw a timeout exception
     * 
     * @param startInfo
     *            the session start info for creating the service session
     * @param timeout
     *            the timeout in milliseconds
     * @return A service job session object, including the endpoint address and
     *         the two jobs related to this session.
     * @throws SessionException
     *             if an exception occurred during session creation
     * @throws TimeoutException
     *             if a timeout occurred during creation
     */
    public static Session createSession(SessionStartInfo startInfo,
            int timeoutMilliseconds) throws SocketTimeoutException,
            SessionException {
        return (Session) createSessionInternal(startInfo, timeoutMilliseconds,
                false);
    }

    /**
     * Attach to an existing session with the session id
     * 
     * @param attachInfo
     *            the attach info
     * @return A persistent session
     * @throws SessionException
     *             if an exception was thrown during attach
     */
    public static Session attachSession(SessionAttachInfo attachInfo)
            throws SessionException, SocketTimeoutException {
        return attachSession(attachInfo, 0);
    }

    /**
     * Attach to an existing session with session id
     * 
     * @param attachInfo
     *            the attach info
     * @param timeoutMilliseconds
     *            timeout
     * @return a persistent session
     * @throws SessionException
     *             if an exception was thrown during attach
     */
    public static Session attachSession(SessionAttachInfo attachInfo,
            int timeoutMilliseconds) throws SessionException,
            SocketTimeoutException {
        return (Session) attachInternal(attachInfo, timeoutMilliseconds);
    }

    /**
     * Dispose the service job if autoclose is true
     */
    public void dispose() {
        if (autoCloseJob) {
            try {
                this._brokerclient.close(this.getId());
            } catch (Exception e) {
            } finally {
            }
        }

        super.dispose();
    }

    /**
     * Generate endpoint address by scheme from epr list
     * 
     * @param eprList
     *            indicating the epr list
     * @param scheme
     *            indicating the scheme
     * @return endpoint address
     */
    private static String generateEndpointAddress(List<String> eprList,
            TransportScheme scheme) {
        int index = scheme.getIndex();
        return eprList.get(index);
    }

}
