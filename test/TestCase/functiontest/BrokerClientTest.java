/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.*;

import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.tempuri.AITestLibService;
import org.tempuri.Echo;
import org.tempuri.EchoResponse;
import org.tempuri.GenerateLoad;
import org.tempuri.GenerateLoadResponse;

import org.datacontract.schemas._2004._07.services.ComputerInfo;
import org.datacontract.schemas._2004._07.services.StatisticInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.microsoft.hpc.BrokerClientStatus;
import com.microsoft.hpc.scheduler.session.*;

/**
 * @author yutongs
 * 
 */
public class BrokerClientTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("BrokerClientTest");
        logger = new Logger(true, true, "BrokerClientTest");

        //HpcJava.setUsername(config.UserName);
        //HpcJava.setPassword(config.Password);
        
        // init sessions and clients
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSessionIdleTimeout(Integer.MAX_VALUE);
        info.setClientIdleTimeout(Integer.MAX_VALUE);

        dSession = DurableSession.createSession(info);
        iSession = Session.createSession(info);

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dSession.close();
        iSession.close();
        logger.close();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dClient = new BrokerClient<AITestLibService>(UUID.randomUUID()
                .toString(), dSession, AITestLibService.class);
        iClient = new BrokerClient<AITestLibService>(UUID.randomUUID()
                .toString(), iSession, AITestLibService.class);

        logger.Start("BrokerClientTest");

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (dClient != null)
            dClient.close(true);

        if (iClient != null)
            iClient.close(true);

        logger.End("BrokerClientTest");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#finalize()}.
     */
    @Ignore
    public final void testFinalize() {
        // we do not test finalize()/ dispose() here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#BrokerClient(com.microsoft.hpc.scheduler.session.SessionBase, java.lang.Class)}
     * .
     */
    @Test
    public final void testBrokerClientSessionBaseClassOfTContract() {
        BrokerClient<AITestLibService> client = null;
        // function
        client = new BrokerClient<AITestLibService>(dSession,
                AITestLibService.class);
        logger.assertNotNull("dSession client", client);
        client = null;
        client = new BrokerClient<AITestLibService>(iSession,
                AITestLibService.class);
        logger.assertNotNull("dSession client", client);
        client = null;

        // boundary

        try {
            client = new BrokerClient<AITestLibService>(null,
                    AITestLibService.class);
            logger.Error("Expected exception is not thrown.");
        } catch (NullPointerException e) {
            logger.Info("Expected NullPointerException : %s", e.getMessage());
        } catch (Throwable e) {
            logger.Error("UE : %s", e.getMessage());
        }

        try {
            client = new BrokerClient<AITestLibService>(dSession, null);
            logger.Error("Expected exception is not thrown.");
        } catch (Throwable e) {
            logger.Info("Expected exception : %s", e.getMessage());
        }

        try {
            client = new BrokerClient<AITestLibService>(null, null);
            logger.Error("Expected exception is not thrown.");
        } catch (NullPointerException e) {
            logger.Info("Expected NullPointerException : %s", e.getMessage());
        } catch (Throwable e) {
            logger.Error("UE : %s", e.getMessage());
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#BrokerClient(java.lang.String, com.microsoft.hpc.scheduler.session.SessionBase, java.lang.Class)}
     * .
     */
    @Test
    public final void testBrokerClientStringSessionBaseClassOfTContract() {
        BrokerClient<AITestLibService> client = null;

        ArrayList<String> validStrings = new ArrayList<String>();
        validStrings.add("1");
        validStrings.add("a");
        validStrings.add("a1");
        validStrings.add("abcxyz123");
        validStrings.add(" "); // a space is valid
        validStrings.add("0aZAz");
        validStrings.add("{valid_string-id}");
        validStrings.add("abc xyz fine");

        ArrayList<String> invalidStrings = new ArrayList<String>();
        invalidStrings.add(null);
        invalidStrings.add("");
        invalidStrings.add("?&*^&%$#)_[]{}");
        invalidStrings.add(".");
        invalidStrings.add("/\\");
        invalidStrings.add("abc+xyz");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 129; i++) {
            sb.append('a');
        }
        invalidStrings.add(sb.toString());
        for (int i = 0; i < 1290; i++) {
            sb.append('a');
        }
        invalidStrings.add(sb.toString());
        Echo request = Util.generateEchoRequest();

        // function
        try {
            for (String s : validStrings) {
                client = new BrokerClient<AITestLibService>(s, dSession,
                        AITestLibService.class);
                client.sendRequest(request);
                client.close(true);
            }
        } catch (Throwable e) {
            logger.Error("Exception %s", e.toString());
            e.printStackTrace();
        }

        // boundary
        for (String s : invalidStrings) {

            try {
                client = new BrokerClient<AITestLibService>(s, dSession,
                        AITestLibService.class);
                client.sendRequest(request);
                client.close(true);
                logger.Error("String %s should be invalid", s);

            } catch (Throwable e) {
                logger.Info("Expected Exception %s", e.toString());
                e.printStackTrace();
            }
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#BrokerClient(com.microsoft.hpc.scheduler.session.SessionBase, java.lang.String, java.lang.Class)}
     * .
     */
    @Ignore
    public final void testBrokerClientSessionBaseStringClassOfTContract() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#BrokerClient(java.lang.String, com.microsoft.hpc.scheduler.session.SessionBase, java.lang.String, java.lang.Class)}
     * .
     */
    @Ignore
    public final void testBrokerClientStringSessionBaseStringClassOfTContract() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#sendRequest(java.lang.Object)}
     * .
     */
    @Test
    public final void testSendRequestTMessage() {
        Echo request = Util.generateEchoRequest();
        // function
        try {
            iClient.sendRequest(request);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(request);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            iClient.sendRequest(null);
            logger.Error("Excetion is expected. i null");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(null);
            logger.Error("Excetion is expected. d null");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(new Object());
            logger.Error("Excetion is expected. i new");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(new Object());
            logger.Error("Excetion is expected. d new");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(1);
            logger.Error("Excetion is expected. i int");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest("message string");
            logger.Error("Excetion is expected.d string");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(true);
            logger.Error("Excetion is expected. i boolean ");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(request.getClass());
            logger.Error("Excetion is expected. i class");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#sendRequest(java.lang.Object, java.lang.Object)}
     * .
     */
    @Test
    public final void testSendRequestObjectObject() {
        Echo request = Util.generateEchoRequest();
        // function
        try {
            iClient.sendRequest(request, 1);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(request, "data");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            iClient.sendRequest(null, 1);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(null, "datat");
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(new Object(), 1);
            logger.Error("Excetion is expected.");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(new Object(), "data");
            logger.Error("Excetion is expected.");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(request, null);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        // following userdata objects are ok
        /*
         * try { dClient.sendRequest(request, new Object());
         * logger.Error("Excetion is expected."); } catch (SessionException e) {
         * logger.Info("Expected exception when sending requests %s",
         * e.toString()); e.printStackTrace(); }
         * 
         * try { iClient.sendRequest(request, request);
         * logger.Error("Excetion is expected."); } catch (SessionException e) {
         * logger.Info("Expected exception when sending requests %s",
         * e.toString()); e.printStackTrace(); }
         * 
         * try { dClient.sendRequest(request, true);
         * logger.Error("Excetion is expected."); } catch (SessionException e) {
         * logger.Info("Expected exception when sending requests %s",
         * e.toString()); e.printStackTrace(); }
         */

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#sendRequest(java.lang.Object, java.lang.Object, java.lang.String)}
     * .
     */
    @Test
    public final void testSendRequestObjectObjectString() {

        // functional
        Echo request = Util.generateEchoRequest();

        try {
            iClient.sendRequest(request, 1, "http://tempuri.org/IEchoSvc/Echo");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(request, "data",
                    "http://tempuri.org/IEchoSvc/Echo");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // TODO

        // boundary

        /*
         * try { iClient.sendRequest(request, 1, "ActionString");
         * logger.Error("Excetion is expected."); } catch (SessionException e) {
         * logger.Info("Expected exception when sending requests %s",
         * e.toString()); e.printStackTrace(); }
         * 
         * try { dClient.sendRequest(request, "datat", "");
         * logger.Error("Excetion is expected."); } catch (SessionException e) {
         * logger.Info("Expected exception when sending requests %s",
         * e.toString()); e.printStackTrace(); }
         */

        try {
            iClient.sendRequest(request, 1, null);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        /*
         * try { dClient.sendRequest(request, "datat", "&^(&^%$#{}?_?#@*");
         * logger.Error("Excetion is expected."); } catch (SessionException e) {
         * logger.Info("Expected exception when sending requests %s",
         * e.toString()); e.printStackTrace(); }
         */

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#getResponses(java.lang.Class)}
     * .
     */
    @Test
    public final void testGetResponses() {
        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");
            dClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<EchoResponse>> responses = dClient
                    .getResponses(EchoResponse.class);
            Iterator<BrokerResponse<EchoResponse>> iter = responses.iterator();
            BrokerResponse<EchoResponse> response = iter.next();
            ComputerInfo reply;
            try {
                reply = response.getResult().getEchoResult().getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            Iterable<BrokerResponse<EchoResponse>> responses = dClient
                    .getResponses(null);
            logger.Error("Expected exception not thrown");
            Iterator<BrokerResponse<EchoResponse>> iter = responses.iterator();
            BrokerResponse<EchoResponse> response = iter.next();
            ComputerInfo reply;
            try {
                reply = response.getResult().getEchoResult().getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            logger.Info("Expected excpetion when getting responses %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when getting responses %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(java.lang.Class, com.microsoft.hpc.scheduler.session.ResponseListener)}
     * .
     */
    @Test
    public final void testSetResponseHandler() {

        // functional
        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(EchoResponse.class,
                new ResponseListener<EchoResponse>() {

                    private int count = 0;

                    @Override
                    public void responseReturned(
                            BrokerResponse<EchoResponse> response) {
                        count++;
                        ComputerInfo reply = null;
                        try {
                            reply = response.getResult().getEchoResult()
                                    .getValue();
                        } catch (SOAPFaultException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        } catch (SOAPException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        }
                        logger.Info("\tReceived response for request %s: %s",
                                response.getUserData(), reply);
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(request.getRefID()) == 0);
                    }

                    @Override
                    public void endOfMessage() {
                        logger.Info("Done retrieving %d responses", count);
                        l.countDown();

                    }

                    @Override
                    public void raiseError(Exception e) {
                        logger.Error("Error in process request %s",
                                e.toString());
                        e.printStackTrace();
                    }

                });

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(2, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(java.lang.Class, com.microsoft.hpc.scheduler.session.ResponseListener)}
     * .
     */
    @Test
    public final void testSetResponseHandler_E1() {

        // boundary
        // when the responsehandler is null
        Echo request = Util.generateEchoRequest();

        try {
            iClient.setResponseHandler(EchoResponse.class, null);
            logger.Error("EE is not thrown.");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException is expected.");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#dispose()}.
     */
    @Ignore
    public final void testDispose() {
        // we do not test dispose here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#endRequests(int)}
     * .
     */
    @Test
    public final void testEndRequestsInt() {
        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.endRequests(3000);
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when end requests %s", e.toString());
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Excpetion when end requests %s", e.toString());
            e.printStackTrace();
        } // BUG this cannot throw InvalidOperationException

        // boundary

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#endRequests(int)}
     * .
     */
    @Test
    public final void testEndRequestsInt_1() {
        // functional
        Echo request = Util.generateEchoRequest();
        try {
            dClient.sendRequest(request, "data");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.endRequests(0);
        } catch (Throwable e) {
            logger.Error("UE for 0 timeout %s", e.toString());
            e.printStackTrace();
        }

        // boundary

    }

    @Test
    public final void testEndRequestsInt_0() {
        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.endRequests(-1);
            logger.Error("EE is not thrown. -1");
        } catch (Throwable e) {
            logger.Info("Expected Exception %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.endRequests(Integer.MIN_VALUE);
            logger.Error("EE is not thrown. min");
        } catch (Throwable e) {
            logger.Info("Expected Exception %s", e.toString());
            e.printStackTrace();
        }

        /*
         * try { dClient.endRequests(1); //endrequest use the larger
         * timeoutThrottlingMS which is default 60 seconds
         * logger.Error("EE is not thrown. 1"); } catch (Throwable e) {
         * logger.Info("Expected Exception %s", e.toString());
         * e.printStackTrace(); }
         */

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#flush()}.
     */
    @Test
    public final void testFlush() {

        // functional
        Echo request = Util.generateEchoRequest();

        try {
            for (int i = 0; i < config.NbOfCalls; i++) {
                dClient.sendRequest(request, "data");
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            logger.Info("start to flush");
            dClient.flush(); // BUG flush should throw SessionException or
                             // InvalidOperationException
            dClient.endRequests();
        } catch (Throwable e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // get responses
        try {
            logger.Info("start go get response");
            Iterable<BrokerResponse<EchoResponse>> responses = dClient
                    .getResponses(EchoResponse.class);
            Iterator<BrokerResponse<EchoResponse>> iter = responses.iterator();
            BrokerResponse<EchoResponse> response = iter.next();
            ComputerInfo reply;
            try {
                reply = response.getResult().getEchoResult().getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply.getRefID().toString());
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        // flush without any requests sent
        /*
         * try { dClient.flush(); // BUG flush should throw SessionException or
         * // InvalidOperationException
         * 
         * } catch (Throwable e) {
         * logger.Error("Excpetion when sending requests %s", e.toString());
         * e.printStackTrace(); }
         */

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#flush()}.
     */
    @Test(timeout = 180000)
    public final void testFlush_1() {

        // functional
        Echo request = Util.generateEchoRequest();

        try {
            for (int i = 0; i < 100; i++) {
                try {
                    dClient.sendRequest(request, "data");
                } catch (SocketTimeoutException e) {
                    logger.Error("Excpetion when sending requests %s", e.toString());
                    e.printStackTrace();
                }
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.flush(); // BUG flush should throw SessionException or
                             // InvalidOperationException
            dClient.endRequests();
        } catch (Throwable e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // get responses
        try {
            for (BrokerResponse<EchoResponse> response : dClient
                    .getResponses(EchoResponse.class)) {
                ComputerInfo reply;
                try {
                    reply = response.getResult().getEchoResult().getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply);
                    logger.assertTrue("check response", reply.getRefID()
                            .compareTo(request.getRefID()) == 0);
                } catch (SOAPFaultException e) {
                    logger.Error("Exception in getResponse %s", e.toString());
                    e.printStackTrace();
                } catch (SOAPException e) {
                    logger.Error("Exception in getResponse %s", e.toString());
                    e.printStackTrace();
                }
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        // flush without any requests sent
        /*
         * try { dClient.flush(); // BUG flush should throw SessionException or
         * // InvalidOperationException
         * 
         * } catch (Throwable e) {
         * logger.Error("Excpetion when sending requests %s", e.toString());
         * e.printStackTrace(); }
         */

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#flush()}.
     */
    @Test(timeout = 180000)
    public final void testFlush_Bug1() {

        // functional
        final Echo request = Util.generateEchoRequest();

        try {
            for (int i = 0; i < 10; i++) {
                try {
                    dClient.sendRequest(request, "data");
                } catch (SocketTimeoutException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.flush();
            //dClient.endRequests();
        } catch (Throwable e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        final CountDownLatch l = new CountDownLatch(1);
        dClient.setResponseHandler(EchoResponse.class,
                new ResponseListener<EchoResponse>() {

                    @Override
                    public void responseReturned(
                            BrokerResponse<EchoResponse> response) {

                        ComputerInfo reply = null;
                        try {
                            reply = response.getResult().getEchoResult()
                                    .getValue();
                        } catch (SOAPFaultException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        } catch (SOAPException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        }
                        logger.Info("\tReceived response for request %s: %d",
                                response.getUserData(), reply.getRefID());
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(request.getRefID()) == 0);

                    }

                    @Override
                    public void endOfMessage() {
                        logger.Info("Done retrieving %d responses",
                                config.NbOfCalls);
                        l.countDown();

                    }

                    @Override
                    public void raiseError(Exception e) {
                        logger.Error("Error in process request %s",
                                e.toString());

                    }
                });

        try {
            l.await(2, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // boundary

        // flush without any requests sent
        /*
         * try { dClient.flush(); // BUG flush should throw SessionException or
         * // InvalidOperationException
         * 
         * } catch (Throwable e) {
         * logger.Error("Excpetion when sending requests %s", e.toString());
         * e.printStackTrace(); }
         */

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#flush(int)}.
     */
    @Test
    public final void testFlushInt() {

        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");

        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.flush(3000); // BUG flush should throw SessionException or
                                 // InvalidOperationException
        } catch (Throwable e) {
            logger.Error("Excpetion when flushing requests %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            dClient.flush(-1);
            logger.Error("Expected exception is not thrown");

        } catch (Throwable e) {
            logger.Info("Excpetion when flushing requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.flush(1);
            logger.Error("Expected exception is not thrown");

        } catch (Throwable e) {
            logger.Info("Excpetion when flushing requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.flush(0);
            logger.Error("Expected exception is not thrown");

        } catch (Throwable e) {
            logger.Info("Excpetion when flushing requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.flush(Integer.MIN_VALUE);
            logger.Error("Expected exception is not thrown");

        } catch (Throwable e) {
            logger.Info("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#endRequests()}.
     */
    @Test
    public final void testEndRequests() {

        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.endRequests(); // BUG this cannot throw
                                   // InvalidOperationException
        } catch (Throwable e) {
            logger.Error("Excpetion when end requests %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#endRequests()}.
     */
    @Test
    public final void testEndRequests_1() {

        // boundary
        Echo request = Util.generateEchoRequest();

        // endrequest before any requests sent
        try {
            dClient.endRequests(); // BUG this cannot throw
                                   // InvalidOperationException
        } catch (IllegalStateException e) {
            logger.Info("EE when end requests %s", e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when end requests %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#close()}.
     */
    @Test
    public final void testClose() {
        // functional
    	// bug 11821

        try {
            dClient.close();
        } catch (Throwable e) {
            logger.Error("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        // close the client the second time
        try {
            dClient.close();
            logger.Info("Exception should not be thrown.");
        } catch (Throwable e) {
            logger.Error("UE when closing the client %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#close(java.lang.Boolean)}
     * .
     */
    @Test
    public final void testCloseBoolean() {
        // functional
    	// bug 11821

        try {
            dClient.close(true);
        } catch (IllegalStateException e) {
        	logger.Info("IllegalStateException is thrown. %s", e.toString());
        	e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        // close the client the second time
        try {
            dClient.close(true);
            logger.Info("Exception should not be thrown.");
        } catch (Throwable e) {
            logger.Error("UE when closing the client %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#close(java.lang.Boolean, int)}
     * .
     */
    @Test
    public final void testCloseBooleanInt() {
        // functional

        try {
            dClient.close(false, 5000);
            dClient = null;
        } catch (Throwable e) {
            logger.Error("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#close(java.lang.Boolean, int)}
     * .
     */
    @Test
    public final void testCloseBooleanInt_1() {
        // boundary
    	// bug 11821

        try {
            dClient.close(false, -1);
            logger.Error("EException is not thrown");
        } catch (Throwable e) {
            logger.Info("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.close(true, Integer.MIN_VALUE);
            logger.Error("EException is not thrown");
        } catch (Throwable e) {
            logger.Info("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }

       

        try {
            dClient.close(true, 1);
            logger.Error("EException is not thrown");
        } catch (Throwable e) {
            logger.Info("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }
        
        try {
            dClient.close(true, 0);
            
        } catch (Throwable e) {
            logger.Error("Excpetion when close the client %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#sendRequest(java.lang.Object, java.lang.Object, int)}
     * .
     */
    @Test
    public final void testSendRequestObjectObjectInt() {
        Echo request = Util.generateEchoRequest();
        // function
        try {
            iClient.sendRequest(request, 1, 5000);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(request, "data", Integer.MAX_VALUE);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(request, 1, 0);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            iClient.sendRequest(request, 1, -1);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(request, "datat", Integer.MIN_VALUE);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(request, 1, 1);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(request, 1, -5000);
            logger.Error("Excetion is expected.");
        } catch (SessionException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

        try {
            iClient.sendRequest(request, null, 1);
            logger.Error("Excetion is expected.");
        } catch (NullPointerException e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Info("Expected exception when sending requests %s",
                    e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#sendRequest(java.lang.Object, java.lang.Object, java.lang.String, int)}
     * .
     */
    @Test
    public final void testSendRequestObjectObjectStringInt() {
        // functional
        Echo request = Util.generateEchoRequest();

        try {
            iClient.sendRequest(request, 1, "http://tempuri.org/IEchoSvc/Echo",
                    5000);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            dClient.sendRequest(request, "data",
                    "http://tempuri.org/IEchoSvc/Echo", 0);
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        // boundary
    }
    
    
    /**
     * Test method for GetRequestCount
     */
    @Test
    public final void testGetRequestCount() {
        
    	try {
			logger.Info(dClient.getRequestCount());
			logger.Info(iClient.getRequestCount());
			
			logger.assertEqual("dClient request count", dClient.getRequestCount(), 0);
			logger.assertEqual("iClient request count", iClient.getRequestCount(),0);
			
		
    	
		 int refId = Util.generateRandomInteger();
         for (int i = 0; i < 100; i++) {
             Echo request = Util.generateEchoRequest(refId);
             iClient.sendRequest(request, i);
			
         }
         logger.Info(iClient.getRequestCount());
         // logger.assertEqual("iClient request count", iClient.getRequestCount(), 100);
         // getRequestCount - the name is confusing, what does it stand for?
         
         logger.Info("Call endRequests()...");
       
			iClient.endRequests();
		 logger.Info(iClient.getRequestCount());
		 logger.assertEqual("iClient request count", iClient.getRequestCount(), 100);
         
		 logger.Info("Retrieving responses...");
         for (BrokerResponse<EchoResponse> response : iClient
                 .getResponses(EchoResponse.class)) {
             try {
                 ComputerInfo reply = response.getResult().getEchoResult()
                         .getValue();
                 logger.Info(
                         "\tReceived response for request %s: refId: %d",
                         response.getUserData(), reply.getRefID());
                 logger.assertTrue("check response",
                         reply.getRefID() == refId);
             } catch (Throwable ex) {
                 logger.Error("Error in process request %s", ex.toString());

             }

         }
         
         logger.Info(iClient.getRequestCount());
		 logger.assertEqual("iClient request count", iClient.getRequestCount(), 100);
         logger.Info("Done retrieving %d responses", 100);
         
		 		
    	} catch (SessionException e) {
			logger.Error("UE is thrown %s",e.toString());
			e.printStackTrace();
    	} catch (SocketTimeoutException e) {
    		logger.Error("UE is thrown %s",e.toString());
			e.printStackTrace();
		}
		
		
    	
    	
    	
    }

    /**
     * Test method for GetStatus
     */
    @Test
    public final void testGetStatus() {
        
    	try {
			logger.Info(dClient.getStatus().toString());
			logger.Info(iClient.getStatus().toString());
			
			logger.assertEqual("dClient status", dClient.getStatus(), BrokerClientStatus.READY);
			logger.assertEqual("iClient status", iClient.getStatus(),BrokerClientStatus.READY);
			
		
    	
		 int refId = Util.generateRandomInteger();
         for (int i = 0; i < 100; i++) {
             Echo request = Util.generateEchoRequest(refId);
             dClient.sendRequest(request, i);
			
         }
         logger.Info(dClient.getStatus().toString());
         logger.assertEqual("dClient status", dClient.getStatus(), BrokerClientStatus.READY);
         logger.Info("Call endRequests()...");
       
			dClient.endRequests();
		 logger.Info(dClient.getStatus().toString());
		 logger.assertEqual("dClient status", dClient.getStatus(), BrokerClientStatus.PROCESSING);
		 
		 logger.Info("Retrieving responses...");
         for (BrokerResponse<EchoResponse> response : dClient
                 .getResponses(EchoResponse.class)) {
             try {
                 ComputerInfo reply = response.getResult().getEchoResult()
                         .getValue();
                 logger.Info(
                         "\tReceived response for request %s: refId: %d",
                         response.getUserData(), reply.getRefID());
                 logger.assertTrue("check response",
                         reply.getRefID() == refId);
             } catch (Throwable ex) {
                 logger.Error("Error in process request %s", ex.toString());

             }

         }
         
         logger.Info(dClient.getStatus().toString());
         logger.assertEqual("dClient status", dClient.getStatus(), BrokerClientStatus.FINISHED);
         logger.Info("Done retrieving %d responses", 100);
         
		 		
    	} catch (SessionException e) {
			logger.Error("UE is thrown %s",e.toString());
			e.printStackTrace();
    	} catch (SocketTimeoutException e) {
    		logger.Error("UE is thrown %s",e.toString());
			e.printStackTrace();
		}
		
		
    	
    	
    	
    }

    /**
     * Test method for {@link java.lang.Object#Object()}.
     */
    @Ignore
    public final void testObject() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#getClass()}.
     */
    @Ignore
    public final void testGetClass() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#hashCode()}.
     */
    @Ignore
    public final void testHashCode() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
     */
    @Ignore
    public final void testEquals() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#clone()}.
     */
    @Ignore
    public final void testClone() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#toString()}.
     */
    @Ignore
    public final void testToString() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#notify()}.
     */
    @Ignore
    public final void testNotify() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#notifyAll()}.
     */
    @Ignore
    public final void testNotifyAll() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#wait(long)}.
     */
    @Ignore
    public final void testWaitLong() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#wait(long, int)}.
     */
    @Ignore
    public final void testWaitLongInt() {
        // we do not test it here
    }

    /**
     * Test method for {@link java.lang.Object#wait()}.
     */
    @Ignore
    public final void testWait() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#getResponses(java.lang.Class, int)}
     * .
     */
    @Test
    public void testGetResponsesClassOfTMessageInt() {

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#getResponses()}.
     */
    @Test
    public void testGetResponses2() {
        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");
            dClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<EchoResponse>> responses = dClient.getResponses(EchoResponse.class);
            Iterator<BrokerResponse<EchoResponse>> iter = responses.iterator();
            BrokerResponse<EchoResponse> response = iter.next();
            ComputerInfo reply;
            try {
                reply = response.getResult().getEchoResult()
                        .getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#getResponses(int)}
     * .
     */
    @Test
    public void testGetResponsesInt() {
        // functional
        Echo request = Util.generateEchoRequest();

        try {
            dClient.sendRequest(request, "data");
            dClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<EchoResponse>> responses = dClient.getResponses(EchoResponse.class);
            Iterator<BrokerResponse<EchoResponse>> iter = responses.iterator();
            BrokerResponse<EchoResponse> response = iter.next();
            ComputerInfo reply;
            try {
                reply = ((EchoResponse) response.getResult()).getEchoResult().getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<EchoResponse>> responses = dClient.getResponses(EchoResponse.class);
            Iterator<BrokerResponse<EchoResponse>> iter = responses.iterator();
            BrokerResponse<EchoResponse> response = iter.next();
            ComputerInfo reply;
            try {
                reply = ((EchoResponse) response.getResult()).getEchoResult()
                        .getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            Iterable<BrokerResponse<Object>> responses = dClient
                    .getResponses(-1);
            logger.Error("Expected exception not thrown");

        } catch (IllegalArgumentException e) {
            logger.Info("Expected excpetion when getting responses %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when getting responses %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<Object>> responses = dClient
                    .getResponses(Integer.MIN_VALUE);
            logger.Error("Expected exception not thrown");

        } catch (IllegalArgumentException e) {
            logger.Info("Expected excpetion when getting responses %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when getting responses %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#getResponses(int)}
     * .
     */
    @Test
    public void testGetResponsesInt_E1() {
        // functional
        GenerateLoad request = Util.generateGeneratedLoad(null, null,
                1 * 1000, Util.generateRandomInteger());

        try {
            dClient.sendRequest(request, "data");
            dClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<GenerateLoadResponse>> responses = dClient
                    .getResponses(GenerateLoadResponse.class);
            Iterator<BrokerResponse<GenerateLoadResponse>> iter = responses.iterator();
            BrokerResponse<GenerateLoadResponse> response = iter.next();
            StatisticInfo reply;
            try {
                reply = ((GenerateLoadResponse) response.getResult())
                        .getGenerateLoadResult().getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<GenerateLoadResponse>> responses = dClient
            .getResponses(GenerateLoadResponse.class);
            Iterator<BrokerResponse<GenerateLoadResponse>> iter = responses.iterator();
            BrokerResponse<GenerateLoadResponse> response = iter.next();
            StatisticInfo reply;
            try {
                reply = ((GenerateLoadResponse) response.getResult())
                        .getGenerateLoadResult().getValue();
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);
            } catch (SOAPFaultException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            } catch (SOAPException e) {
                logger.Error("Exception in getResponse %s", e.toString());
                e.printStackTrace();
            }
        } catch (SessionException e) {
            logger.Error("Excpetion when getting responses %s", e.toString());
            e.printStackTrace();
        }

        // boundary

        try {
            Iterable<BrokerResponse<Object>> responses = dClient
                    .getResponses(-1);
            logger.Error("Expected exception not thrown");

        } catch (IllegalArgumentException e) {
            logger.Info("Expected excpetion when getting responses %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when getting responses %s", e.toString());
            e.printStackTrace();
        }

        try {
            Iterable<BrokerResponse<Object>> responses = dClient
                    .getResponses(Integer.MIN_VALUE);
            logger.Error("Expected exception not thrown");

        } catch (IllegalArgumentException e) {
            logger.Info("Expected excpetion when getting responses %s",
                    e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when getting responses %s", e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(java.lang.Class, com.microsoft.hpc.scheduler.session.ResponseListener, int)}
     * .
     */
    @Ignore
    public void testSetResponseHandlerClassOfTMessageResponseListenerOfTMessageInt() {

        // functional

        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(EchoResponse.class,
                new ResponseListener<EchoResponse>() {

                    private int count = 0;

                    @Override
                    public void responseReturned(
                            BrokerResponse<EchoResponse> response) {
                        count++;
                        ComputerInfo reply = null;
                        try {
                            reply = response.getResult().getEchoResult()
                                    .getValue();
                        } catch (SOAPFaultException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        } catch (SOAPException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        }
                        logger.Info("\tReceived response for request %s: %s",
                                response.getUserData(), reply);
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(request.getRefID()) == 0);

                    }

                    @Override
                    public void endOfMessage() {
                        logger.Info("Done retrieving %d responses", count);
                        l.countDown();

                    }

                    @Override
                    public void raiseError(Exception e) {
                        logger.Error("Error in process request %s, raised error",
                                e.toString());
                        e.printStackTrace();
                        l.countDown();
                    }

                }, 15000);

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(10, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    
    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(java.lang.Class, com.microsoft.hpc.scheduler.session.ResponseListener, int)}
     * .
     */
    @Test
    public void testSetResponseHandlerClassOfTMessageResponseListenerOfTMessageInt2() {

        // functional

        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(EchoResponse.class,
                new ResponseListener<EchoResponse>() {

                    private int count = 0;

                    @Override
                    public void responseReturned(
                            BrokerResponse<EchoResponse> response) {
                        count++;
                        ComputerInfo reply = null;
                        try {
                            reply = response.getResult().getEchoResult()
                                    .getValue();
                        } catch (SOAPFaultException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        } catch (SOAPException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        }
                        logger.Info("\tReceived response for request %s: %s",
                                response.getUserData(), reply);
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(request.getRefID()) == 0);

                    }

                    @Override
                    public void endOfMessage() {
                        logger.Info("Done retrieving %d responses", count);
                        l.countDown();

                    }

                    @Override
                    public void raiseError(Exception e) {
                        logger.Error("Error in process request %s",
                                e.toString());
                        e.printStackTrace();
                    }

                }, 0);

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(2, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(java.lang.Class, com.microsoft.hpc.scheduler.session.ResponseListener, int)}
     * .
     */
    @Test
    public void testSetResponseHandlerClassOfTMessageResponseListenerOfTMessageInt_E1() {

        // boundary

        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(EchoResponse.class,
                new ResponseListener<EchoResponse>() {

                    private int count = 0;

                    @Override
                    public void responseReturned(
                            BrokerResponse<EchoResponse> response) {
                        count++;
                        ComputerInfo reply = null;
                        try {
                            reply = response.getResult().getEchoResult()
                                    .getValue();
                        } catch (SOAPFaultException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        } catch (SOAPException e) {
                            logger.Error("Error in process request %s",
                                    e.toString());
                            e.printStackTrace();
                        } 
                        logger.Info("\tReceived response for request %s: %s",
                                response.getUserData(), reply);
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(request.getRefID()) == 0);

                    }

                    @Override
                    public void endOfMessage() {
                        logger.Info("Done retrieving %d responses", count);
                        l.countDown();

                    }

                    @Override
                    public void raiseError(Exception e) {
                    	if (e instanceof WebServiceException) {
                    		if (e.getCause() instanceof SocketTimeoutException) {
                    			logger.Info("Expected timeout exception");
                    			e.printStackTrace();
                                l.countDown();
                                return;
                    		}
                    	}
                    	logger.Error("Error in process request %s raised error",
                                e.toString());
                    	e.printStackTrace();
                        l.countDown();
                        return;
                        
                    }

                }, 5);

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(2, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(com.microsoft.hpc.scheduler.session.ResponseListener)}
     * .
     */
    @Test
    public void testSetResponseHandlerResponseListenerOfObject() {

        // functional

        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(new ResponseListener<Object>() {

            private int count = 0;

            @Override
            public void responseReturned(BrokerResponse<Object> response) {
                count++;
                ComputerInfo reply = null;
                try {
                    reply = ((EchoResponse) response.getResult())
                            .getEchoResult().getValue();
                } catch (SOAPFaultException e) {
                    logger.Error("Error in process request %s", e.toString());
                    e.printStackTrace();
                } catch (SOAPException e) {
                    logger.Error("Error in process request %s", e.toString());
                    e.printStackTrace();
                }
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);

            }

            @Override
            public void endOfMessage() {
                logger.Info("Done retrieving %d responses", count);
                l.countDown();

            }

            @Override
            public void raiseError(Exception e) {
                logger.Error("Error in process request %s", e.toString());
                e.printStackTrace();
            }

        });

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(10, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(com.microsoft.hpc.scheduler.session.ResponseListener, int)}
     * .
     */
    @Test
    public void testSetResponseHandlerResponseListenerOfObjectInt() {
        // functional

        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(new ResponseListener<Object>() {

            private int count = 0;

            @Override
            public void responseReturned(BrokerResponse<Object> response) {
                count++;
                ComputerInfo reply = null;
                try {
                    reply = ((EchoResponse) response.getResult())
                            .getEchoResult().getValue();
                } catch (SOAPFaultException e) {
                    logger.Error("Error in process request %s", e.toString());
                    e.printStackTrace();
                } catch (SOAPException e) {
                    logger.Error("Error in process request %s", e.toString());
                    e.printStackTrace();
                }
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);

            }

            @Override
            public void endOfMessage() {
                logger.Info("Done retrieving %d responses", count);
                l.countDown();

            }

            @Override
            public void raiseError(Exception e) {
                logger.Error("Error in process request %s", e.toString());
                e.printStackTrace();
            }

        }, 5000);

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(10, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerClient#setResponseHandler(com.microsoft.hpc.scheduler.session.ResponseListener, int)}
     * .
     */
    @Test
    public void testSetResponseHandlerResponseListenerOfObjectInt_E1() {
        // boundary

        final Echo request = Util.generateEchoRequest();
        final CountDownLatch l = new CountDownLatch(1);

        iClient.setResponseHandler(new ResponseListener<Object>() {

            private int count = 0;

            @Override
            public void responseReturned(BrokerResponse<Object> response) {
                count++;
                ComputerInfo reply = null;
                try {
                    reply = ((EchoResponse) response.getResult())
                            .getEchoResult().getValue();
                } catch (SOAPFaultException e) {
                    logger.Error("Error in process request %s", e.toString());
                    e.printStackTrace();
                } catch (SOAPException e) {
                    logger.Error("Error in process request %s", e.toString());
                    e.printStackTrace();
                }
                logger.Info("\tReceived response for request %s: %s",
                        response.getUserData(), reply);
                logger.assertTrue("check response",
                        reply.getRefID().compareTo(request.getRefID()) == 0);

            }

            @Override
            public void endOfMessage() {
                logger.Info("Done retrieving %d responses", count);
                l.countDown();

            }

            @Override
            public void raiseError(Exception e) {
                logger.Error("Error in process request %s", e.toString());
                e.printStackTrace();
            }

        }, 0);

        try {
            iClient.sendRequest(request, "data");
            iClient.endRequests();
        } catch (SessionException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Excpetion when sending requests %s", e.toString());
            e.printStackTrace();
        }

        try {
            l.await(10, TimeUnit.MINUTES);
        } catch (InterruptedException e1) {
            logger.Error("Excpetion when awaiting latch %s", e1.toString());
            e1.printStackTrace();
        }

    }

    private static Session iSession = null;
    private static DurableSession dSession = null;
    private static BrokerClient<AITestLibService> iClient = null;
    private static BrokerClient<AITestLibService> dClient = null;

    private static Config config;
    private static Logger logger;

}
