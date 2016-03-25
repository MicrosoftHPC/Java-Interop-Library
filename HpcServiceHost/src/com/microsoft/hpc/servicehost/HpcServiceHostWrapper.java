// ------------------------------------------------------------------------------
// <copyright file="HpcServiceHostWrapper.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Set up the Java Servie Host.
// Host the Customer's Jar or Dummy Service
// Host the Controller Service
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.jws.WebService;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.apache.cxf.transports.http.configuration.HTTPServerPolicy;
import org.apache.cxf.ws.addressing.WSAddressingFeature;

import com.microsoft.hpc.properties.ErrorCode;
import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.scheduler.session.servicecontext.Environment;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceRegistration;
import com.microsoft.hpc.scheduler.session.servicecontext.StringResource;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;
import java.io.File;
import java.io.FileInputStream;
import java.security.*;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.*;
import org.apache.cxf.configuration.jsse.TLSServerParameters;
import org.apache.cxf.configuration.security.ClientAuthentication;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

/**
 * @author t-junchw
 * @date May 9, 2011
 * @description Wrapper class for the construction of host service and
 * controller service
 */
public class HpcServiceHostWrapper {

    /**
     * @field Represents how an endpoint address is finalructed
     * http://<jarnetworkprefix>.<nodename>:<port>/<jobId>/<tasksystemId>
     */
    private final String BaseAddrTemplate = "http://%s:%s/%s/%s";

    /**
     * @field Timeout for Exiting event to execute. This must be the same as
     * node manager's default CTRL+C timeout
     */
    private int cancelTaskGracePeriod = Constant.DefaultCancelTaskGracePeriod;

    private int jobId;
    private int taskId;
    private int procNum;
    private boolean isEnableBackendSecurity = false;

    /**
     * @field CXF service retry Time out In MilliSecond
     */
    private int retryTimeoutInMilliSecond;

    /**
     * @field 60 second
     */
    private final int defaultRetryTimeout = 60 * 1000;

    /**
     * @field The real jar service host
     */
    public JaxWsServerFactoryBean host;

    /**
     * @field the controller service
     */
    public JaxWsServerFactoryBean controllerhost;

    /**
     * @field Enable the new feature MessageLevelPreemption or not.
     */
    public boolean enableMessageLevelPreemption;

    /**
     * @field This flag is set when host receives Ctrl-C event.
     */
    public boolean receivedCancelEvent;

    /**
     * @field object used for synchronization
     */
    public Object syncObjOnExitingCalled = new Object();

    /**
     * @field This flag indicates if the OnExiting event is triggered.
     */
    public boolean isOnExitingCalled = false;

    /**
     * @field This list stores ids of messages which are invoking the hosted
     * service. When the response is sent back to the broker, the id is removed
     * from this list. If a message come to host after Ctrl-C event, its id
     * won't be saved in this list.
     */
    public List<String> processingMessageIds = Collections
            .synchronizedList(new ArrayList<String>());

    /**
     * @field the offset to the port
     */
    private int portOffset;

    /**
     * @field the service registration
     */
    private ServiceRegistration serviceRegistration;

    /**
     * @field Http receive Timeout
     */
    private long receiveTimeout;

    /**
     * @field times to retry to host the user service
     */
    private int retrytimes = 3;

    /**
     * @field Ctrl-C hook
     */
    public ShutdownThread shutdownThread;

    public HpcServiceHostWrapper(ServiceRegistration serviceRegistration) {
        this.serviceRegistration = serviceRegistration;

        // Initialize Ctrl-C handler to receive shutdown events from node
        // manager
        this.invokeInitializeControlBreakHandler();
    }

