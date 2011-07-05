//------------------------------------------------------------------------------
// <copyright file="CxfBrokerClient.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      CxfBrokerClient class
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Action;
import javax.xml.ws.Dispatch;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.Service;
import javax.xml.ws.soap.AddressingFeature;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.DispatchImpl;
import org.apache.cxf.transport.http.HTTPConduit;

/**
 * @author junsu
 * 
 */
class CxfBrokerClient<TContract extends Service> extends CxfClientBase
{
    static HashMap<Class<?>, JAXBContext> contextMap = new HashMap<Class<?>, JAXBContext>();;

    HashMap<String, Dispatch<SOAPMessage>> dispatchMap;
    Map<String, String> actionMap;
    Map<String, Class<?>> responseTypeMap;
    Map<String, String> responseMap;
    QName portName;
    javax.xml.ws.Service soaService;
    String EPR;
    Client client;

    public CxfBrokerClient(String username, String password, String epr,
            Class<TContract> service) {
        this.EPR = epr;
        Class<?> interfaceType = GetServiceInterface(service);

        CreateActionMap(interfaceType);

        portName = new QName("http://hpc.microsoft.com", "brokerPort");
        soaService = Service.create(GetServiceName(service));

        soaService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, epr);
        Object interfaceObj = soaService.getPort(portName, interfaceType);
        Initialize(username, password, interfaceObj);

        client = ClientProxy.getClient(serviceObj);

