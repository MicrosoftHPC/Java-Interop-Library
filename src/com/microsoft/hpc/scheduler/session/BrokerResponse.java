//------------------------------------------------------------------------------
// <copyright file="BrokerResponse.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Implement BrokerResponse class.
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

/**
 * Wraps response messages to provide access to data, faults and user data
 * Copyright (c) Microsoft Corporation.  All rights reserved.
 */
package com.microsoft.hpc.scheduler.session;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Document;

/**
 * Wraps response messages to provide access to data, faults, and user data
 * 
 * @param <TMessage>
 *            Response message type
 */
// TODO: Consider whether BrokerResponse should expose raw SOAP Message as
// property (from .NET)
public class BrokerResponse<TMessage>
{
    static HashMap<Class<?>, JAXBContext> contextMap = new HashMap<Class<?>, JAXBContext>();

    /**
     * Response message's buffer
     */
    private SOAPMessage messageBuffer;

    /**
     * Type to convert the response message to
     */
    Class<TMessage> typedMessageConverter;

    /**
     * the response object
     */
    Object message;
    
    Map<String, Class<?>> typeMapping;
    
    /**
     * Stores the fault collection
     */
    // private Collection<?> faultCollection;

    /**
     * Initializes a new instance of the BrokerResponse class
     * 
     * @param typedMessageConverter
     *            Converts the message to its type
     * @param messageBuffer
     *            Response Message's buffer
     */
    BrokerResponse(Class<TMessage> typedMessageConverter, Object message) {
        this.message = message;
        this.typedMessageConverter = typedMessageConverter;
        this.result = null;
    }

    public BrokerResponse(Map<String, Class<?>> mapping, Object message) {
        this.message = message;
        this.typedMessageConverter = null;
        this.result = null;
        this.typeMapping = mapping;
    }

    /**
     * Gets response's data or throw exception if its a fault
     * 
     * @return Parses the result from the message, if haven't been done so
     *         already, and returns the result of the message
     * @throws SOAPFaultException
     *             An exception thrown from a fault in the SOAP Message
     * @throws SOAPException
     *             An exception thrown from SOAP protocol
     */
    TMessage result;

    public SOAPMessage getMessage() {
        if (this.messageBuffer == null)
            this.messageBuffer = getSOAPMessage(message);
        return this.messageBuffer;
    }

    /**
     * Implement the cache for JAXContext for one type
     * 
     * @param type
     * @return
     * @throws JAXBException
     */
    static private JAXBContext getContext(Class<?> type) throws JAXBException {
        JAXBContext jc;
        if (contextMap.containsKey(type)) {
            jc = contextMap.get(type);
        } else {
            jc = JAXBContext.newInstance(type);
            synchronized (contextMap) {
                contextMap.put(type, jc);
            }
        }

        return jc;
    }

    public TMessage getResult() throws SOAPFaultException, SOAPException {
        if (result == null) {
            // Get the SOAP body
            SOAPBody soapBody = getMessage().getSOAPBody();

            // If it isnt a fault
            if (!soapBody.hasFault()) {
                Document doc = soapBody.extractContentAsDocument();
                Class<?> msg;
                // fetch the type
                if (typedMessageConverter == null) {
                    msg = this.typeMapping.get(Utility.getAction(this.messageBuffer));
                }
                else {
                    msg = this.typedMessageConverter;
                }
                
                try {
                    JAXBContext jc = getContext(msg);
                    Unmarshaller unmarshaller = jc.createUnmarshaller();
                    result = (TMessage) unmarshaller.unmarshal(
                            doc,
                            msg).getValue();
                } catch (Exception e) {
                    throwIfFaultUnderstood(soapBody, soapBody.getFault(),
                            Utility.getAction(getMessage()),
                            Utility.getSOAPVersion(getMessage()));
                }
            }
            // If it is a fault
            else {
                SOAPFault messageFault = soapBody.getFault();
                String action = Utility.getAction(getMessage());

                if (messageFault.hasDetail()) {
                    // FaultDescription?
                    throw new SOAPFaultException(messageFault);
                }
                throwIfFaultUnderstood(soapBody, messageFault, action,
                        Utility.getSOAPVersion(getMessage()));
            }
        }
        return result;
    }