    /**
     * @description method to host the service
     * @throws InterruptedException
     * @throws UnknownHostException
     */
    public void publish() throws InterruptedException, UnknownHostException {
        String errorMsg = null;

        int errorCode = ErrorCode.Success;
        errorCode = getEnvironmentVariables();

        // initially retry wait period is 0.5 second
        long retryWaitPeriodInMilliSecond = 500;

        if (errorCode != ErrorCode.Success) {
            return;
        }

        int retry = 0;
        long serviceStartTime = System.currentTimeMillis();
        while (true) {
            try {
                // try to host the user jar
                errorCode = RunInternal();

                if (retry == Integer.MAX_VALUE) {
                    retry = 0;
                } else {
                    retry++;
                }

                if (errorCode != ErrorCode.Success) {
                    if (errorCode < ErrorCode.ServiceHost_ExitCode_Start
                            || errorCode > ErrorCode.ServiceHost_ExitCode_End) {
                        errorCode = ErrorCode.ServiceHost_UnexpectedException;
                    }

                    errorMsg = StringResource
                            .getResource("FailedInStartingService");
                    ServiceContext.Logger.traceEvent(Level.SEVERE, errorMsg);
                    TraceHelper.traceError(errorCode + ":" + errorMsg);
                }
            } catch (Exception e) {
                TraceHelper.traceError(e.getMessage());
                errorCode = ErrorCode.ServiceHost_UnexpectedException;
                errorMsg = e.getMessage();
            }

            if (errorCode == ErrorCode.ServiceHost_AssemblyLoadingError
                    || errorCode == ErrorCode.ServiceHost_NoContractImplemented) {
                if (retry >= retrytimes) {
                    TraceHelper.traceInformation("exit while loop with too many retries " + retry);
                    break;
                }
            }

            if (errorCode == ErrorCode.Success) {
                TraceHelper.traceInformation("exit while loop with Success");
                break;
            }

            long serviceStopTime = System.currentTimeMillis();

            // if retry timeout is reached, work is done
            long elapsedStartTimeInMilliSecond = serviceStopTime
                    - serviceStartTime;
            if (elapsedStartTimeInMilliSecond > retryTimeoutInMilliSecond) {
                break;
            }

            serviceStartTime = System.currentTimeMillis();

            if (elapsedStartTimeInMilliSecond + retryWaitPeriodInMilliSecond > retryTimeoutInMilliSecond) {
                retryWaitPeriodInMilliSecond = retryTimeoutInMilliSecond
                        - elapsedStartTimeInMilliSecond;
            }

            ServiceContext.Logger.traceEvent(Level.INFO, String.format(
                    "Wait %s milliseconds and retry",
                    retryWaitPeriodInMilliSecond));

            TraceHelper.traceInformation(String.format(
                    "Wait %d milliseconds and retry.",
                    retryWaitPeriodInMilliSecond));

            // wait and retry
            Thread.sleep(retryWaitPeriodInMilliSecond);

            // back off
            retryWaitPeriodInMilliSecond *= 2;
        }
        if (errorCode != ErrorCode.Success) {
            RunAsDummy();
        }
    }

