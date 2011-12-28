/**
 * 
 */
package functiontest;

import java.net.SocketTimeoutException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

import org.datacontract.schemas._2004._07.services.ComputerInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import org.tempuri.AITestLibService;
import org.tempuri.Echo;
import org.tempuri.EchoResponse;
import com.microsoft.hpc.scheduler.session.BrokerClient;
import com.microsoft.hpc.scheduler.session.BrokerResponse;
import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.ResponseListener;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionAttachInfo;
import com.microsoft.hpc.scheduler.session.SessionBase;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;

/**
 * @author yutongs
 * 
 */
public class BVT {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("BVT");
        logger = new Logger(true, true, "BVT");

        HpcJava.setUsername(config.UserName);
        HpcJava.setPassword(config.Password);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        logger.close();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // todo: check if the target cluster is in ready state
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // todo: cleanup work for each case
    }

    /**
     * create a durable session, send requests and get responses
     */

    @Test
    public void test_BVT_DurableEcho() {

        logger.Start("test_BVT_DurableEcho");
        config.getValue("NbOfCalls");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName);
        info.setSecure(false);
        logger.Info("Creating a %s durable session at %s.", config.ServiceName,
                config.Scheduler);

        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    "clientid", session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);
            int refId = Util.generateRandomInteger();
            for (int i = 0; i < config.NbOfCalls; i++) {
                Echo request = Util.generateEchoRequest(refId);
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            logger.Info("Retrieving responses...");
            for (BrokerResponse<EchoResponse> response : client
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
                    logger.Error(ex);

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e) {
            logger.Error(e);            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
        }

        logger.End("test_BVT_DurableEcho");

    }

    /**
     * using secure
     */
    @Test
    public void test_BVT_DurableEcho_2() {
        logger.Start("test_BVT_DurableEcho_2");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSecure(true);
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    "clientId0", session, AITestLibService.class);
            Echo req = Util.generateEchoRequest();

            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(req, i);
            }

            client.endRequests();

            Iterable<BrokerResponse<EchoResponse>> p = client
                    .getResponses(EchoResponse.class);

            for (BrokerResponse<EchoResponse> r : p) {
                ComputerInfo value = r.getResult().getEchoResult().getValue();
                logger.assertTrue("check response",
                        value.getRefID().compareTo(req.getRefID()) == 0);
            }
            client.close();

        } catch (Throwable e) {
            logger.Error(e);
            

        }

        try {
            session.close();
        } catch (Exception e) {
            logger.Error(e);
        }

        logger.End("test_BVT_DurableEcho_2");
    }

    /**
     * create a durable session, send requests, attach the session and then get
     * responses
     */

    @Test
    public void test_BVT_DurableEcho_Attach() {

        logger.Start("test_BVT_DurableEcho_Attach");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        int sessionId = 0;
        int refId = Util.generateRandomInteger();

        try {
            session = DurableSession.createSession(info);
            sessionId = session.getId();
            logger.Info("Session %d is created.", sessionId);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);
            for (int i = 0; i < config.NbOfCalls; i++) {
                Echo request = Util.generateEchoRequest(refId);
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();
            client.close();
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s%n%s", e.toString(),
                    e.getStackTrace());
        }

        SessionAttachInfo attInfo = new SessionAttachInfo(config.Scheduler,
                sessionId, config.UserName, config.Password);

        try {
            session = DurableSession.attachSession(attInfo);
            logger.Info("Session %d is attached.", sessionId);

            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Retrieving responses...");

            for (BrokerResponse<EchoResponse> response : client
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check response",
                            reply.getRefID() == refId);
                } catch (Throwable ex) {
                    logger.Error(ex);

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e1) {
            logger.Error(e1);
            
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e1) {
                logger.Error(e1);
                
            }
        }

        logger.End("test_BVT_DurableEcho_Attach");

    }

    /**
     * create a interactive session, send requests and get responses
     */

    @Test
    public void test_BVT_InteractiveEcho() {

        logger.Start("test_BVT_InteractiveEcho");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s interactive session.", config.ServiceName);
        Session session = null;
        int refId = Util.generateRandomInteger();
        try {
            session = Session.createSession(info);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);

            for (int i = 0; i < config.NbOfCalls; i++) {
                Echo request = Util.generateEchoRequest(refId);
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            logger.Info("Retrieving responses...");
            for (BrokerResponse<EchoResponse> response : client
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check responses",
                            reply.getRefID() == refId);
                } catch (Exception ex) {
                    logger.Error(ex);
                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error(e);
                
            }
        }

        logger.End("test_BVT_InteractiveEcho");

    }

    /**
     * using secure
     */
    @Test
    public void test_BVT_InteractiveEcho_2() {
        logger.Start("test_BVT_InteractiveEcho_2");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSecure(true);
        Session session = null;
        int refId = Util.generateRandomInteger();
        try {
            session = Session.createSession(info);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    "clientId0", session, AITestLibService.class);
            Echo req = Util.generateEchoRequest(refId);

            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(req, i);
            }

            client.endRequests();

            Iterable<BrokerResponse<EchoResponse>> p = client
                    .getResponses(EchoResponse.class);

            for (BrokerResponse<EchoResponse> r : p) {
                ComputerInfo value = r.getResult().getEchoResult().getValue();
                logger.assertTrue("check responses", value.getRefID() == refId);

            }
            client.close();

        } catch (Exception e) {
            logger.Error(e);

        }

        try {
            session.close();
        } catch (Exception e) {
            logger.Error(e);
        }

        logger.End("test_BVT_InteractiveEcho_2");

    }

    /**
     * create an interactive session, send requests, attach the session and then
     * get responses
     */

    @Test
    public void test_BVT_InteractiveEcho_Attach() {

        logger.Start("test_BVT_InteractiveEcho_Attach");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        Session session = null;
        int sessionId = 0;
        int refId = Util.generateRandomInteger();
        try {
            session = Session.createSession(info);
            sessionId = session.getId();
            logger.Info("Session %d is created.", sessionId);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);

            for (int i = 0; i < config.NbOfCalls; i++) {
                Echo request = Util.generateEchoRequest(refId);
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();
            client.close();
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s%n%s", e.toString(),
                    e.getStackTrace());
        }

        SessionAttachInfo attInfo = new SessionAttachInfo(config.Scheduler,
                sessionId, config.UserName, config.Password);

        try {
            session = Session.attachSession(attInfo);
            logger.Info("Session %d is attached.", sessionId);

            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Retrieving responses...");

            for (BrokerResponse<EchoResponse> response : client
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check response",
                            reply.getRefID() == refId);
                    // logger.assertEqual("check whole string", reply,
                    // "SHPC-00421001:Java BVT");
                } catch (Throwable e) { 
                    logger.Error("Exception is thrown %s%n%s", e.toString(),
                            e.getStackTrace());

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e1) {
            logger.Error(e1);
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e1) {
                logger.Error(e1);
            }
        }

        logger.End("test_BVT_InteractiveEcho_Attach");

    }

    /**
     * only create and then close a durable session
     */

    @Test
    public void test_BVT_CreateDurableSession() {
        logger.Start("test_BVT_CreateDurableSession");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase session = null;
        try {
            session = Util.CreateSession(info, true);
        } catch (SocketTimeoutException e) {
            logger.Error(e);
        } catch (SessionException e) {
            logger.Error(e); 
        }

        if (session != null) {
            try {
                session.close();
            } catch (SessionException e) {
                logger.Error(e);
            } catch (SocketTimeoutException e) {
                logger.Error(e);
            }
        }

        logger.End("test_BVT_CreateDurableSession");
    }

    /**
     * only create and then close an interactive session
     */

    @Test
    public void test_BVT_CreateInteractiveSession() {
        logger.Start("test_BVT_CreateInteractiveSession");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase session = null;
        try {
            session = Util.CreateSession(info, false);
        } catch (SessionException e) {
            logger.Error(e);
        } catch (SocketTimeoutException e) {
            logger.Error(e);
        }

        if (session != null) {
            try {
                session.close();
            } catch (SessionException e) {
                logger.Error(e);
            } catch (SocketTimeoutException e) {
                logger.Error(e);
            }
        }

        logger.End("test_BVT_CreateInteractiveSession");

    }

    /**
     * durable client purge
     */
    @Test
    public void test_BVT_DurableClientPurge() {
        logger.Start("test_BVT_DurableClientPurge");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;

        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            Echo request = Util.generateEchoRequest();
            logger.Info("Sending %d requests...", config.NbOfCalls);

            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            client.close(false);

            logger.Info("Retrieving responses...");
            BrokerClient<AITestLibService> client2 = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);

            for (BrokerResponse<EchoResponse> response : client2
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check response", reply.getRefID()
                            .compareTo(request.getRefID()) == 0);
                } catch (Throwable ex) {
                    logger.Error(ex);

                }

            }

            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client2.close(true);

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_DurableClientPurge");

    }

    /**
     * interactive client purge
     */
    @Test
    public void test_BVT_InteractiveClientPurge() {

        logger.Start("test_BVT_InteractiveClientPurge");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s interactive session.", config.ServiceName);
        Session session = null;

        try {
            session = Session.createSession(info);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);
            Echo request = Util.generateEchoRequest();
            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();
            client.close(false);

            logger.Info("Retrieving responses...");
            BrokerClient<AITestLibService> client2 = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);

            for (BrokerResponse<EchoResponse> response : client2
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check responses", reply.getRefID()
                            .compareTo(request.getRefID()) == 0);
                } catch (Exception ex) {
                    logger.Error(ex);
                }

            }

            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client2.close();

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error(e);
                
            }
        }

        logger.End("test_BVT_InteractiveClientPurge");

    }

    /**
     * durable client flush
     */
    @Ignore
    public void test_BVT_DurableClientFlush() {
        logger.Start("test_BVT_DurableClientFlush");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);
            Echo request = Util.generateEchoRequest();
            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.flush();

            logger.Info("Retrieving responses...");
            for (BrokerResponse<EchoResponse> response : client
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check response", reply.getRefID()
                            .compareTo(request.getRefID()) == 0);
                    // logger.assertEqual("check whole string", reply,
                    // "SHPC-00421001:Java BVT");
                } catch (Throwable ex) {
                    logger.Error(ex);

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_DurableClientFlush");

    }

    /**
     * durable multiple batches
     */
    @Test
    public void test_BVT_DurableMultipleBatches() {
        logger.Start("test_BVT_DurableMultipleBatches");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        Echo[] requests = new Echo[config.NbOfClients];
        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());

            for (int k = 0; k < config.NbOfClients; k++) {
                BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                        "Client" + k, session, AITestLibService.class);
                requests[k] = Util.generateEchoRequest();
                logger.Info("Client%d sending %d requests...", k,
                        config.NbOfCalls);

                for (int i = 0; i < config.NbOfCalls; i++) {
                    client.sendRequest(requests[k], i);
                }
                logger.Info("Client%d calling endRequests()...", k);
                client.endRequests();
            }

            for (int k = 0; k < config.NbOfClients; k++) {
                BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                        "Client" + k, session, AITestLibService.class);
                logger.Info("Client%d retrieving responses...", k);
                for (BrokerResponse<EchoResponse> response : client
                        .getResponses(EchoResponse.class)) {
                    try {
                        ComputerInfo reply = response.getResult()
                                .getEchoResult().getValue();
                        logger.Info("\tReceived response for request %s: %s",
                                response.getUserData(), reply.getRefID());
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(requests[k].getRefID()) == 0);
                    } catch (Throwable ex) {
                        logger.Error(ex);

                    }

                }
                logger.Info("Done retrieving %d responses", config.NbOfCalls);
                client.close();
            }

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_DurableMultipleBatches");
    }

    /**
     * interactive multiple batches
     */
    @Test
    public void test_BVT_InteractiveMultipleBatches() {
        logger.Start("test_BVT_InteractiveMultipleBatches");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        Session session = null;
        Echo[] requests = new Echo[config.NbOfClients];
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.", session.getId());

            for (int k = 0; k < config.NbOfClients; k++) {
                BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                        "Client" + k, session, AITestLibService.class);

                logger.Info("Client%d sending %d requests...", k,
                        config.NbOfCalls);
                requests[k] = Util.generateEchoRequest();
                for (int i = 0; i < config.NbOfCalls; i++) {
                    client.sendRequest(requests[k], i);
                }
                logger.Info("Client%d calling endRequests()...", k);
                client.endRequests();
            }

            for (int k = 0; k < config.NbOfClients; k++) {
                BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                        "Client" + k, session, AITestLibService.class);

                logger.Info("Client%d retrieving responses...", k);
                for (BrokerResponse<EchoResponse> response : client
                        .getResponses(EchoResponse.class)) {
                    try {
                        ComputerInfo reply = response.getResult()
                                .getEchoResult().getValue();
                        logger.Info("\tReceived response for request %s: %s",
                                response.getUserData(), reply.getRefID()
                                        .toString());
                        logger.assertTrue("check response", reply.getRefID()
                                .compareTo(requests[k].getRefID()) == 0);

                    } catch (Throwable ex) {
                        logger.Error(ex);

                    }

                }
                logger.Info("Done retrieving %d responses", config.NbOfCalls);
                client.close();
            }

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_InteractiveMultipleBatches");
    }

    /**
     * durable shared session
     */
    @Test
    public void test_BVT_DurableSharedSession() {
        logger.Start("test_BVT_DurableSharedSession");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setShareSession(true); // BUG if set false,
                                    // java.lang.ArrayIndexOutOfBoundsException:
                                    // 50331651

        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        int sessionId = 0;

        try {
            session = DurableSession.createSession(info);
            sessionId = session.getId();
            logger.Info("Session %d is created.", sessionId);

        } catch (Throwable e) {
            logger.Error("Exception is thrown %s%n%s", e.toString(),
                    e.getStackTrace());
        }

        SessionAttachInfo attInfo = new SessionAttachInfo(config.Scheduler,
                sessionId, config.UserName2, config.Password2);

        try {
            session = DurableSession.attachSession(attInfo);
            logger.Info("Session %d is attached.", sessionId);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);
            Echo request = Util.generateEchoRequest();
            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            logger.Info("Retrieving responses...");

            for (BrokerResponse<EchoResponse> response : client
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check response", reply.getRefID()
                            .compareTo(request.getRefID()) == 0);
                    // logger.assertEqual("check whole string", reply,
                    // "SHPC-00421001:Java BVT");
                } catch (Throwable ex) {
                    logger.Error(ex);

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e1) {
            logger.Error(e1);
            
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e1) {
                logger.Error(e1);
                
            }
        }

        logger.End("test_BVT_DurableSharedSession");

    }

    /**
     * interactive shared session
     */
    @Ignore
    public void test_BVT_InteractiveSharedSession() {
        logger.Start("test_BVT_InteractiveSharedSession");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setShareSession(true); // BUG if set false,
                                    // java.lang.ArrayIndexOutOfBoundsException:
                                    // 50331651

        logger.Info("Creating a %s durable session.", config.ServiceName);

        Session session = null;
        int sessionId = 0;

        try {
            session = Session.createSession(info);
            sessionId = session.getId();
            logger.Info("Session %d is created.", sessionId);

        } catch (Throwable e) {
            logger.Error("Exception is thrown %s%n%s", e.toString(),
                    e.getStackTrace());
        }

        SessionAttachInfo attInfo = new SessionAttachInfo(config.Scheduler,
                sessionId, config.UserName2, config.Password2);

        try {
            session = Session.attachSession(attInfo);
            logger.Info("Session %d is attached.", sessionId);
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);
            Echo request = Util.generateEchoRequest();
            for (int i = 0; i < config.NbOfCalls; i++) {
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            logger.Info("Retrieving responses...");

            for (BrokerResponse<EchoResponse> response : client
                    .getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply.getRefID());
                    logger.assertTrue("check response", reply.getRefID()
                            .compareTo(request.getRefID()) == 0);
                    // logger.assertEqual("check whole string", reply,
                    // "SHPC-00421001:Java BVT");
                } catch (Throwable ex) {
                    logger.Error(ex);

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();

        } catch (Throwable e1) {
            logger.Error(e1);
            
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e1) {
                logger.Error(e1);
                
            }
        }

        logger.End("test_BVT_InteractiveSharedSession");

    }

    /**
     * durable concurrent multiple batches, n * m batches
     */
    @Test
    public void test_BVT_DurableConcurrentMultipleBatches() {
        logger.Start("test_BVT_DurableConcurrentMultipleBatches");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());

            CountDownLatch L = new CountDownLatch(config.NbOfBatches);

            Thread[] workers = new Thread[config.NbOfBatches];
            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m] = new Thread(new ConcurrentClient(m, L, session, 0));
            }
            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m].start();
            }

            L.await(10, TimeUnit.MINUTES);

            CountDownLatch L2 = new CountDownLatch(config.NbOfBatches);

            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m] = new Thread(new ConcurrentClient(m, L2, session, 1));
            }
            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m].start();
            }

            L2.await(10, TimeUnit.MINUTES);

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_DurableConcurrentMultipleBatches");
    }

    class ConcurrentClient implements Runnable {
        public ConcurrentClient(int m, CountDownLatch l, SessionBase session,
                int sendget) {
            this.m = m;
            this.l = l;
            this.session = session;
            this.sendget = sendget;

        }

        @Override
        public void run() {
            if (sendget == 0) {
                for (int k = 0; k < config.NbOfClients; k++) {
                    BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                            "Client" + m + "_" + k, session,
                            AITestLibService.class);

                    logger.Info("Client %s sending %d requests...",
                            m + "_" + k, config.NbOfCalls);

                    for (int i = 0; i < config.NbOfCalls; i++) {
                        Echo request = Util.generateEchoRequest(m * k);
                        try {
                            client.sendRequest(request, i);
                        } catch (SessionException e) {
                            logger.Error(e);
                            
                        } catch (SocketTimeoutException e) {
                            logger.Error(e);
                            
                        }
                    }
                    logger.Info("Client %s calling endRequests()...", m + "_"
                            + k);
                    try {
                        client.endRequests();
                    } catch (SocketTimeoutException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SessionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                l.countDown();
            } else if (sendget == 1) {
                for (int k = 0; k < config.NbOfClients; k++) {
                    BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                            "Client" + m + "_" + k, session,
                            AITestLibService.class);
                    logger.Info("Client%s retrieving responses...", m + "_" + k);
                    try {
                        for (BrokerResponse<EchoResponse> response : client
                                .getResponses(EchoResponse.class)) {
                            try {
                                ComputerInfo reply = response.getResult()
                                        .getEchoResult().getValue();
                                logger.Info(
                                        "\tReceived response for request %s: %s",
                                        response.getUserData(), reply.getRefID());
                                logger.assertTrue("check response",
                                        reply.getRefID() == m * k);

                            } catch (Throwable ex) {
                                logger.Error(ex);

                            }

                        }
                    } catch (SessionException e) {
                        logger.Error(e);
                        
                    }
                    logger.Info("Done retrieving %d responses",
                            config.NbOfCalls);
                    try {
                        client.close();
                    } catch (SocketTimeoutException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SessionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                l.countDown();
            }

        }

        private int m = 0;
        private CountDownLatch l = null;
        private SessionBase session = null;
        private int sendget = 0;

    }

    /**
     * interactive concurrent multiple batches, n * m batches
     */
    @Test
    public void test_BVT_InteractiveConcurrentMultipleBatches() {
        logger.Start("test_BVT_InteractiveConcurrentMultipleBatches");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        Session session = null;
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.", session.getId());

            CountDownLatch L = new CountDownLatch(config.NbOfBatches);

            Thread[] workers = new Thread[config.NbOfBatches];
            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m] = new Thread(new ConcurrentClient(m, L, session, 0));
            }
            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m].start();
            }

            L.await(10, TimeUnit.MINUTES);

            CountDownLatch L2 = new CountDownLatch(config.NbOfBatches);

            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m] = new Thread(new ConcurrentClient(m, L2, session, 1));
            }
            for (int m = 0; m < config.NbOfBatches; m++) {
                workers[m].start();
            }

            L2.await(10, TimeUnit.MINUTES);

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_InteractiveConcurrentMultipleBatches");
    }

    /**
     * to get responses async for a durable session
     */

    @Test
    public void test_BVT_DurableGetResponseAsync() {

        logger.Start("test_BVT_DurableGetResponseAsync");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        final int refId = Util.generateRandomInteger();
        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);

            for (int i = 0; i < config.NbOfCalls; i++) {
                Echo request = Util.generateEchoRequest(refId);
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            logger.Info("Retrieving responses...");

            final CountDownLatch l = new CountDownLatch(1);
            client.setResponseHandler(EchoResponse.class,
                    new ResponseListener<EchoResponse>() {

                        @Override
                        public void responseReturned(
                                BrokerResponse<EchoResponse> response) {

                            ComputerInfo reply = null;
                            try {
                                reply = response.getResult().getEchoResult()
                                        .getValue();
                            } catch (SOAPFaultException e) {
                                logger.Error(e);
                                
                            } catch (SOAPException e) {
                                logger.Error(e);
                                
                            }
                            logger.Info(
                                    "\tReceived response for request %s: %s",
                                    response.getUserData(), reply.getRefID());
                            logger.assertTrue("check response",
                                    reply.getRefID() == refId);

                        }

                        @Override
                        public void endOfMessage() {
                            logger.Info("Done retrieving %d responses",
                                    config.NbOfCalls);
                            l.countDown();

                        }

                        @Override
                        public void raiseError(Exception e) {
                            logger.Error(e);

                        }
                    });

            l.await(10, TimeUnit.MINUTES);
            client.close();

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_DurableGetResponseAsync");

    }

    /**
     * to get responses async for a durable session
     */

    @Test
    public void test_BVT_InteractiveGetResponseAsync() {

        logger.Start("test_BVT_InteractiveGetResponseAsync");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        Session session = null;
        final int refId = Util.generateRandomInteger();
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.", session.getId());
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Sending %d requests...", config.NbOfCalls);

            for (int i = 0; i < config.NbOfCalls; i++) {
                Echo request = Util.generateEchoRequest(refId);
                client.sendRequest(request, i);
            }
            logger.Info("Call endRequests()...");
            client.endRequests();

            logger.Info("Retrieving responses...");

            final CountDownLatch l = new CountDownLatch(1);
            client.setResponseHandler(EchoResponse.class,
                    new ResponseListener<EchoResponse>() {

                        @Override
                        public void responseReturned(
                                BrokerResponse<EchoResponse> response) {

                            ComputerInfo reply = null;
                            try {
                                reply = response.getResult().getEchoResult()
                                        .getValue();
                            } catch (SOAPFaultException e) {
                                logger.Error(e);
                                
                            } catch (SOAPException e) {
                                logger.Error(e);
                                
                            }
                            logger.Info(
                                    "\tReceived response for request %s: %s",
                                    response.getUserData(), reply.getRefID());
                            logger.assertTrue("check response",
                                    reply.getRefID() == refId);

                        }

                        @Override
                        public void endOfMessage() {
                            logger.Info("Done retrieving %d responses",
                                    config.NbOfCalls);
                            l.countDown();

                        }

                        @Override
                        public void raiseError(Exception e) {
                            logger.Error(e);

                        }
                    });

            l.await(10, TimeUnit.MINUTES);
            client.close();

        } catch (Throwable e) {
            logger.Error(e);
            
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error(e);
            
        }

        logger.End("test_BVT_InteractiveGetResponseAsync");

    }

    /**
     * custom binding
     */
    @Ignore
    public void test_BVT_CustomBinding() {

    }

    private static Config config;
    private static Logger logger;

}
