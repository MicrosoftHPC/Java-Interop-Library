//------------------------------------------------------------------------------
// <copyright file="CxfClientBase.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      CxfClientBase class
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;

/**
 * @author junsu
 * 
 */
class CxfClientBase implements CallbackHandler
{

    // the service proxy object
    Object serviceObj;

    // the user name
    String username;

    // the password
    String password;

    // the client object
    org.apache.cxf.endpoint.Client client;

    /**
     * Setup the common security settings for Http/https call to SOA services
     * 
     * @param serviceObj
     * @param info
     */
    void Initialize(String username, String password, Object serviceObj) {
        this.serviceObj = serviceObj;
        this.username = username;
        this.password = password;

        client = ClientProxy.getClient(serviceObj);
        org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

        if (this.username != null && this.password != null) {
            addWSSHeaders(cxfEndpoint);

            // skip SSL setup
            trustAll((HTTPConduit) client.getConduit());
        }

        // debug purpose
        cxfEndpoint.getInInterceptors().add(new LoggingInInterceptor());
        cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());
    }

    /**
     * Put the current username and password to the endpoint
     * 
     * @param cxfEndpoint
     */
    void addWSSHeaders(org.apache.cxf.endpoint.Endpoint cxfEndpoint) {
        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, this.username);
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, this);

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        cxfEndpoint.getOutInterceptors().add(wssOut);
    }

    /**
     * Setup the SSL policy to ignore the SSL and hostname verification
     * 
     * @param httpConduit
     */
    static void trustAll(HTTPConduit httpConduit) {
        try {
            TLSClientParameters tlsParams = new TLSClientParameters();
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs,
                        String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs,
                        String authType) {
                }
            } };
            tlsParams.setTrustManagers(trustAllCerts);
            tlsParams.setDisableCNCheck(true);
            httpConduit.setTlsClientParameters(tlsParams);
        } catch (NullPointerException e) {

        }
    }

    public void setTimeout(int timeout) {
        Client client = ClientProxy.getClient(serviceObj);

        setClientTimeout(client, timeout);
    }

    protected void setClientTimeout(Client client, int timeout) {
        boolean changed = false;

        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = http.getClient();

        if (httpClientPolicy.getConnectionTimeout() != timeout) {
            httpClientPolicy.setConnectionTimeout(timeout);
            changed = true;
        }
        if (httpClientPolicy.getReceiveTimeout() != timeout) {
            httpClientPolicy.setReceiveTimeout(timeout);
            changed = true;
        }

        if (changed)
            http.setClient(httpClientPolicy);
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
            if (pwcb.getIdentifier() == this.username)
                pwcb.setPassword(this.password);
            else
                throw new UnsupportedCallbackException(pwcb);
        }

    }

    public void destory() {
        if (this.client != null)
            this.client.destroy();
        this.client = null;
    }
}