    /**
     * @description host the dummy service method invoked when failed to host
     * the user jar
     * @throws UnknownHostException
     */
    private void RunAsDummy() throws UnknownHostException {
        TraceHelper.traceInformation("RunAsDummy");

        String defaultBaseAddr = createEndpointAddress(Constant.ServiceHostPort
                + portOffset);
        TraceHelper.traceInformation("defaultBaseAddr = " + defaultBaseAddr);

        // destroy the existing host
        if (host != null) {
            try {
                host.getServer().destroy();
            } catch (Exception e) {
                // do nothing
            }
        }

        host = null;
        try {
            DummyProvider dummyService = new DummyProvider();

            host = new JaxWsServerFactoryBean();
            String listenUri = defaultBaseAddr + "/_defaultEndpoint/";
            TraceHelper.traceInformation("listenUri = " + listenUri);
            host.setAddress(listenUri);
            host.setServiceBean(dummyService);

            // log the in/out bound message
            host.getInInterceptors().add(new LoggingInInterceptor());
            host.getOutInterceptors().add(new LoggingOutInterceptor());

            TraceHelper.traceInformation(StringResource
                    .getResource("TryCreateHost"));
            Server server = host.create();

            JettyHTTPDestination destination = (JettyHTTPDestination) server
                    .getDestination();
            // Update back end binding's receive timeout with global settings if
            // they are enabled
            // update the Service setting
            getServiceSettings();
            HTTPServerPolicy httpServerPolicy = new HTTPServerPolicy();
            httpServerPolicy.setReceiveTimeout(receiveTimeout);
            destination.setServer(httpServerPolicy);

            TraceHelper.traceInformation(StringResource
                    .getResource("TryOpenCtrlHost"));
            String endpointAddress = createEndpointAddress(Constant.ServiceHostControllerPort
                    + portOffset);
            OpenHostController(endpointAddress);

            ServiceContext.Logger.traceEvent(Level.INFO, String.format(
                    "[HpcServiceHost]: Dummy service opened on %s", listenUri));
        } catch (Exception e) {
            TraceHelper.traceError(e.toString());
            ServiceContext.Logger.traceEvent(Level.SEVERE,
                    String.format("[HpcServiceHost]: " + e.toString()));

            if (host != null) {
                try {
                    host.getServer().destroy();
                } catch (Exception ex) {
                    // do nothing
                }
            }
        }
    }

