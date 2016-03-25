//------------------------------------------------------------------------------
// <copyright file="CxfBrokerLauncherClient.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      CxfBrokerLauncherClient class
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

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import com.microsoft.hpc.brokerlauncher.BrokerInitializationResult;
import com.microsoft.hpc.brokerlauncher.IBrokerLauncher;
import com.microsoft.hpc.brokerlauncher.IBrokerLauncherAttachSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokerlauncher.IBrokerLauncherCloseSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokerlauncher.IBrokerLauncherCreateDurableSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokerlauncher.IBrokerLauncherCreateSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokerlauncher.IBrokerLauncherPingBrokerSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.SessionInfo;

/**
 * @author junsu
 * 
 */
class CxfBrokerLauncherClient extends CxfClientBase
{
    IBrokerLauncher brokerLauncher;
    String headnode;
    String epr;

    public CxfBrokerLauncherClient(String username, String password,
            String EPRAddr) {
        this.epr = EPRAddr;

        QName portName = new QName("http://hpc.microsoft.com",
                "HpcBrokerClientHttpsPort");
        javax.xml.ws.Service soaService = Service
                .create(new QName("http://hpc.microsoft.com/brokerlauncher/", "BrokerLauncher"));
        soaService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, this.epr);
        brokerLauncher = soaService.getPort(portName, IBrokerLauncher.class);

        Initialize(username, password, brokerLauncher);
    }

    /**
     * Class to build SessionInfo
     * 
     */
    private static SessionInfo BuildSessionInfo(
            BrokerInitializationResult result, Boolean durable, Boolean secure,
            int id, String brokerLauncherEpr, TransportScheme scheme) {
        com.microsoft.hpc.sessionlauncher.ObjectFactory of = new com.microsoft.hpc.sessionlauncher.ObjectFactory();
        SessionInfo info = new SessionInfo();
        info.setId(id);
        info.setDurable(durable);
        info.setBrokerEpr(result.getBrokerEpr());
        info.setBrokerLauncherEpr(of
                .createSessionInfoBrokerLauncherEpr(brokerLauncherEpr));
        info.setControllerEpr(result.getControllerEpr());
        info.setResponseEpr(result.getResponseEpr());
        info.setSecure(secure);
        info.getTransportScheme().add(scheme.getName());
        return info;
    }

    public SessionInfo create(SessionStartInfo startInfo, int sessionid)
            throws SessionException {
        try {
            BrokerInitializationResult result = this.brokerLauncher.create(
                    startInfo.GetContractInfo(), sessionid);
            return BuildSessionInfo(
                    result,
                    false,
                    startInfo.isSecure(),
                    sessionid,
                    this.epr,
                    TransportScheme.valueOf(startInfo.getTransportScheme().get(
                            0)));
        } catch (IBrokerLauncherCreateSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public SessionInfo createDurable(SessionStartInfo startInfo, int sessionid)
            throws SessionException {
        try {
            BrokerInitializationResult result = this.brokerLauncher
                    .createDurable(startInfo.GetContractInfo(), sessionid);
            return BuildSessionInfo(
                    result,
                    true,
                    startInfo.isSecure(),
                    sessionid,
                    this.epr,
                    TransportScheme.valueOf(startInfo.getTransportScheme().get(
                            0)));
        } catch (IBrokerLauncherCreateDurableSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public void attach(SessionInfo info) throws SessionException {
        try {
            BrokerInitializationResult brokerLauncherInfo = this.brokerLauncher
                    .attach(info.getId());
            info.setBrokerEpr(brokerLauncherInfo.getBrokerEpr());
            info.setControllerEpr(brokerLauncherInfo.getControllerEpr());
            info.setResponseEpr(brokerLauncherInfo.getResponseEpr());
        } catch (IBrokerLauncherAttachSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public void close(int id) throws SessionException {
        try {
            this.brokerLauncher.close(id);
        } catch (IBrokerLauncherCloseSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public boolean pingBroker(Integer id) {
        try {
            this.brokerLauncher.pingBroker(id);
            return true;
        } catch (IBrokerLauncherPingBrokerSessionFaultFaultFaultMessage e) {
            return false;
        }
    }
}
