// ------------------------------------------------------------------------------
// <copyright file="MessagePreemptionInInterceptor.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Outbound interceptor
// Capture the outbound message
// For message Preemption
// </summary>
// ------------------------------------------------------------------------------
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
package com.microsoft.hpc.servicehost;

import java.util.logging.Level;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import com.microsoft.hpc.exceptions.SOAFaultCode;
import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.scheduler.session.fault.FaultMessageException;
import com.microsoft.hpc.scheduler.session.fault.ObjectFactory;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.scheduler.session.fault.SvcHostSessionFault;

/**
 * @author t-junchw
 * @date May 10, 2011
 * @description create fault message during message preemption The client should
 *              not try to create or attach to a session. It should report the
 *              error to their helpdesk. There is something wrong with app's
 *              implementation or installation
 */
public class MessagePreemptionOutInterceptor extends AbstractSoapInterceptor
{
    private HpcServiceHostWrapper hpcHostWrapper;

    public MessagePreemptionOutInterceptor(HpcServiceHostWrapper wrapper)
    {
        super(Phase.PRE_PROTOCOL);
        this.hpcHostWrapper = wrapper;

    }

    /**
     * (non-Javadoc)
     * 
     * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     */
    @Override
    public void handleMessage(SoapMessage message) throws Fault
    {
        if (hpcHostWrapper.enableMessageLevelPreemption)
        {
            String id = (String) message.getExchange().get("ID");
            ServiceContext.Logger.traceEvent(Level.INFO, id);
            // If the message is not being processed, reply a fault message to
            // the broker.
            if (!this.hpcHostWrapper.processingMessageIds.contains(id))
            {
                // create session fault instance
                ObjectFactory objectFactory = new ObjectFactory();
                SvcHostSessionFault sessionFault = objectFactory
                        .createSessionFault();

                sessionFault.setCode(SOAFaultCode.Service_Preempted.getCode());
                sessionFault
                        .setReason(objectFactory.createSessionFaultReason(String
                                .valueOf(this.hpcHostWrapper.processingMessageIds
                                        .size())));

                // create the fault detail
                Element detailElement = InterceptorUtility
                        .createDetailElement(sessionFault);
                Fault fault = new Fault(new FaultMessageException("",
                        sessionFault));
                fault.setDetail(detailElement);
                throw fault;

            } else
            {
                // The service host receives the cancel event when the request
                // is
                // being processed, so add a header to notice the broker.
                // It is mandatory when this response message is the last one.
                // (no incoming request anymore)
                if (this.hpcHostWrapper.receivedCancelEvent)
                {
                    int messageCount = this.hpcHostWrapper.processingMessageIds
                            .size();
                    InterceptorUtility.addOrUpdateMessageHeader(message,
                            Constant.MessageHeaderPreemption,
                            Constant.HpcHeaderNS, messageCount);
                }
            }
        }
    }

}