    /**
     * @description Open the service host. All exceptions will be directly
     * thrown out. So the outer program must know how to deal with those
     * exceptions.
     * @return error code
     */
    private int RunInternal() {
        TraceHelper.traceInformation("RunInternal Start opening");

        ServiceContext.Logger.traceEvent(Level.ALL,
                "[HpcServiceHost]: Start opening");

        // Load the service jar from the specified path
        JarClassLoader jarClassLoader = null;

        try {
            jarClassLoader = new JarClassLoader(
                    serviceRegistration.serviceAssemblyFullPath);
            ServiceContext.Logger.traceEvent(Level.ALL,
                    "[HpcServiceHost]: Jar is loaded.");
        } catch (IOException e) {
            TraceHelper.traceError(e.getMessage());
            return ErrorCode.ServiceHost_AssemblyLoadingError;
        }

        boolean isimplemented = false;
        Class<?> userclass = null;
        try {
            // if the user specify the ServiceTypeName
            if (serviceRegistration.getServiceTypeName() != null) {
                userclass = jarClassLoader
                        .loadClass(serviceRegistration.serviceTypeName);
                // if the user specify the ServiceContractName
                if (serviceRegistration.getServiceContractName() != null) {
                    Class<?>[] userinterfacesClass = userclass.getInterfaces();
                    for (Class<?> tempinterface : userinterfacesClass) {
                        if (tempinterface.getName() == serviceRegistration
                                .getServiceContractName()) {
                            isimplemented = true;
                            break;
                        }
                    }
                }
                if (!isimplemented) {
                    String message = String.format(StringResource
                            .getResource("CantFindServiceContract"),
                            serviceRegistration.serviceContractName,
                            serviceRegistration.serviceAssemblyFileName);
                    TraceHelper.traceError(message);
                    ServiceContext.Logger.traceEvent(Level.SEVERE, message);
                    return ErrorCode.ServiceHost_NoContractImplemented;
                }
            } // if the user only specify the ServiceTypeName
            else if (serviceRegistration.getServiceContractName() != null) {
                userclass = jarClassLoader
                        .loadClassbyInterface(serviceRegistration
                                .getServiceContractName());
            } // Try to auto discover the service contract interface from the jar
            else {
                userclass = jarClassLoader.findClass();
                TraceHelper.traceInformation("found class from jar : " + userclass.getName());
            }
        } catch (ClassNotFoundException e) {
            TraceHelper.traceError(e.getMessage());
            return ErrorCode.ServiceHost_ServiceTypeDiscoverError;
        }

        String defaultBaseAddr = null;
        try {
            defaultBaseAddr = createEndpointAddress(Constant.ServiceHostPort
                    + portOffset);
        } catch (UnknownHostException e) {
            TraceHelper.traceError(e.toString());
            return ErrorCode.ServiceHost_UnexpectedException;
        }
        TraceHelper.traceInformation("defaultBaseAddr = " + defaultBaseAddr);

        String listenUri = defaultBaseAddr + "/_defaultEndpoint";
        TraceHelper.traceInformation("listenUri = " + listenUri);

        try {
            // Set ssl info if using https
//            JettyHTTPServerEngineFactory factory = new JettyHTTPServerEngineFactory();
//            TLSServerParameters tlsParams = getSSLParameters();
//            if (tlsParams != null) {
//                // set ssl for host
//                factory.setTLSServerParametersForPort(Constant.ServiceHostPort + portOffset, tlsParams);
//                TraceHelper.traceInformation("Set tlsParams " + tlsParams.getSecureSocketProtocol() + " on Port " + (Constant.ServiceHostPort + portOffset));
//                // set ssl for controller host
//                factory.setTLSServerParametersForPort(Constant.ServiceHostControllerPort + portOffset, tlsParams);
//                TraceHelper.traceInformation("Set tlsParams " + tlsParams.getSecureSocketProtocol() + " on Port " + (Constant.ServiceHostControllerPort + portOffset));
//
//            } else {
//                TraceHelper.traceError("Security configuration failed.");
//                return ErrorCode.ServiceHost_ServiceHostFailedToOpen;
//            }

            host = new JaxWsServerFactoryBean();
            if (serviceRegistration.getEnableWSAddressing() == true) {
                // add ws-addressing feature
                host.getFeatures().add(new WSAddressingFeature());
            }

            host.setAddress(listenUri);
            host.setServiceBean(userclass.newInstance());

            LoggingInInterceptor login = new LoggingInInterceptor();
            LoggingOutInterceptor logout = new LoggingOutInterceptor();
            // log the in/out bound message
            // disable to speedup
//            host.getInInterceptors().add(login);
//            host.getOutInterceptors().add(logout);
            host.getOutFaultInterceptors().add(logout);
            host.getOutFaultInterceptors().add(
                    new AddHeaderOutInterceptor(this));

            // if users specify the WSDL location, try to find it from jar
            WebService annotation = userclass
                    .getAnnotation(javax.jws.WebService.class);
            String wSDLLocation = null;
            if (annotation != null) {
                wSDLLocation = annotation.wsdlLocation();
            }
            String folder = "";
            if (wSDLLocation != null && !wSDLLocation.isEmpty()) {
                wSDLLocation = wSDLLocation.substring(wSDLLocation
                        .indexOf("file:") + 5);
                int index = serviceRegistration.getServiceAssemblyFullPath()
                        .lastIndexOf(File.separatorChar);
                folder = serviceRegistration.getServiceAssemblyFullPath()
                        .substring(0, index + 1);
                host.setWsdlLocation(folder + wSDLLocation);
                TraceHelper.traceInformation("WsdlLocation = " + folder + wSDLLocation);
            }

            // Add interceptors if the config enables the message
            // level preemption.
            if (this.enableMessageLevelPreemption == true) {
                host.getInInterceptors().add(
                        new MessagePreemptionInInterceptor(this));
                host.getOutInterceptors().add(
                        new MessagePreemptionOutInterceptor(this));
            }

            // add ws-security interceptors
            if (this.isEnableBackendSecurity == true) {
                addWSSHeaders(host);
            }

            //enable the debug to stack trace in fault soap message
            host.getBus().setProperty("faultStackTraceEnabled", "true");

            // create server
            TraceHelper.traceInformation(StringResource.getResource("TryCreateHost"));
            Server server = host.create();

            // Update backend binding's receive timeout and max message settings
            // with global settings if they are enabled
            // update the Service setting
            getServiceSettings();
            JettyHTTPDestination destination = (JettyHTTPDestination) server
                    .getDestination();

            // Configure Jetty server max idle time out
            JettyHTTPServerEngine engine = (JettyHTTPServerEngine) destination.getEngine();
            engine.getConnector().setMaxIdleTime((int) receiveTimeout);

            HTTPServerPolicy httpServerPolicy = new HTTPServerPolicy();
            httpServerPolicy.setReceiveTimeout(receiveTimeout);
            ServiceContext.Logger.traceEvent(Level.INFO, "Service Operation Timeout" + receiveTimeout);
            destination.setServer(httpServerPolicy);

            TraceHelper.traceInformation("Service host successfully opened on "
                    + server.getEndpoint().getEndpointInfo().getAddress());

            TraceHelper.traceInformation(StringResource
                    .getResource("TryOpenCtrlHost"));
            String endpointAddress = createEndpointAddress(Constant.ServiceHostControllerPort
                    + portOffset);

            OpenHostController(endpointAddress);
        } catch (Exception e) {
            TraceHelper.traceError(e.toString());
            TraceHelper.traceStackError(e);
            ServiceContext.Logger.traceEvent(Level.SEVERE, "[HpcServiceHost]: "
                    + e.toString());

            return ErrorCode.ServiceHost_ServiceHostFailedToOpen;
        }

        ServiceContext.Logger.traceEvent(Level.INFO, String.format(
                "[HpcServiceHost]: Service host successfully opened on %s ",
                listenUri));

        TraceHelper.traceInformation("Service host successfully opened on "
                + listenUri);

        return ErrorCode.Success;
    }

