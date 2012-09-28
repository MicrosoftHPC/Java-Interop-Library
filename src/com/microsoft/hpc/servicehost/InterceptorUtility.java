// ------------------------------------------------------------------------------
// <copyright file="Interceptorutility.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Utility for service host
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

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.session.RetryOperationError;

/**
 * @author t-junchw
 * @date May 9, 2011
 * @description utility for CXF interceptor
 */
public final class InterceptorUtility
{

    /**
     * @description add message header
     * @param message
     * @param name
     * @param namespace
     * @param data
     */
    static public void addOrUpdateMessageHeader(SoapMessage message, String name,
            String namespace, Object data)
    {
        Document document = DOMUtils.createDocument();
        Element root = document.createElementNS(namespace, name);
        root.setTextContent(data.toString());
        
        QName headname = new QName(namespace, name);
        SoapHeader header = null; 
        List<Header> headers = message.getHeaders();
        for(Header h : headers) 
        {
            if (h.getName().getLocalPart() == name && 
                    h.getName().getNamespaceURI() == namespace) {
                header = (SoapHeader) h;
                break;
            }
        }
        if(header == null) {
            header = new SoapHeader(headname, root);
            headers.add(header);
        } else {
            ((Element)header.getObject()).setTextContent(data.toString());
        }
        
    }

    /**
     * @description create fault message detail
     * @param sessionFault
     * @return
     */
    public static Element createDetailElement(com.microsoft.hpc.scheduler.session.fault.SvcHostSessionFault sessionFault)
    {
        Element detail = null;
        try
        {
            Document detailDocument = DOMUtils.createDocument();
            detail = detailDocument.createElement("detail");
            Document doc = DOMUtils.createDocument();
            JAXBContext jaxbContext = JAXBContext
                    .newInstance(com.microsoft.hpc.scheduler.session.fault.SvcHostSessionFault.class);

            // Java Class converted to XML
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(sessionFault, doc);

            Node DocImportedNode = detailDocument.importNode(
                    doc.getDocumentElement(), true);
            detail.appendChild(DocImportedNode);

            if (DocImportedNode.getLocalName().equals("SessionFault"))
            {
                // SessionFault
                Attr sessionAttr = detailDocument.createAttribute("xmlns:i");
                sessionAttr
                        .setValue(Constant.XMLSchemaInstanceNS);
                DocImportedNode.getAttributes().setNamedItem(sessionAttr);
            }

            // debug the jaxb convert result
            TransformerFactory tfactory = TransformerFactory.newInstance();
            Transformer transformer = tfactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, new StreamResult(System.out));

            return detail;
        } catch (Exception e)
        {
            return detail;
        }
    }
    
    /**
     * @description create fault message detail
     * @param sessionFault
     * @return
     */
    public static Element createDetailElement(RetryOperationError error)
    {
        Element detail = null;
        try
        {
            Document detailDocument = DOMUtils.createDocument();
            detail = detailDocument.createElement("detail");
            Document doc = DOMUtils.createDocument();
            JAXBContext jaxbContext = JAXBContext
                    .newInstance(RetryOperationError.class);

            // Java Class converted to XML
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(error, doc);

            Node DocImportedNode = detailDocument.importNode(
                    doc.getDocumentElement(), true);
            detail.appendChild(DocImportedNode);

            // debug the jaxb convert result
            TransformerFactory tfactory = TransformerFactory.newInstance();
            Transformer transformer = tfactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, new StreamResult(System.out));

            return detail;
        } catch (Exception e)
        {
            return detail;
        }
    }
}
