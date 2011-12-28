/**
 * 
 */
package stresstest;

import java.net.SocketTimeoutException;
import java.util.Random;
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
import functiontest.Logger;
import functiontest.Config;
import functiontest.Util;

/**
 * @author yutongs
 * 
 */
public class Stress {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("Stress");
        logger = new Logger(true, true, "Stress");

        HpcJava.setUsername(config.UserName);
        HpcJava.setPassword(config.Password);
        
      //read config params
        minCalls = Integer.parseInt(config.getValue("MinCalls"));
        maxCalls = Integer.parseInt(config.getValue("MaxCalls"));
        minClients = Integer.parseInt(config.getValue("MinClients"));
        maxClients = Integer.parseInt(config.getValue("MaxClients"));
        minBatches = Integer.parseInt(config.getValue("MinBatches"));
        maxBatches = Integer.parseInt(config.getValue("MaxBatches"));
        
        /*int minDataKb = Integer.parseInt(config.getValue("MinDataKb"));
        int maxDataKb = Integer.parseInt(config.getValue("MaxDataKb"));
        int minTimeMs = Integer.parseInt(config.getValue("MinTimeMs"));
        int maxTimeMs = Integer.parseInt(config.getValue("MaxTimeMs"));
        */
        rand =  new Random();
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
     * durable concurrent multiple batches, n * m batches
     */
    @Test
    public void test_Stress_DurableConcurrentMultipleBatches() {
        logger.Start("test_BVT_DurableConcurrentMultipleBatches");
        
        
        int NbOfCalls = minCalls+ rand.nextInt(maxCalls -minCalls);
        int NbOfClients = minClients + rand.nextInt(maxClients - minClients);
        int NbOfBatches = minBatches + rand.nextInt(maxBatches - minBatches);
        

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            logger.Info("Session %d is created.", session.getId());
 
            CountDownLatch L = new CountDownLatch(NbOfClients);

            Thread[] workers = new Thread[NbOfClients];
            for (int m = 0; m < NbOfClients; m++) {
                workers[m] = new Thread(new ConcurrentClient(m, NbOfBatches, NbOfCalls, L, session, 0));
            }
            for (int m = 0; m < NbOfClients; m++) {
                workers[m].start();
            }

            L.await(10, TimeUnit.MINUTES);

            CountDownLatch L2 = new CountDownLatch(NbOfClients);

            for (int m = 0; m < NbOfClients; m++) {
                workers[m] = new Thread(new ConcurrentClient(m,NbOfBatches, NbOfCalls, L2, session, 1));
            }
            for (int m = 0; m < NbOfClients; m++) {
                workers[m].start();
            }

            L2.await(10, TimeUnit.MINUTES);

        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        logger.End("test_BVT_DurableConcurrentMultipleBatches");
    }

    /**
     * interactive concurrent multiple batches, n * m batches
     */
    @Test
    public void test_Stress_InteractiveConcurrentMultipleBatches() {
        logger.Start("test_BVT_InteractiveConcurrentMultipleBatches");
        
        
        int NbOfCalls = minCalls+ rand.nextInt(maxCalls -minCalls);
        int NbOfClients = minClients + rand.nextInt(maxClients - minClients);
        int NbOfBatches = minBatches + rand.nextInt(maxBatches - minBatches);


        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.Info("Creating a %s durable session.", config.ServiceName);

        Session session = null;
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.", session.getId());

            CountDownLatch L = new CountDownLatch(NbOfClients);

            Thread[] workers = new Thread[NbOfClients];
            for (int m = 0; m < NbOfClients; m++) {
                workers[m] = new Thread(new ConcurrentClient(m, NbOfBatches, NbOfCalls, L, session, 0));
            }
            for (int m = 0; m < NbOfClients; m++) {
                workers[m].start();
            }

            L.await(10, TimeUnit.MINUTES);

            CountDownLatch L2 = new CountDownLatch(NbOfClients);

            for (int m = 0; m < NbOfClients; m++) {
                workers[m] = new Thread(new ConcurrentClient(m,NbOfBatches, NbOfCalls,  L2, session, 1));
            }
            for (int m = 0; m < NbOfClients; m++) {
                workers[m].start();
            }

            L2.await(10, TimeUnit.MINUTES);

        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        try {
            session.close();
        } catch (Throwable e) {
            logger.Error("Exception is thrown %s", e.toString());
            e.printStackTrace();
        }

        logger.End("test_BVT_InteractiveConcurrentMultipleBatches");
    }

    class ConcurrentClient implements Runnable {
        public ConcurrentClient(int m, int NbOfBatches, int NbOfCalls, CountDownLatch l, SessionBase session,
                int sendget) {
            this.m = m;
            this.l = l;
            this.NbOfBatches = NbOfBatches;
            this.NbOfCalls = NbOfCalls;
            this.session = session;
            this.sendget = sendget;

        }

        @Override
        public void run() {
            if (sendget == 0) {
                for (int k = 0; k < NbOfBatches; k++) {
                    BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                            "Client" + m + "_" + k, session,
                            AITestLibService.class);

                    logger.Info("Client %s sending %d requests...",
                            m + "_" + k, NbOfCalls);

                    for (int i = 0; i < NbOfCalls; i++) {
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
                for (int k = 0; k < NbOfBatches; k++) {
                    BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                            "Client" + m + "_" + k, session,
                            AITestLibService.class);
                    logger.Info("Client%s retrieving responses...", m + "_" + k);
                    try {
                    	int count =0;
                        for (BrokerResponse<EchoResponse> response : client
                                .getResponses(EchoResponse.class)) {
                            try {
                                ComputerInfo reply = response.getResult()
                                        .getEchoResult().getValue();
                                count++;

                            } catch (Throwable ex) {
                                logger.Error("Error in process request %s",
                                        ex.toString());

                            }

                        }
                        logger.assertEqual("response count should equal", count, NbOfCalls);
                        
                    } catch (SessionException e) {
                        logger.Error("SessionException: %s", e.toString());
                        e.printStackTrace();
                    }
                    logger.Info("Done retrieving %d responses",
                            NbOfCalls);
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
        private int NbOfBatches = 0;
        private int NbOfCalls = 0;
        private CountDownLatch l = null;
        private SessionBase session = null;
        private int sendget = 0;

    }


  //read config params
    private static int minCalls = 0;
    private static int maxCalls = 0;
    private static int minClients = 0;
    private static int maxClients = 0;
    private static int minBatches = 0;
    private static int maxBatches = 0;
    
   private static Random rand = null;
    
    
    private static Config config;
    private static Logger logger;
    
    

}
