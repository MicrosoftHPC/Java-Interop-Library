//------------------------------------------------------------------------------
// <copyright file="BrokerClient.java" company="Microsoft" / >
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

import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import com.microsoft.hpc.BrokerClientStatus;

/**
 * Used to communicate with the broker to send and receive messages
 * 
 * @see #sendRequest(Object)
 * @see #endRequests()
 * @see #pullResponses(Class)
 */
public class BrokerClient<TContract extends Service>
{
    /**
     * Store the count of the sent messages
     */
    private volatile AtomicInteger sendCount = new AtomicInteger();

    private volatile AtomicInteger uncommitCount = new AtomicInteger();

    // private CallbackManager callbackManager
    // Callbacks are not supported in Java

    // private int defaultResponsesTimeout = 60 * 1000;
    // PullResponses uses a built in timeout of 1 minute

    private volatile boolean isBrokerDown = false;

    /**
     * Reference to Controller client. Java Changes: Changed from
     * BrokerControllerClient. Implemented as IBrokerController in Java
     */
    private CxfBrokerControllerClient controllerClient;

    /**
     * Reference to the Session object passed to the client
     */
    private SessionBase session;

    /**
     * The string of the clientid
     */
    private String clientId;

    /**
     * Stores the scheme
     */
    private TransportScheme scheme;

    /**
     * Default timeout when waiting for new requests to reach broker
     */
    private int timeoutThrottlingMS = 60 * 1000;

    // The following fields are only necessary for the java implementation.
    /**
     * Specifies the default timeout for EOM call
     */
    final public int DEFAULT_TIMEOUT = 60000;

    // / <summary>
    // / Default timeout when waiting for new requests to reach broker
    // / </summary>
    final int defaultSendTimeout = 60 * 1000; // Default to 1 min

    /**
     * The client proxy to communicate to broker endpoint
     */
    private CxfBrokerClient<TContract> client;

    /**
     * if we called endrequest
     */
    private boolean isEndRequest = false;

    /**
     * if this client is disposed
     */
    private boolean isDisposed = false;

    /**
     * Creates instances of the BrokerClient
     * 
     * @param session
     *            Session or DurableSession
     * @param service
     *            Service class to instantiate
     */
    public BrokerClient(SessionBase session, Class<TContract> service) {
        Utility.throwIfNull(session, "session");

        init(session, null, null, service);
    }

    /**
     * Creates instances of the BrokerClient
     * 
     * @param clientid
     *            String identity of the client
     * @param session
     *            Session or DurableSession
     * @param service
     *            Service class to instantiate
     */
    public BrokerClient(String clientid, SessionBase session,
            Class<TContract> service) {
        Utility.throwIfNull(session, "session");
        Utility.throwIfNullOrEmpty(clientid, "clientid");
        Utility.throwIfTooLong(clientid.length(), "clientid", 128,
                SR.v("ClientIdTooLong"));

        init(session, null, clientid, service);
    }

    /**
     * Creates instances of the BrokerClient
     * 
     * @param session
     *            Session or DurableSession
     * @param bindingConfigName
     *            Binding configuration to use to connect to broker
     * @param service
     *            Service class to instantiate
     */
    public BrokerClient(SessionBase session, String bindingConfigName,
            Class<TContract> service) {
        Utility.throwIfNull(session, "session");

        init(session, bindingConfigName, null, service);
    }

    /**
     * Creates instances of the BrokerClient
     * 
     * @param clientid
     *            String identity of the client
     * @param session
     *            Session or DurableSession
     * @param bindingConfigName
     *            Binding configuration to use to connect to the broker
     * @param service
     *            Service class to instantiate
     */
    public BrokerClient(String clientid, SessionBase session,
            String bindingConfigName, Class<TContract> service) {
        Utility.throwIfNull(session, "session");
        Utility.throwIfNullOrEmpty(clientid, "clientid");
        Utility.throwIfTooLong(clientid.length(), "clientid", 128,
                SR.v("ClientIdTooLong"));

        init(session, bindingConfigName, clientid, service);
    }

