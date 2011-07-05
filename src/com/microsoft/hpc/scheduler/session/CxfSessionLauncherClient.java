//------------------------------------------------------------------------------
// <copyright file="CxfSessionLauncherClient.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Implement CxfSessionLauncherClient class.
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

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.ws.security.SecurityConstants;

import com.microsoft.hpc.sessionlauncher.ISessionLauncher;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherAllocateDurableSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherAllocateSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherGetInfoSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherGetSOAConfigurationSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherGetServerVersionSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherGetServiceVersionsSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherTerminateSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.SessionInfo;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;

/**
 * @author junsu
 * 
 */
final class CxfSessionLauncherClient extends CxfClientBase
{
    ISessionLauncher sessionLauncher;
    String headnode;

    public CxfSessionLauncherClient(SessionAttachInfo info) {
        this(info.getHeadnode(), info.getUsername(), info.getPassword());
    }

    public CxfSessionLauncherClient(SessionStartInfo info) {
        this(info.getHeadnode(), info.getUsername(), info.getPassword());
    }

    public CxfSessionLauncherClient(String headnode, String username,
            String password) {
        this.headnode = headnode;

        String sessionLauncherAddr = "https://" + this.headnode
                + "/SessionLauncher";

        QName portName = new QName("http://hpc.microsoft.com", "httpPort");
        javax.xml.ws.Service soaService = Service
                .create(new QName("http://hpc.microsoft.com/sessionlauncher/", "SessionLauncher"));
        soaService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
                sessionLauncherAddr);
        sessionLauncher = soaService.getPort(portName, ISessionLauncher.class);

        Initialize(username, password, sessionLauncher);

    }

    /**
     * Allocate a durable session resource
     * 
     * @param id
     *            return the sessionid
     * @param version
     *            return the service version
     * @return return the EPR list for the broker endpoints
     * @throws SessionException
     */
    public List<String> allocateDurable(SessionStartInfo info,
            Holder<Integer> id, Holder<String> version) throws SessionException {
        try {
            Holder<ArrayOfstring> eprList = new Holder<ArrayOfstring>();

            sessionLauncher.allocateDurable(info.GetContractInfo(), "https://",
                    eprList, id, version);

            if(eprList.value != null) {
                return eprList.value.getString();
            }
            else {
                return null;
            }
        } catch (ISessionLauncherAllocateDurableSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    /**
     * Allocate an interactive session resource
     * 
     * @param sessionid
     *            return the session id
     * @param serviceVersion
     *            return the service version
     * @return the EPR list of the broker endpoints
     * @throws SessionException
     */
    public List<String> allocate(SessionStartInfo info, Holder<Integer> id,
            Holder<String> version) throws SessionException {
        try {
            Holder<ArrayOfstring> eprList = new Holder<ArrayOfstring>();

            sessionLauncher.allocate(info.GetContractInfo(), "https://",
                    eprList, id, version);

            if(eprList.value != null) {
                return eprList.value.getString();
            }
            else {
                return null;
            }
        } catch (ISessionLauncherAllocateSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    /**
     * terminate the session
     * 
     * @param sessionid
     *            The session id
     * @throws SessionException
     */
    public void terminate(int sessionid) throws SessionException {
        try {
            sessionLauncher.terminate(headnode, sessionid);
        } catch (ISessionLauncherTerminateSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }

    }

    /**
     * Get the session information
     * 
     * @param id
     * @return
     * @throws SessionException
     */
    public SessionInfo getInfo(int sessionId) throws SessionException {
        try {
            SessionInfo info = sessionLauncher.getInfo(headnode, "https://",
                    sessionId);

            return info;
        } catch (ISessionLauncherGetInfoSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }

    }

    /**
     * Update the username and password when user change the password when the
     * password is invalid in last try
     * 
     * @param username
     * @param password
     */
    public void UpdateCredential(String username, String password) {
        this.username = username;
        this.password = password;

        java.util.Map<String, Object> requestContext = ((javax.xml.ws.BindingProvider) serviceObj)
                .getRequestContext();

        requestContext.put(SecurityConstants.USERNAME, this.username);
        requestContext.put(SecurityConstants.PASSWORD, this.password);
    }

    public Version[] getServiceVersions(String serviceName)
            throws SessionException {
        try {
            Version[] ret;
            org.datacontract.schemas._2004._07.system.ArrayOfVersion versions = sessionLauncher
                    .getServiceVersions(serviceName);
            List<org.datacontract.schemas._2004._07.system.Version> vs = versions
                    .getVersion();
            ret = new Version[vs.size()];
            int i = 0;
            for (org.datacontract.schemas._2004._07.system.Version v : vs) {
                ret[i] = new Version(v.getMajor(), v.getMinor(), v.getRevision(), v.getBuild());
                i++;
            }
            return ret;
        } catch (ISessionLauncherGetServiceVersionsSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public String getSOAConfiguration(String key) throws SessionException {
        try {
            return sessionLauncher.getSOAConfiguration(key);
        } catch (ISessionLauncherGetSOAConfigurationSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public Version getServerVersion() throws SessionException {
        try {
            org.datacontract.schemas._2004._07.system.Version s = sessionLauncher
                    .getServerVersion();
            return new Version(s.getMajor(), s.getMinor(), s.getBuild(), s.getRevision());
        } catch (ISessionLauncherGetServerVersionSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }
}
