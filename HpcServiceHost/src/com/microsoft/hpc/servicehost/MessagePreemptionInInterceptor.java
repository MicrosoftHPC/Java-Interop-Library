// ------------------------------------------------------------------------------
// <copyright file="MessagePreemptionInInterceptor.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Inbound interceptor
// Capture the inbound message
// Message Preemption
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

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;
import javax.xml.soap.SOAPMessage;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.interceptor.OutgoingChainInterceptor;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.BindingOperationInfo;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;

public class MessagePreemptionInInterceptor extends AbstractSoapInterceptor {

    private HpcServiceHostWrapper hpcHostWrapper;

    public MessagePreemptionInInterceptor(HpcServiceHostWrapper wrapper) {
        super(Phase.POST_STREAM);
        this.hpcHostWrapper = wrapper;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     */
    public void handleMessage(SoapMessage message) throws Fault {

        ServiceContext.Logger.traceEvent(Level.INFO, "MessagePreemptionInInterceptor : handle inbound message");

        // After receiving cancel event, skip invoking the hosted service.
        // The message inspector will reply a fault message to the broker
        // worker.
        if (this.hpcHostWrapper.receivedCancelEvent) {
            ServiceContext.Logger.traceEvent(Level.INFO, "MessagePreemptionInInterceptor : receivedCancelEvent");

            Exchange exchange = message.getExchange();
            BindingOperationInfo bin = exchange.get(BindingOperationInfo.class);
            if (null != bin && null != bin.getOperationInfo()
                    && bin.getOperationInfo().isOneWay()) {
                closeInput(message);
                return;
            }

            // Get the generated response as a javax.xml.soap.SOAPMessag object
            MessageImpl msg = new MessageImpl();
            SoapMessage response = new SoapMessage(msg);

            // Create a CXF-specific response (SoapMessage)
            Endpoint ep = exchange.get(Endpoint.class);
            Message outMessage = exchange.getOutMessage();
            if (outMessage == null) {
                outMessage = ep.getBinding().createMessage();
            }

            // Set the SOAPMessage response into a CXF-specific response
            outMessage.setContent(SOAPMessage.class, response);

            // Put the CXF-specific message into the current message exchange
            exchange.setOutMessage(outMessage);
            outMessage.setExchange(exchange);

            // add necessary CXF outbound interceptors
            InterceptorChain chain = OutgoingChainInterceptor
                    .getOutInterceptorChain(message.getExchange());
            outMessage.setInterceptorChain(chain);
            chain.doInterceptStartingAfter(outMessage,
                    SoapPreProtocolOutInterceptor.class.getName());
            message.getInterceptorChain().abort();

        } else {
            // identity for a soap message
            UUID messageUuid = UUID.randomUUID();
            message.getExchange().put("ID", messageUuid.toString());
            this.hpcHostWrapper.processingMessageIds
                    .add(messageUuid.toString());
        }
    }

    /**
     * @description close the input if inbound message is unavailable or oneway
     * @param message
     */
    private void closeInput(SoapMessage message) {
        InputStream is = message.getContent(InputStream.class);
        if (is != null) {
            try {
                is.close();
                message.removeContent(InputStream.class);
            } catch (IOException ioex) {
                // ignore
            }
        }
    }
}
