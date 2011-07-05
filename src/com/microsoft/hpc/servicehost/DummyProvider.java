//------------------------------------------------------------------------------
// <copyright file="DummyProvider.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Return the SOAP fault message to the Broker worker
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

import javax.xml.namespace.QName;
import javax.xml.soap.Detail;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.microsoft.hpc.exceptions.SOAFaultCode;
import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.scheduler.session.fault.ObjectFactory;
import com.microsoft.hpc.scheduler.session.fault.SvcHostSessionFault;
import com.microsoft.hpc.scheduler.session.servicecontext.StringResource;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;

/**
 * @author t-junchw
 * @date May 9, 2011
 * @description used to accept all the request from broker, then return the same soap fault message back.
 */
@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
public class DummyProvider implements Provider<SOAPMessage>
{
 
    public DummyProvider()
    {
    }

    
    /**(non-Javadoc)
     * @see javax.xml.ws.Provider#invoke(java.lang.Object)
     * @param request message
     * @return generated fault message
     */
    @Override
    public SOAPMessage invoke(SOAPMessage request)
    {

        try
        {
           
            MessageFactory mf = MessageFactory
                    .newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage response = mf.createMessage();
            
            //add soap header
            SOAPHeader soapHeader = response.getSOAPHeader();
            QName headerName = new QName(
                    Constant.HpcActionHeaderNS, "Action");
            SOAPHeaderElement headerElement = soapHeader
                    .addHeaderElement(headerName);
            headerElement.setTextContent(SvcHostSessionFault.Action);

            //add fault part to the soap body
            SOAPBody respBody = response.getSOAPBody();
            SOAPFault fault = respBody.addFault();
            SOAPFactory sf = SOAPFactory.newInstance();

            //set fault code
            Name faultName = sf.createName("Sender", "",
                    javax.xml.soap.SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE);
            fault.setFaultCode(faultName);
            
            //set fault string
            fault.setFaultString(StringResource
                    .getResource("FailedToInitializeServiceHost"));
            
            //set fault details
            ObjectFactory objectFactory = new ObjectFactory();
            SvcHostSessionFault sessionFault = objectFactory
                    .createSessionFault();
            sessionFault.setCode(SOAFaultCode.Service_InitializeFailed.getCode());
            sessionFault.setReason(objectFactory
                    .createSessionFaultReason(StringResource
                            .getResource("FailedToInitializeServiceHost")));
            
            //convert SessionFault instance to DOM object
            Element detailElement = InterceptorUtility
                    .createDetailElement(sessionFault);
            
            //import DOM to the fault detail
            Detail detail = fault.addDetail();
             
            Node DocImportedNode = detail.getOwnerDocument().importNode(
                    detailElement.getFirstChild(), true);
            detail.appendChild(DocImportedNode);
            
            return response;

        } catch (SOAPException e)
        {
            TraceHelper.traceError(e.toString());
            TraceHelper.traceStackError(e);
            return null;
        }

    }
}
