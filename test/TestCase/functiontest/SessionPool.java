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
public class SessionPool {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("SessionPool");
        logger = new Logger(true, true, "SessionPool");

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
    public void test_SessionPool_DurableEcho() {

        logger.Start("test_SessionPool_DurableEcho");
        
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName);
        info.setSecure(true);
        info.setShareSession(true);
        info.setUseSessionPool(true);
        
        logger.Info("Creating a %s durable session from session pool at %s.", config.ServiceName,
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
                    logger.Error("Error in process request %s", ex.toString());

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close(true);

        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        try {
            session.close(false);
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        logger.End("test_SessionPool_DurableEcho");

    }

    /**
     * create a durable session, send requests, attach the session and then get
     * responses
     */

    @Test
    public void test_SessionPool_DurableEcho_Attach() {

        logger.Start("test_SessionPool_DurableEcho_Attach");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSecure(false);
        info.setShareSession(true);
        info.setUseSessionPool(true);
        
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
                            response.getUserData(), reply);
                    logger.assertTrue("check response",
                            reply.getRefID() == refId);
                } catch (Throwable ex) {
                    logger.Error("Error in process request %s", ex.toString());

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close(true);

        } catch (Throwable e1) {
            logger.Error("Exception is thrown %s", e1.toString());
            e1.printStackTrace();
        }

        if (session != null) {
            try {
                session.close(false); //needed to keep the session in the pool
            } catch (Throwable e1) {
                logger.Error("Exception is thrown %s", e1.toString());
                e1.printStackTrace();
            }
        }

        logger.End("test_SessionPool_DurableEcho_Attach");

    }

    
    /**
     * create a interactive session, send requests, attach the session and then get
     * responses
     */

    @Test
    public void test_SessionPool_InteractiveEcho_Attach() {

        logger.Start("test_SessionPool_InteractiveEcho_Attach");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSecure(false);
        info.setShareSession(true);
        info.setUseSessionPool(true);
        
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
            logger.assertEqual("autoclose should be false", session.getAutoCloseJob(), false);
            

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
                    logger.Error("Error in process request %s", ex.toString());

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close(true);

        } catch (Throwable e1) {
            logger.Error("Exception is thrown %s", e1.toString());
            e1.printStackTrace();
        }

        if (session != null) {
            try {
                session.close(false); //needed to keep the session in the pool
            } catch (Throwable e1) {
                logger.Error("Exception is thrown %s", e1.toString());
                e1.printStackTrace();
            }
        }

        logger.End("test_SessionPool_InteractiveEcho_Attach");

    }

   
    /**
     * interactive multiple batches
     */
    @Test
    public void test_SessionPool_InteractiveMultipleBatches() {
        logger.Start("test_SessionPool_InteractiveMultipleBatches");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setShareSession(true);
        info.setUseSessionPool(true);
        
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
                        logger.Error("Error in process request %s",
                                ex.toString());

                    }

                }
                logger.Info("Done retrieving %d responses", config.NbOfCalls);
                client.close();
            }

        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        try {
            session.close(false);
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        logger.End("test_SessionPool_InteractiveMultipleBatches");
    }

    /**
     * error - not shared
     */
    @Test
    public void test_SessionPool_NotShared() {
        logger.Start("test_SessionPool_NotShared");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setShareSession(false); 
        info.setUseSessionPool(true);

        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        int sessionId = 0;

        try {
            session = DurableSession.createSession(info);
            sessionId = session.getId();
            logger.Error("Session %d is created.", sessionId);

        } catch (Throwable e) {
            logger.Info("Exception is thrown %s%n%s", e.toString(),
                    e.getStackTrace());
        }
        logger.End("test_SessionPool_NotShared");

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
                            logger.Error("SessionException: %s", e.toString());
                            e.printStackTrace();
                        } catch (SocketTimeoutException e) {
                            logger.Error("SessionException: %s", e.toString());
                            e.printStackTrace();
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
                                        response.getUserData(), reply);
                                logger.assertTrue("check response",
                                        reply.getRefID() == m * k);

                            } catch (Throwable ex) {
                                logger.Error("Error in process request %s",
                                        ex.toString());

                            }

                        }
                    } catch (SessionException e) {
                        logger.Error("SessionException: %s", e.toString());
                        e.printStackTrace();
                    }
                    logger.Info("Done retrieving %d responses",
                            config.NbOfCalls);
                    try {
                        client.close(true);
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
    public void test_SessionPool_InteractiveConcurrentMultipleBatches() {
        logger.Start("test_SessionPool_InteractiveConcurrentMultipleBatches");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setShareSession(true);
        info.setUseSessionPool(true);
        
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
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        try {
            session.close(false);
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        logger.End("test_SessionPool_InteractiveConcurrentMultipleBatches");
    }


    private static Config config;
    private static Logger logger;

}
