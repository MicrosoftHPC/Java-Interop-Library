//------------------------------------------------------------------------------
// <copyright file="CxfBrokerControllerClient.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      CxfBrokerControllerClient class
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

import java.util.Collection;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.soap.AddressingFeature;
import javax.xml.ws.soap.SOAPBinding;

import com.microsoft.hpc.BrokerClientStatus;
import com.microsoft.hpc.brokercontroller.BrokerResponseMessages;
import com.microsoft.hpc.brokercontroller.GetResponsePosition;
import com.microsoft.hpc.brokercontroller.IBrokerController;
import com.microsoft.hpc.brokercontroller.IBrokerControllerEndRequestsSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokercontroller.IBrokerControllerFlushSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokercontroller.IBrokerControllerGetBrokerClientStatusSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokercontroller.IBrokerControllerGetRequestsCountSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokercontroller.IBrokerControllerPullResponsesSessionFaultFaultFaultMessage;
import com.microsoft.hpc.brokercontroller.IBrokerControllerPurgeSessionFaultFaultFaultMessage;

class CxfBrokerControllerClient extends CxfClientBase
{
    IBrokerController brokerController;
    String epr;

    /**
     * Create the broker controller instance
     * 
     * @param username
     *            user name if this is a secure session
     * @param password
     *            password if this is a secure session
     * @param EPRAddr
     *            brokerController epr
     */
    public CxfBrokerControllerClient(String username, String password,
            String EPRAddr) {
        this.epr = EPRAddr.toLowerCase();

        QName portName = new QName("http://hpc.microsoft.com",
                "HpcBrokerControllerClientHttpsPort");
        
        javax.xml.ws.Service soaService = javax.xml.ws.Service
            .create(new QName("http://hpc.microsoft.com/brokercontroller/", "BrokerController"),
                    new AddressingFeature(true));
        soaService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, this.epr);

        brokerController = soaService.getPort(portName, IBrokerController.class);
        Initialize(username, password, brokerController);
    }

    public void endRequests(int sendCount, String clientId, int largerTimeout,
            int timeoutMilliseconds) throws SessionException {
        try {
            this.brokerController.endRequests(sendCount, clientId,
                    largerTimeout, timeoutMilliseconds);
        } catch (IBrokerControllerEndRequestsSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public void purge(String clientId) throws SessionException {
        try {
            this.brokerController.purge(clientId);
        } catch (IBrokerControllerPurgeSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public boolean pullResponses(Map<String, Class<?>> mapping, String action,
            boolean fromStart, int count, String clientId, Collection queue)
            throws SessionException {
        BrokerResponseMessages responses = null;

        GetResponsePosition position = (fromStart) ? GetResponsePosition.BEGIN
                : GetResponsePosition.CURRENT;

        try {
            responses = brokerController.pullResponses(action, position, count,
                    clientId);
        } catch (IBrokerControllerPullResponsesSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }

        for (BrokerResponseMessages.SOAPMessage response : responses
                .getSOAPMessage()) {
            // Convert the message XML to a SOAPMessage
            if (action.isEmpty())
                queue.add(new BrokerResponse(mapping, response.getAny()));
            else
                queue.add(new BrokerResponse(mapping.get(action), response.getAny()));
        }

        return responses.isEOM();
    }

    public void flush(int sendCount, String clientId, int largerTimeout,
            int timeoutMilliseconds) throws SessionException {

        try {
            this.brokerController.flush(sendCount, clientId, largerTimeout,
                    timeoutMilliseconds);
        } catch (IBrokerControllerFlushSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public BrokerClientStatus GetStatus(String clientId)
            throws SessionException {
        try {
            return this.brokerController.getBrokerClientStatus(clientId);
        } catch (IBrokerControllerGetBrokerClientStatusSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public int getRequestsCount(String clientId) throws SessionException {
        try {
            return this.brokerController.getRequestsCount(clientId);
        } catch (IBrokerControllerGetRequestsCountSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }
}
