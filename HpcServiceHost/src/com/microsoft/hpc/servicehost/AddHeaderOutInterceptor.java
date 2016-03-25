// ------------------------------------------------------------------------------
// <copyright file="AddHeaderOutInterceptor.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
//  Add action header to the message when the message level preemption is enabled
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
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.session.RetryOperationError;

/**
 * @author t-junchw
 * @date May 16, 2011
 * @description add header to the soap message during message preemption
 */
public class AddHeaderOutInterceptor extends AbstractSoapInterceptor {

    private static final Logger LOG = LogUtils.getL7dLogger(AddHeaderOutInterceptor.class);
    HpcServiceHostWrapper hpcHostWrapper;

    /**
     * @param wrapper
     */
    public AddHeaderOutInterceptor(HpcServiceHostWrapper wrapper) {
        super(Phase.PRE_PROTOCOL);
        this.hpcHostWrapper = wrapper;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     */
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        ServiceContext.Logger.traceEvent(Level.INFO, "AddHeaderOutInterceptor : handle message");

        if (hpcHostWrapper.enableMessageLevelPreemption) {
            String guid = (String) message.getExchange().get("ID");
            if (guid == null) {
                return;
            }

            // InterceptorUtility.addOrUpdateMessageHeader(message, "Action",
            // "http://www.w3.org/2005/08/addressing",
            // com.microsoft.hpc.scheduler.session.fault.SvcHostSessionFault.Action);
        }

        Fault f = (Fault) message.getContent(Exception.class);
        QName qn = new QName("http://www.w3.org/2005/08/addressing", "Action");
        Header header = message.getHeader(qn);
        if (header != null && header.getObject() != null) {
            Element root = (Element) header.getObject();
            if (Pattern
                    .compile(Pattern.quote("RetryOperationError"),
                            Pattern.CASE_INSENSITIVE)
                    .matcher(root.getTextContent()).find()) {
                root.setTextContent("http://hpc.microsoft.com/session/RetryOperationError");
                RetryOperationError error = new RetryOperationError();
                Element detailElement = InterceptorUtility
                        .createDetailElement(error);
                f.setDetail(detailElement);
                message.setContextualProperty(
                        org.apache.cxf.message.Message.FAULT_STACKTRACE_ENABLED,
                        "false"); // disable stack trace to make it compatible
                // with broker
            }
        }
    }

}