    /**
     * Returns user data supplied when sending the response's corresponding
     * request
     * 
     * @return Returns the data supplied with the responses's request in String
     *         format
     */
    public String getUserData() {
        SOAPMessage soapMessage = this.getMessage();
        SOAPHeader soapHeader;
        try {
            soapHeader = soapMessage.getSOAPHeader();
        } catch (SOAPException e) {
            return null;
        }

        Iterator<?> it = soapHeader.getChildElements(new QName(
                Constant.HpcHeaderNS, Constant.UserDataHeaderName));
        if (it.hasNext()) {
            SOAPHeaderElement he = (SOAPHeaderElement) it.next();
            return he.getTextContent();
        }

        return null;
    }

    /**
     * Gets the message out of a SOAP xml file.
     * 
     * @param messageXml
     *            message to be parsed
     * @return
     */
    SOAPMessage getSOAPMessage(Object messageXml) {
        try {
            org.w3c.dom.Element element = (org.w3c.dom.Element) messageXml;

            // Convert message from Element to string
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans;

            trans = tf.newTransformer();

            StringWriter sw = new StringWriter();
            trans.transform(new DOMSource(element), new StreamResult(sw));
            InputStream stream = new ByteArrayInputStream(sw.toString()
                    .getBytes());

            // Convert message from string to SOAPMessage
            MessageFactory mf = MessageFactory.newInstance();
            return mf.createMessage(new MimeHeaders(), stream);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Converts fault to exception and throws it
     * 
     * @param response
     *            Not used
     * @param fault
     *            SOAPFault used to create SOAPFaultException
     * @param action
     *            Action epr
     * @param version
     *            SOAP Version
     * 
     * @see SOAPFaultException
     * @see SOAPException
     * @throws SOAPFaultException
     *             if an exception occurred during SOAP process
     * @throws SOAPException
     *             if an exception occurred in SOAP format
     */
    private static void throwIfFaultUnderstood(SOAPBody response, SOAPFault fault,
            String action, SOAPVersion version) throws SOAPFaultException,
            SOAPException {
        SOAPFaultException exception;
        boolean isSenderFault;
        boolean isReceiverFault;
        QName subCode;

        if ((exception = new SOAPFaultException(fault)) != null) {
            // will ever return null?
            throw exception; // repetitive
        }

        if (version == SOAPVersion.SOAP11) {
            isSenderFault = true;
            isReceiverFault = true;
            subCode = fault.getFaultCodeAsQName();
        } else {
            isSenderFault = fault.getFaultCode().contains("Sender");
            isReceiverFault = fault.getFaultCode().contains("Receiver");
            subCode = (QName) fault.getFaultSubcodes().next(); // more?
        }

        if ((subCode != null) && (subCode.getNamespaceURI() != null)) {
            if (isSenderFault) {
                if (subCode
                        .getNamespaceURI()
                        .equalsIgnoreCase(
                                "http://schemas.microsoft.com/net/2005/12/windowscommunicationfoundation/dispatcher")) {
                    if (subCode.getLocalPart().equalsIgnoreCase(
                            "SessionTerminated")) {
                        throw new WebServiceException(
                                fault.getFaultReasonText(Locale.getDefault()));
                    }

                    if (subCode.getLocalPart().equalsIgnoreCase(
                            "TransactionAborted")) {
                        throw new ProtocolException(
                                fault.getFaultReasonText(Locale.getDefault()));
                    }
                }
            }

            if (isReceiverFault
                    && subCode
                            .getNamespaceURI()
                            .equalsIgnoreCase(
                                    "http://schemas.microsoft.com/net/2005/12/windowscommunicationfoundation/dispatcher")) {
                if (subCode.getLocalPart().equalsIgnoreCase(
                        "InternalServiceFault")) {
                    // if(fault.hasDetail())
                    // {
                    // throw new SOAPFaultException(fault);
                    // }

                    throw new SOAPFaultException(fault);// should work still?
                }

                if (subCode.getLocalPart().equalsIgnoreCase(
                        "DeserializationFailed")) {
                    throw new ProtocolException(fault.getFaultReasonText(Locale
                            .getDefault()));
                }
            }
        }
        throw new SOAPFaultException(fault); // repetitive
    }
}