    /**
     * Initializes BrokerClient session
     * 
     * @param session
     *            Session or DurableSession
     * @param bindingConfigName
     *            Binding configuration to use to connect to the broker
     * @param _clientId
     *            String identity of the client
     * @param service
     *            Service class to instantiate
     * @throws Exception
     */
    private void init(SessionBase session, String bindingConfigName,
            String _clientId, Class<TContract> service) {
        // printSessionInfo(session.getInfo());
        if (_clientId == null) {
            clientId = "";
        } else {
            Utility.throwIfInvalidClientId(_clientId);
            clientId = _clientId;
        }

        this.session = session;

        
        // Try to get the scheme from session info
        this.scheme = TransportScheme.valueOf((session.getInfo()
                .getTransportScheme().get(0)));

        controllerClient = CreateControllerClient(session);

        // Set the scheme
        if (this.scheme != TransportScheme.Http &&
                this.scheme != TransportScheme.Custom) {
            throw new UnsupportedOperationException();
        }
        
        String epr = "";
        if(this.scheme == TransportScheme.Http) {
            epr = session.getInfo().getBrokerEpr().getValue().getString().get(1);
        } else if (this.scheme == TransportScheme.Custom) {
            epr = session.getInfo().getBrokerEpr().getValue().getString().get(2);
        }
        
        client = new CxfBrokerClient<TContract>(session._username,
                session._password, epr, service);

        // If serviceOperationTimeout is specified, derive default timeouts for
        // EndRequest
        if (this.session.getInfo().getServiceOperationTimeout() != null
                && this.session.getInfo().getServiceOperationTimeout() > 0) {
            this.timeoutThrottlingMS = this.session.getInfo()
                    .getServiceOperationTimeout();
        } else {
            this.timeoutThrottlingMS = Integer.MAX_VALUE;
        }
        client.setTimeout(this.timeoutThrottlingMS);

        sendCount.set(0);
        uncommitCount.set(0);
    }

    private CxfBrokerControllerClient CreateControllerClient(SessionBase session) {
        String controllerEpr = "";
        if(this.scheme == TransportScheme.Http) {
            controllerEpr = session.getInfo().getControllerEpr().getValue().getString().get(1);
        } else if (this.scheme == TransportScheme.Custom) {
            controllerEpr = session.getInfo().getControllerEpr().getValue().getString().get(2);
        }

        if (session.getInfo().isSecure()) {
            return new CxfBrokerControllerClient(session._username,
                    session._password, controllerEpr);
        } else {
            return new CxfBrokerControllerClient(null, null, controllerEpr);
        }
    }

    /**
     * Send typed message to broker
     * 
     * @param <TMessage>
     *            Typed message type
     * @param request
     *            Typed message to send
     * @throws SessionException
     * @throws TimeoutException
     * @see {@link HpcJava#createRequest(Class, Object...)}
     */
    public <TMessage> void sendRequest(TMessage request)
            throws SessionException, SocketTimeoutException {
        sendRequest(request, "");
    }

    /**
     * Send typed messages with user data to broker
     * 
     * @param <TMessage>
     *            Typed message type
     * @param request
     *            Typed message to send
     * @param userData
     *            User data (will be converted to String because of Java
     *            limitations)
     * @throws SessionException
     * @throws TimeoutException
     * @see {@link HpcJava#createRequest(Class, Object...)}
     */
    public void sendRequest(Object request, Object userData)
            throws SessionException, SocketTimeoutException {
        sendRequest(request, userData, defaultSendTimeout);
    }