    private void addWSSHeaders(JaxWsServerFactoryBean fc) {
        final String WSSE_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        final String WSU_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
        final String SignVerifyPropFile = "sign.properties";
        final String DecryptPropFile = "decrypt.properties";
        final String SignPropFile = "decrypt.properties";
        final String EncryptPropFile = "encrypt.properties";

        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.TIMESTAMP + " "
                + WSHandlerConstants.SIGNATURE + " "
                + WSHandlerConstants.ENCRYPT + " "
        );
//        inProps.put(WSHandlerConstants.USER, KeyStorePasswordCallbackHandler.USERNAME);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, KeyStorePasswordCallbackHandler.class.getName());

        inProps.put(WSHandlerConstants.SIG_PROP_FILE, SignVerifyPropFile);
//        inProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
//        inProps.put(WSHandlerConstants.SIG_ALGO, "http://www.w3.org/2000/09/xmldsig#rsa-sha1");

        inProps.put(WSHandlerConstants.DEC_PROP_FILE, DecryptPropFile);
//        inProps.put(WSHandlerConstants.ENC_KEY_TRANSPORT, "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
        fc.getInInterceptors().add(wssIn);
        // if occurs "not understood header" error.
//        WSSPassThroughInterceptor wssPass = new WSSPassThroughInterceptor(this);
//        fc.getInInterceptors().add(wssPass);

        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.TIMESTAMP + " "
                + WSHandlerConstants.SIGNATURE + " "
                + WSHandlerConstants.ENCRYPT + " "
        );