        this.dispatchMap = new HashMap<String, Dispatch<SOAPMessage>>();
    }

    private QName GetServiceName(Class<TContract> service) {
        try {
            Field field = service.getDeclaredField("SERVICE");
            return (QName) field.get(null);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    SR.v("RequireServiceContractType"));
        }
    }

    private void CreateActionMap(Class<?> interfaceType) {
        actionMap = new HashMap<String, String>();
        responseMap = new HashMap<String, String>();
        responseTypeMap = new HashMap<String, Class<?>>();
        Method[] methods = interfaceType.getMethods();
        for (Method t : methods) {
            Action action = t.getAnnotation(Action.class);
            RequestWrapper request = t.getAnnotation(RequestWrapper.class);
            ResponseWrapper response = t.getAnnotation(ResponseWrapper.class);
            if (actionMap.containsKey(request.className())) {
                // if this request type is in the table
                // this is a ambiguous request type, clear it to null
                actionMap.put(request.className(), null);
            } else {
                actionMap.put(request.className(), action.input());
            }

            try {
                responseTypeMap.put(action.output(), Class.forName(response.className()));
            } catch (ClassNotFoundException e) {
            }
            
            if (responseMap.containsKey(response.className())) {
                // if this request type is in the table
                // this is a ambiguous request type, clear it to null
                responseMap.put(response.className(), null);
            } else {
                responseMap.put(response.className(), action.input());
            }
        }
    }

    private Class<?> GetServiceInterface(Class<TContract> service) {
        Method[] methods = service.getMethods();
        for (Method t : methods) {
            if (t.getName().startsWith("getDefault")
                    && t.getParameterTypes().length == 0) {
                // searches for a function that starts with "getDefault" (as in
                // getDefaultBindingIEchoSvc)
                return t.getReturnType();
            }
        }

        throw new IllegalArgumentException(SR.v("RequireServiceContractType"));
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

    private Dispatch<SOAPMessage> getSOAPDispatch(String action) {
        if (dispatchMap.containsKey(action)) {
            return dispatchMap.get(action);
        } else {
            Dispatch<SOAPMessage> dispatch = soaService.createDispatch(
                    portName, SOAPMessage.class, Service.Mode.MESSAGE,
                    new AddressingFeature(false));

            DispatchImpl<SOAPMessage> dispImpl = (DispatchImpl<SOAPMessage>) dispatch;
            trustAll((HTTPConduit) dispImpl.getClient().getConduit());
            addWSSHeaders(dispImpl.getClient().getEndpoint());

            dispatch.getRequestContext().put(
                    Dispatch.ENDPOINT_ADDRESS_PROPERTY, this.EPR);
            dispatch.getRequestContext().put(Dispatch.SOAPACTION_USE_PROPERTY,
                    true);
            dispatch.getRequestContext().put(Dispatch.SOAPACTION_URI_PROPERTY,
                    action);
            dispatchMap.put(action, dispatch);

            return dispatch;
        }
    }

    /**
     * Client Identity QName
     */
    private QName clientIdHeader = new QName(Constant.HpcHeaderNS,
            Constant.ClientIdHeaderName);

    /**
     * Generates the SOAP message for the dispatch
     * 
     * @param payload
     *            the message to be generated
     * @param userData
     *            userdata to be supplied with the message
     * @return
     */
    private SOAPMessage createSOAPMessage(Dispatch<SOAPMessage> dispatch,
            Object payload, String clientid, Object userData)
            throws SessionException {
        try {
            // Create message factory
            MessageFactory mf = ((SOAPBinding) dispatch.getBinding())
                    .getMessageFactory();
            SOAPMessage msg = mf.createMessage();

            // Add userData as a SOAP header
            generateUserDataHeader(userData, msg);

            // Add clientid as a SOAP header
            generateClientIdHeader(msg, clientid);

            // Add the payload object as a message body
            JAXBContext jc = getContext(payload.getClass());

            Marshaller marshaller = jc.createMarshaller();
            marshaller.marshal(payload, msg.getSOAPBody());
            msg.saveChanges();

            return msg;
        } catch (Exception e) {
            throw new SessionException(e);
        }
    }

    /**
     * Set SOAP header for clientId
     * <p>
     * Java Changes: return type to void, added SOAPMessage msg as parameter
     * Required for interoperability with Java's native SOAP functions
     * </p>
     * 
     * @param msg
     * @throws SOAPException
     */
    private void generateClientIdHeader(SOAPMessage msg, String clientId)
            throws SOAPException {
        SOAPHeader header = msg.getSOAPHeader();
        SOAPHeaderElement headerElement = header
                .addHeaderElement(clientIdHeader);
        headerElement.setValue(clientId);
    }

    /**
     * Generates SOAP header for user data
     * <p>
     * Java Changes: return type to void, added SOAPMessage msg as parameter
     * Required for interoperability with Java's native SOAP functions
     * </p>
     * 
     * @param userData
     * @param msg
     * @throws SOAPException
     */
    private static void generateUserDataHeader(Object userData, SOAPMessage msg)
            throws SOAPException {
        SOAPHeader header = msg.getSOAPHeader();
        SOAPHeaderElement headerElement = header.addHeaderElement(new QName(
                Constant.HpcHeaderNS, Constant.UserDataHeaderName));
        headerElement.setValue(userData.toString());
    }

    /**
     * Send the message by OneWay Operation
     * 
     * @param payload
     * @param userData
     * @throws SessionException
     */
    public void SendMessage(Object payload, String clientId, Object userData,
            int timeout) throws SessionException {
        // find out the action name
        String className = payload.getClass().getName();
        if (!actionMap.containsKey(className)
                || actionMap.get(className) == null) {
            throw new SessionException(
                    "Cannot find the action assiocate with the request class.");
        }

        String Action = actionMap.get(className);
        SendMessage(payload, clientId, userData, Action, timeout);
    }

    public void SendMessage(Object payload, String clientId, Object userData,
            String action, int timeout) throws SessionException {
        // find the Dispatcher
        Dispatch<SOAPMessage> dispatch = getSOAPDispatch(action);

        // create the message based on payload and userData
        SOAPMessage msg;
        msg = createSOAPMessage(dispatch, payload, clientId, userData);

        setClientTimeout(((DispatchImpl<SOAPMessage>) dispatch).getClient(),
                timeout);

        // send out the message
        dispatch.invokeOneWay(msg);
    }

    public String getRequestAction(Class<?> messageClass)
            throws SessionException {
        if (responseMap.containsKey(messageClass.getName())) {
            return responseMap.get(messageClass.getName());
        } else {
            throw new SessionException(SR.v("AmbiguousOperation"));
        }
    }
    
    public Map<String, Class<?>> getResponseTypeMapping()
    {
        return responseTypeMap;
    }
}