    /**
     * Send typed messages with user data to broker
     * 
     * @param <TMessage>
     *            Typed message type
     * @param request
     *            Typed message to send
     * @param userData
     *            User data (will be converted to String because of Java
     *            limitations)
     * @param timeout
     *            send timeout
     * @throws SessionException
     * @throws TimeoutException
     * @see {@link HpcJava#createRequest(Class, Object...)}
     */
    public void sendRequest(Object request, Object userData, int timeout)
            throws SessionException, SocketTimeoutException {
        Utility.throwIfNull(request, "request");
        Utility.throwIfNull(userData, "userData");
        if (timeout < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        if (isEndRequest)
            throw new IllegalStateException();
        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        try {
            client.SendMessage(request, this.clientId, userData, timeout);
            sendCount.incrementAndGet();
            uncommitCount.incrementAndGet();
        } catch (WebServiceException we) {
            if (we.getCause().getClass() == SocketTimeoutException.class) {
                throw new SocketTimeoutException(SR.v("ClientTimeout"));
            } else {
                throw new SessionException(we);
            }
        }
    }

    /**
     * Send typed messages with user data to broker
     * 
     * @param <TMessage>
     *            Typed message type
     * @param request
     *            Typed message to send
     * @param userData
     *            User data (will be converted to String because of Java
     *            limitations)
     * @param action
     *            Action of typed message if type is ambiguous
     * @throws SessionException
     * @throws TimeoutException
     * @see {@link HpcJava#createRequest(Class, Object...)}
     */
    public void sendRequest(Object request, Object userData, String action)
            throws SessionException, SocketTimeoutException {
        sendRequest(request, userData, action, defaultSendTimeout);
    }

    /**
     * Send typed messages with user data to broker
     * 
     * @param <TMessage>
     *            Typed message type
     * @param request
     *            Typed message to send
     * @param userData
     *            User data (will be converted to String because of Java
     *            limitations)
     * @param action
     *            Action of typed message if type is ambiguous
     * @param timeout
     *            send timeout
     * @throws SessionException
     * @throws TimeoutException
     * @see {@link HpcJava#createRequest(Class, Object...)}
     */
    public void sendRequest(Object request, Object userData, String action,
            int timeout) throws SessionException, SocketTimeoutException {
        Utility.throwIfNull(request, "request");
        Utility.throwIfNull(userData, "userData");
        Utility.throwIfNull(action, "action");
        if (timeout < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        if (isEndRequest)
            throw new IllegalStateException();
        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        try {
            client.SendMessage(request, this.clientId, userData, action,
                    timeout);
            sendCount.incrementAndGet();
            uncommitCount.incrementAndGet();
        } catch (WebServiceException we) {
            if (we.getCause().getClass() == SocketTimeoutException.class) {
                throw new SocketTimeoutException(SR.v("ClientTimeout"));
            } else {
                throw new SessionException(we);
            }
        }
    }

    /**
     * Get the response from the server. the function will return an iterable
     * instance which can be used to do for each.
     * 
     * the iterator is able to move next until it hits end of message flag.
     * 
     * @param <TMessage>
     * @param message
     * @return
     * @throws SessionException
     * 
     */
    public <TMessage> Iterable<BrokerResponse<TMessage>> getResponses(
            Class<TMessage> message) throws SessionException {
        return getResponses(message, 0);
    }

    /**
     * Get the response from the server. the function will return an iterable
     * instance which can be used to do for each.
     * 
     * the iterator is able to move next until it hits end of message flag.
     * 
     * @param <TMessage>
     * @param message
     * @return
     * @throws SessionException
     * 
     */
    public <TMessage> Iterable<BrokerResponse<TMessage>> getResponses(
            Class<TMessage> message, int timeoutMilliseconds)
            throws SessionException {
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        return new ResponseList<BrokerResponse<TMessage>>(message,
                CreateControllerClient(session),
                this.client.getRequestAction(message), this.clientId,
                timeoutMilliseconds);
    }

    /**
     * Get the response from the server. the function will return an iterable
     * instance which can be used to do for each.
     * 
     * the iterator is able to move next until it hits end of message flag.
     * 
     * @param <TMessage>
     * @param message
     * @return
     * @throws SessionException
     * 
     */
    public Iterable<BrokerResponse<Object>> getResponses()
            throws SessionException {
        return getResponses(0);
    }

    /**
     * Get the response from the server. the function will return an iterable
     * instance which can be used to do for each.
     * 
     * the iterator is able to move next until it hits end of message flag.
     * 
     * @param <TMessage>
     * @param message
     * @return
     * @throws SessionException
     * 
     */
    public Iterable<BrokerResponse<Object>> getResponses(int timeoutMilliseconds)
            throws SessionException {
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }
        
        Map<String, Class<?>> t = this.client.getResponseTypeMapping();
        return new ResponseList<BrokerResponse<Object>>(t,
                CreateControllerClient(session), this.clientId,
                timeoutMilliseconds);
    }

    /**
     * Set the response handler to handle the response when it is arrived
     * 
     * @param <TMessage>
     * @param message
     * @param responselistener
     */
    public <TMessage> void setResponseHandler(final Class<TMessage> message,
            final ResponseListener<TMessage> responselistener) {
        Utility.throwIfNull(responselistener, "responselistener");
        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        Runnable runnable = new Runnable() {
            public void run() {
                Iterable<BrokerResponse<TMessage>> responses;
                try {
                    responses = getResponses(message);
                    for (BrokerResponse<TMessage> r : responses) {
                        responselistener.responseReturned(r);
                    }
                    responselistener.endOfMessage();
                } catch (SessionException e) {
                    responselistener.raiseError(e);
                } catch (RuntimeException e) {
                    if (e.getCause() == null)
                        responselistener.raiseError(e);
                    else
                        responselistener.raiseError((Exception)e.getCause());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * Set the response handler to handle the response when it is arrived
     * 
     * @param <TMessage>
     * @param message
     * @param responselistener
     */
    public <TMessage> void setResponseHandler(final Class<TMessage> message,
            final ResponseListener<TMessage> responselistener,
            final int timeoutMilliseconds) {
        Utility.throwIfNull(responselistener, "responselistener");
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        Runnable runnable = new Runnable() {
            public void run() {
                Iterable<BrokerResponse<TMessage>> responses;
                try {
                    responses = getResponses(message, timeoutMilliseconds);
                    for (BrokerResponse<TMessage> r : responses) {
                        responselistener.responseReturned(r);
                    }
                    responselistener.endOfMessage();
                } catch (SessionException e) {
                    responselistener.raiseError(e);
                } catch (RuntimeException e) {
                    if (e.getCause() == null)
                        responselistener.raiseError(e);
                    else
                        responselistener.raiseError((Exception) e.getCause());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * Set the response handler to handle the response when it is arrived
     * 
     * @param <TMessage>
     * @param message
     * @param responselistener
     */
    public void setResponseHandler(
            final ResponseListener<Object> responselistener) {
        Utility.throwIfNull(responselistener, "responselistener");
        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        Runnable runnable = new Runnable() {
            public void run() {
                Iterable<BrokerResponse<Object>> responses;
                try {
                    responses = getResponses();
                    for (BrokerResponse<Object> r : responses) {
                        responselistener.responseReturned(r);
                    }
                    responselistener.endOfMessage();
                } catch (SessionException e) {
                    responselistener.raiseError(e);
                } catch (RuntimeException e) {
                    if (e.getCause() == null)
                        responselistener.raiseError(e);
                    else
                        responselistener.raiseError((Exception) e.getCause());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * Set the response handler to handle the response when it is arrived
     * 
     * @param <TMessage>
     * @param message
     * @param responselistener
     */
    public void setResponseHandler(
            final ResponseListener<Object> responselistener,
            final int timeoutMilliseconds) {
        Utility.throwIfNull(responselistener, "responselistener");
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        Runnable runnable = new Runnable() {
            public void run() {
                Iterable<BrokerResponse<Object>> responses;
                try {
                    responses = getResponses(timeoutMilliseconds);
                    for (BrokerResponse<Object> r : responses) {
                        responselistener.responseReturned(r);
                    }
                    responselistener.endOfMessage();
                } catch (SessionException e) {
                    responselistener.raiseError(e);
                } catch (RuntimeException e) {
                    if (e.getCause() == null)
                        responselistener.raiseError(e);
                    else
                        responselistener.raiseError((Exception) e.getCause());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * Handles cleanup of the BrokerClient instance. Semi-Equivalent to Dispose
     * in C#
     */
    protected void finalize() {
        dispose();
    }

    /**
     * Handles cleanup of the BrokerClient instance
     */
    protected void dispose() {
        if (this.isDisposed)
            return;

        if (this.controllerClient != null) {
            this.controllerClient.destory();
            this.controllerClient = null;
        }
        this.session = null;
        this.isDisposed = true;
    }

    /**
     * Commits requests to broker's store.
     * 
     * @param timeoutMilliseconds
     *            timeout before exception
     * @throws TimeoutException
     * @throws SessionException
     * @throws InvalidOperationException
     */
    public void endRequests(int timeoutMilliseconds)
            throws SocketTimeoutException, SessionException {
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }
        if (this.sendCount.get() == 0) {
            throw new IllegalStateException(SR.v("InvalidEndRequestsCount"));
        }
        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        // Pick the larger of the two timeouts to ensure operationTimeout is
        // long enough
        int largerTimeout = this.timeoutThrottlingMS > timeoutMilliseconds ? this.timeoutThrottlingMS
                : timeoutMilliseconds;

        int count = uncommitCount.getAndSet(0);
        try {
            if (timeoutMilliseconds == 0) {
                controllerClient.setTimeout(0);
                timeoutMilliseconds = Integer.MAX_VALUE;
            } else
                controllerClient.setTimeout(largerTimeout);

            controllerClient.endRequests(count, clientId, largerTimeout,
                    timeoutMilliseconds);
        } catch (WebServiceException we) {
            if (we.getCause().getClass() == SocketTimeoutException.class) {
                throw new SocketTimeoutException(SR.v(
                        "ConnectSessionLauncherTimeout", timeoutMilliseconds));
            } else {
                throw new SessionException(we);
            }
        }
        isEndRequest = true;
    }

    /**
     * Flush requests to broker's store.
     * 
     * @throws SessionException
     * @throws TimeoutException
     * @throws InvalidOperationException
     */
    public void flush() throws SocketTimeoutException, SessionException {
        flush(DEFAULT_TIMEOUT);
    }

    /**
     * Flush requests to broker's store.
     * 
     * @param timeoutMilliseconds
     *            timeout before exception
     * @throws TimeoutException
     * @throws SessionException
     */
    public void flush(int timeoutMilliseconds) throws SocketTimeoutException,
            SessionException {
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }
        if (isDisposed) {
            throw new IllegalStateException(SR.v("ClientAlreadyClosed"));
        }

        if (this.sendCount.get() == 0) {
            return;
        }

        // Pick the larger of the two timeouts to ensure operationTimeout is
        // long enough
        int largerTimeout = this.timeoutThrottlingMS > timeoutMilliseconds ? this.timeoutThrottlingMS
                : timeoutMilliseconds;

        int count = uncommitCount.getAndSet(0);
        try {
            if (timeoutMilliseconds == 0) {
                controllerClient.setTimeout(0);
                timeoutMilliseconds = -1;
            } else
                controllerClient.setTimeout(largerTimeout);

            controllerClient.flush(count, clientId, largerTimeout,
                    timeoutMilliseconds);
        } catch (WebServiceException we) {
            if (we.getCause().getClass() == SocketTimeoutException.class) {
                throw new SocketTimeoutException(SR.v(
                        "ConnectSessionLauncherTimeout", timeoutMilliseconds));
            } else {
                throw new SessionException(we);
            }
        }
    }

    /**
     * Commits requests to broker's store. Default timeout is defined above.
     * 
     * @throws SessionException
     * @throws TimeoutException
     * @throws InvalidOperationException
     */
    public void endRequests() throws SocketTimeoutException, SessionException {
        endRequests(DEFAULT_TIMEOUT);
    }

    /**
     * Closes broker connections
     * 
     * @throws SessionException
     * @throws TimeoutException
     */
    public void close() throws SocketTimeoutException, SessionException {
        close(false, Constant.PurgeTimeoutMS);
    }

    /**
     * Close the connection and purge the results to the server
     * 
     * @param purge
     *            if true, the result will be removed in the broker
     * @throws SessionException
     * @throws TimeoutException
     */
    public void close(Boolean purge) throws SocketTimeoutException,
            SessionException {
        close(purge, Constant.PurgeTimeoutMS);
    }

    /**
     * Close the connection and purge the results in the server
     * 
     * @param purge
     *            if true, the result will be removed in the broker
     * @param timeoutMilliseconds
     *            the timeout for the response if purge
     * @throws TimeoutException
     * @throws SessionException
     */
    public void close(Boolean purge, int timeoutMilliseconds)
            throws SocketTimeoutException, SessionException {
        if (timeoutMilliseconds < 0) {
            throw new IllegalArgumentException(SR.v("InvalidTimeout"));
        }

        if (isDisposed) {
            return;
        }

        if (purge) {
            try {
                controllerClient.setTimeout(timeoutMilliseconds);
                controllerClient.purge(clientId);
            } catch (WebServiceException we) {
                if (we.getCause().getClass() == SocketTimeoutException.class) {
                    throw new SocketTimeoutException(SR.v(
                            "ConnectSessionLauncherTimeout",
                            timeoutMilliseconds));
                } else {
                    throw new SessionException(we);
                }
            }
        }
        dispose();
    }

    public BrokerClientStatus getStatus() throws SessionException {
        return controllerClient.GetStatus(this.clientId);
    }

    public int getRequestCount() throws SessionException {
        return controllerClient.getRequestsCount(this.clientId);
    }
}