//        outProps.put(WSHandlerConstants.USER, KeyStorePasswordCallbackHandler.USERNAME);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, KeyStorePasswordCallbackHandler.class.getName());

        outProps.put(WSHandlerConstants.SIGNATURE_USER, KeyStorePasswordCallbackHandler.USERNAME);
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, SignPropFile);
//        outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
//        outProps.put(WSHandlerConstants.SIG_ALGO, "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
        outProps.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}{" + WSU_NS + "}Timestamp;"
                + "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");;

        outProps.put(WSHandlerConstants.ENCRYPTION_USER, KeyStorePasswordCallbackHandler.USERNAME);
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, EncryptPropFile);
//        outProps.put(WSHandlerConstants.ENC_KEY_TRANSPORT, "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
        outProps.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        fc.getOutInterceptors().add(wssOut);
    }

    private TLSServerParameters getSSLParameters() {
        try {
//            java.security.Security.setProperty("jdk.tls.disabledAlgorithms", "DHE, ECDHE");
//            System.setProperty("https.protocols", "SSLv3,SSLv2Hello,TLSv1,TLSv1.1,TLSv1.2");
//            System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
//            System.setProperty("javax.net.ssl.keyStore", keystore.getAbsolutePath());
//            System.setProperty("javax.net.ssl.keyStorePassword", password);

            String soahome = Environment.getEnvironmentVariable(Constant.SOA_HOMEEnvVar);

            TLSServerParameters tlsParams = new TLSServerParameters();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            String password = "!!!123abc";

            File keystore = new File(soahome, "keystore.jks");
            TraceHelper.traceInformation("Open KeyManager file " + keystore.getAbsolutePath());
            keyStore.load(new FileInputStream(keystore), password.toCharArray());
            KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFactory.init(keyStore, password.toCharArray());
            KeyManager[] km = keyFactory.getKeyManagers();
            tlsParams.setKeyManagers(km);
            TraceHelper.traceInformation("KeyManagerFactory.getDefaultAlgorithm(): " + KeyManagerFactory.getDefaultAlgorithm());

            File truststore = new File(soahome, "truststore.jks");
            TraceHelper.traceInformation("Open TrustManager file " + truststore.getAbsolutePath());
            keyStore.load(new FileInputStream(truststore), password.toCharArray());
            TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustFactory.init(keyStore);
            TrustManager[] tm = trustFactory.getTrustManagers();
            tlsParams.setTrustManagers(tm);
            TraceHelper.traceInformation("TrustManagerFactory.getDefaultAlgorithm(): " + TrustManagerFactory.getDefaultAlgorithm());

            FiltersType filter = new FiltersType();
            filter.getInclude().add(".*_EXPORT_.*");
            filter.getInclude().add(".*_EXPORT1024_.*");
            filter.getInclude().add(".*_WITH_DES_.*");
            filter.getInclude().add(".*_WITH_NULL_.*");
            filter.getExclude().add(".*_DH_anon_.*");
            tlsParams.setCipherSuitesFilter(filter);

            ClientAuthentication ca = new ClientAuthentication();
            ca.setRequired(false);
            ca.setWant(false);
            tlsParams.setClientAuthentication(ca);

            tlsParams.setSecureSocketProtocol("TLSv1.2");
            tlsParams.setJsseProvider("SunJSSE");
//
//            SSLServerSocket sslSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket();
//            tlsParams.setJsseProvider("SunJSSE");
//            TraceHelper.traceInformation("getJsseProvider: " + tlsParams.getJsseProvider());
//
//            String[] supportSuites = sslSocket.getSupportedCipherSuites();
//            for (int i = 0; i < supportSuites.length; i++) {
//                TraceHelper.traceInformation("sslSocket.getSupportedCipherSuites: " + supportSuites[i]);
//            }
//
//            List<String> suites = java.util.Arrays.asList(supportSuites);
//            tlsParams.setCipherSuites(suites);
//
//            suites = tlsParams.getCipherSuites();
//            for (int i = 0; i < suites.size(); i++) {
//                TraceHelper.traceInformation("tlsParams.getCipherSuites(): " + suites.get(i));
//            }
//            TraceHelper.traceInformation("tlsParams.getCipherSuites() count: " + suites.size());
//
            return tlsParams;
        } catch (Exception e) {
            TraceHelper.traceError("Security configuration failed with the following: " + e.toString());
        }

//        catch (KeyStoreException kse) {
//            TraceHelper.traceError("Security configuration failed with the following: " + kse.getCause());
//        } catch (NoSuchAlgorithmException nsa) {
//            TraceHelper.traceError("Security configuration failed with the following: " + nsa.getCause());
//        } catch (FileNotFoundException fnfe) {
//            TraceHelper.traceError("Security configuration failed with the following: " + fnfe.getCause());
//        } catch (UnrecoverableKeyException uke) {
//            TraceHelper.traceError("Security configuration failed with the following: " + uke.getCause());
//        } catch (CertificateException ce) {
//            TraceHelper.traceError("Security configuration failed with the following: " + ce.getCause());
//        } catch (GeneralSecurityException gse) {
//            TraceHelper.traceError("Security configuration failed with the following: " + gse.getCause());
//        } catch (IOException ioe) {
//            TraceHelper.traceError("Security configuration failed with the following: " + ioe.getCause());
//        }
        return null;
    }

    /**
     * @description opens the service host controller service
     * @remark port disabled sharing on CNs/WNs, we need to open another port
     * @param defaultBaseAddr
     */
    private void OpenHostController(String defaultBaseAddr) throws IOException {
        TraceHelper.traceInformation("defaultBaseAddr of HostController is "
                + defaultBaseAddr);

        // Open the Control Service Host;
        TraceHelper.traceInformation("Created ServiceHost for controller.");
        HpcControllerSvc ControllerService = new HpcControllerSvc(this.jobId,
                cancelTaskGracePeriod, this);

        TraceHelper.traceInformation("defaultBaseAddr = " + defaultBaseAddr);
        controllerhost = new JaxWsServerFactoryBean();

        String listenUri = defaultBaseAddr + "/_defaultEndpoint/";
        TraceHelper.traceInformation("listenUri = " + listenUri);

        TraceHelper.traceInformation("Adding endpoint to controller.");
        controllerhost.setAddress(listenUri);
        controllerhost.setServiceBean(ControllerService);

        // log the in/out bound message
        controllerhost.getInInterceptors().add(new LoggingInInterceptor());
        controllerhost.getOutInterceptors().add(new LoggingOutInterceptor());

        TraceHelper.traceInformation(StringResource
                .getResource("TryCreateCtrlHost"));
        Server server = controllerhost.create();
        TraceHelper.traceInformation("Controller opened on "
                + server.getEndpoint().getEndpointInfo().getAddress());
    }

    /**
     * @description create the end point address for the service
     * @param port
     * @return
     * @throws UnknownHostException
     */
    private String createEndpointAddress(int port) throws UnknownHostException {
        // The format of default base addr:
        // http://<jarnetworkprefix>.<nodename>:<port>/<jobId>/<taskId>

        String jarNetworkPrefix = Environment
                .getEnvironmentVariable(Constant.NetworkPrefixEnv);
        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: Java network prefix = " + jarNetworkPrefix);

        String hostnameWithPrefix = InetAddress.getLocalHost().getHostName();
        if (jarNetworkPrefix != null && !jarNetworkPrefix.isEmpty()) {
            hostnameWithPrefix = String.format("%s.%s", jarNetworkPrefix,
                    hostnameWithPrefix);
        }

        return String.format(BaseAddrTemplate, hostnameWithPrefix, port, jobId,
                taskId);
    }

    /**
     * @description Get service's service operation Timeout
     */
    private void getServiceSettings() {
        String serviceOperationTimeoutString = Environment
                .getEnvironmentVariable(Constant.ServiceConfigServiceOperatonTimeoutEnvVar);
        try {
            receiveTimeout = Integer.parseInt(serviceOperationTimeoutString);
        } catch (NumberFormatException e) {
            ServiceContext.Logger
                    .traceEvent(
                            Level.ALL,
                            "[HpcServiceHost]: Error retrieving ServiceOperationTimeout. Defaulting to binding's default - "
                            + e.toString());
            TraceHelper.traceError(e.getMessage());
            receiveTimeout = Constant.DefaultServiceOperationTimeout;
        }
    }

    /**
     * @description get environment variables jobID taskTD CoreId procNum
     * cancelTaskGracePeriod enableMessageLevelPreemption
     * @return error code
     */
    private int getEnvironmentVariables() {
        String jobidenvvar = Environment.getEnvironmentVariable(Constant.JobIDEnvVar);
        try {
            jobId = Integer.parseInt(jobidenvvar);

        } catch (Exception e) {
            ServiceContext.Logger.traceEvent(Level.SEVERE,
                    StringResource.getResource("CantFindJobId"));
            return ErrorCode.ServiceHost_UnexpectedException;
        }

        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: Job Id = " + jobId);

        String taskidenvvar = Environment.getEnvironmentVariable(Constant.TaskSystemIDEnvVar);
        try {
            taskId = Integer.parseInt(taskidenvvar);
        } catch (Exception e) {
            ServiceContext.Logger.traceEvent(Level.SEVERE,
                    StringResource.getResource("CantFindTaskId"));
            return ErrorCode.ServiceHost_UnexpectedException;
        }

        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: Task Id = " + taskId);

        String procnumenvvar = Environment
                .getEnvironmentVariable(Constant.ProcNumEnvVar);
        try {
            procNum = Integer.parseInt(procnumenvvar);
        } catch (Exception e) {
            ServiceContext.Logger.traceEvent(Level.SEVERE,
                    StringResource.getResource("CantFindProcNum"));
            return ErrorCode.ServiceHost_UnexpectedException;
        }

        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: Number of processors (service capability) = "
                + procNum);

        String strServiceInitializationTimeout = Environment
                .getEnvironmentVariable(Constant.ServiceInitializationTimeoutEnvVar);
        try {
            retryTimeoutInMilliSecond = Integer
                    .parseInt(strServiceInitializationTimeout);
        } catch (Exception e) {
            ServiceContext.Logger
                    .traceEvent(
                            Level.WARNING,
                            "[HpcServiceHost]: invalid serviceInitializationTimeout value.  Fall back to default value = 60s");
            retryTimeoutInMilliSecond = defaultRetryTimeout;
        }

        String cancelTaskGracePeriodEnvVarStr = Environment
                .getEnvironmentVariable(Constant.CancelTaskGracePeriodEnvVar);
        try {
            cancelTaskGracePeriod = Integer
                    .parseInt(cancelTaskGracePeriodEnvVarStr);
            // Convert to millisecond from second
            cancelTaskGracePeriod *= 1000;

        } catch (Exception e) {
            cancelTaskGracePeriod = 0;
        }

        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: Cancel Task Grace Period = "
                + cancelTaskGracePeriod);

        // parse the coreID list to get the first allocate core
        // the core ids is like "0 1 2 5" if allocate 4 cores
        String CoreIds = Environment.getEnvironmentVariable(Constant.CoreIdsEnvVar);
        try {
            String[] cores = CoreIds.split(" ");
            portOffset = Integer.parseInt(cores[0]);
        } catch (Exception e) {
            ServiceContext.Logger.traceEvent(Level.WARNING,
                    "[HpcServiceHost]: Fail to get CoreId use default value.");
        }
        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: First Allocated CoreId = " + portOffset);

        // get the preemption switcher from the env var, the default value is
        // true.
        String preemption = Environment
                .getEnvironmentVariable(Constant.EnableMessageLevelPreemptionEnvVar);
        try {
            enableMessageLevelPreemption = Boolean.parseBoolean(preemption);
        } catch (Exception e) {
            this.enableMessageLevelPreemption = true;
        }
        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: EnableMessageLevelPreemption = "
                + enableMessageLevelPreemption);

        // get the enable backend security value from the env var, the default
        // value is false
        String enableSecurity = Environment.getEnvironmentVariable(Constant.EnableBackendSecurityEnvVar);
        try {
            isEnableBackendSecurity = Boolean.parseBoolean(enableSecurity);
        } catch (Exception e) {
            isEnableBackendSecurity = false;
        }
        ServiceContext.Logger.traceEvent(Level.INFO,
                "[HpcServiceHost]: EnableBackendSecurity = "
                + isEnableBackendSecurity);

        return ErrorCode.Success;
    }

    private void invokeInitializeControlBreakHandler() {
        this.shutdownThread = new ShutdownThread(this);
        Runtime.getRuntime().addShutdownHook(this.shutdownThread);
    }

    public void openDummyService() throws UnknownHostException {
        getEnvironmentVariables();
        RunAsDummy();
    }
}
