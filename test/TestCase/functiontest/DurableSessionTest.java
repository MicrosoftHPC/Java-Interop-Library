/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;

import javax.xml.ws.WebServiceException;
import javax.xml.xpath.XPathExpressionException;

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
import org.tempuri.GenerateLoad;
import org.tempuri.GenerateLoadResponse;

import com.microsoft.hpc.scheduler.session.BrokerClient;
import com.microsoft.hpc.scheduler.session.BrokerResponse;
import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.SessionAttachInfo;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.Version;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherGetServiceVersionsSessionFaultFaultFaultMessage;
import com.microsoft.hpc.sessionlauncher.SessionInfo;

/**
 * @author yutongs
 * 
 */
public class DurableSessionTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("DurableSessionTest");
        logger = new Logger(true, true, "DurableSessionTest");
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
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.DurableSession#DurableSession(com.microsoft.hpc.sessionlauncher.SessionInfo, java.lang.String)}
     * .
     */
    @Ignore
    public final void testDurableSession() {
        // DurableSession ds = new DurableSession(null, null); //it is invisible

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.DurableSession#createSession(com.microsoft.hpc.scheduler.session.SessionStartInfo)}
     * .
     */
    @Test
    public final void testCreateSessionSessionStartInfo() {
        logger.Start("testCreateSessionSessionStartInfo");
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession.close();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        }

        // boundary
        try {
            dSession = DurableSession.createSession(null);
            logger.Error("Exception is not thrown.");
        } catch (NullPointerException e) {
            logger.Info("Exception is thrown when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.Error("SocketTimeoutException was thrown");
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.Error("SessionException was thrown");
        }

        SessionStartInfo info2 = new SessionStartInfo(config.Scheduler,
                config.ServiceName);

        try {
            dSession = DurableSession.createSession(info2);
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.Error("SocketTimeoutException was thrown");
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.Error("SessionException was thrown");
        } finally {
            try {
                dSession.close();
            } catch (SocketTimeoutException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SessionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // more boundary test case for invalid startInfo would be included in
        // SessionStartInfo test

        logger.End("testCreateSessionSessionStartInfo");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.DurableSession#createSession(com.microsoft.hpc.scheduler.session.SessionStartInfo, int)}
     * .
     */
    @Test
    public final void testCreateSessionSessionStartInfoInt() {
        logger.Start("testCreateSessionSessionStartInfoInt");
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info, 5000);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        } 
        
        try {
            dSession.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        try {
            dSession = DurableSession.createSession(info, 0);

        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        // boundary

        try {
            dSession = DurableSession.createSession(info, -1);
            logger.Error("EException is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession = DurableSession.createSession(info, 1);
            logger.Error("EException is not thrown.");
        } catch (SocketTimeoutException e) {
            logger.Info("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession = DurableSession.createSession(info, Integer.MIN_VALUE);
            logger.Error("EException is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        logger.End("testCreateSessionSessionStartInfoInt");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.DurableSession#attachSession(com.microsoft.hpc.scheduler.session.SessionAttachInfo, int)}
     * .
     */
    @Test
    public final void testAttachSessionSessionAttachInfoInt() {
        logger.Start("testAttachSessionSessionAttachInfoInt");

        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        SessionAttachInfo attachInfo = new SessionAttachInfo(config.Scheduler,
                dSession.getId(), config.UserName, config.Password);

        DurableSession dSession_attached = null;
        try {
            dSession_attached = DurableSession.attachSession(attachInfo, 5000);
        } catch (SessionException e1) {
            logger.Error("SessionException when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            dSession_attached = DurableSession.attachSession(attachInfo, 0);
        } catch (SessionException e1) {
            logger.Error("SessionException when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            dSession_attached.close();
        } catch (SessionException e) {
            logger.Error("SessionException when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        // boundary

        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("SessionException when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            dSession_attached = DurableSession.attachSession(attachInfo, -1);
            logger.Error("EE is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession_attached = DurableSession.attachSession(attachInfo, 1);
            logger.Error("EE is not thrown.");
        } catch (SessionException e1) {
            logger.Error("SessionException when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Info("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            dSession_attached = DurableSession.attachSession(attachInfo,
                    Integer.MIN_VALUE);
            logger.Error("EE is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession.close();
        } catch (SessionException e) {
            logger.Error("SessionException when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        logger.End("testAttachSessionSessionAttachInfoInt");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.DurableSession#attachSession(com.microsoft.hpc.scheduler.session.SessionAttachInfo)}
     * .
     */
    @Test
    public final void testAttachSessionSessionAttachInfo() {
        // BUG there is exception thrown for this interface
        // TODO change the following test code in this case

        logger.Start("testAttachSessionSessionAttachInfo");

        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        SessionAttachInfo attachInfo = new SessionAttachInfo(config.Scheduler,
                dSession.getId(), config.UserName, config.Password);

        DurableSession dSession_attached = null;
        try {
            dSession_attached = DurableSession.attachSession(attachInfo);
        } catch (SessionException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (Exception e1) {
            logger.Error("Exception when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            dSession_attached.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        // boundary

        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            dSession_attached = DurableSession.attachSession(null);
            logger.Error("EE is not thrown.");
        } catch (SessionException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (NullPointerException e1) {
            logger.Info("NullPointerException when attaching a durable session");
            e1.printStackTrace();
        } catch (Exception e1) {
            logger.Error("Exception when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            dSession.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        logger.End("testAttachSessionSessionAttachInfo");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getTraceSourceName()}
     * .
     */
    @Ignore
    public final void testGetTraceSourceName() {

        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getEventSource()}.
     */
    @Ignore
    public final void testGetEventSource() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#SessionBase(com.microsoft.hpc.sessionlauncher.SessionInfo, java.lang.String)}
     * .
     */
    @Ignore
    public final void testSessionBase() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getInfo()}.
     */
    @Ignore
    public final void testGetInfo() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getId()}.
     */
    @Test
    public final void testGetId() {
        logger.Start("testGetId");
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        logger.assertTrue("Session id should be greater than 0",
                dSession.getId() > 0);

        // boundary
        try {
            dSession.close();
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.End("testGetId");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getEndpointReference()}
     * .
     */
    @Test
    public final void testGetEndpointReference() {

        logger.Start("testGetEndpointReference");
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        logger.Info("EPR is %s", dSession.getEndpointReference());
        logger.assertTrue("Session EPR", dSession.getEndpointReference()
                .contains("http"));

        // boundary
        try {
            dSession.close();
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.End("testGetEndpointReference");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getClientVersion()}
     * .
     */
    @Test
    public final void testGetClientVersion() {

        logger.Start("testGetClientVersion");
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        Version v = dSession.getClientVersion();
        logger.assertEqual("DurableSession clientVersion major", v.getMajor(),
                3);
        logger.assertEqual("DurableSession clientVersion minor", v.getMinor(),
                3);

        // boundary
        try {
            dSession.close();
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.End("testGetClientVersion");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getServerVersion()}
     * .
     */
    @Test
    public final void testGetServerVersion() {
    	//Bug 13405

        logger.Start("testGetServerVersion");
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        Version v = dSession.getServerVersion();
        logger.Info(v.toString());
        logger.assertEqual("DurableSession serverVersion major", v.getMajor(),
                3);
        //logger.assertEqual("DurableSession serverVersion minor", v.getMinor(),
        //        2); // BUG to check if the server Version

        // boundary
        try {
            dSession.close();
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.End("testGetServerVersion");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#createSessionInternal(com.microsoft.hpc.scheduler.session.SessionStartInfo, int, java.lang.Boolean)}
     * .
     */
    @Ignore
    public final void testCreateSessionInternalSessionStartInfoIntBoolean() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#dispose()}.
     */
    @Ignore
    public final void testDispose() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#createSessionInternal(com.microsoft.hpc.scheduler.session.SessionStartInfo, com.microsoft.hpc.scheduler.session.Timeout, java.lang.Boolean)}
     * .
     */
    @Ignore
    public final void testCreateSessionInternalSessionStartInfoTimeoutBoolean() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#close()}.
     */
    @Test
    public final void testClose() {
        logger.Start("testClose");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            dSession.close();
        } catch (SessionException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        // boundary
        // close the session twice
        try {
            dSession.close();
            logger.Info("E should not be thrown");
        } catch (SessionException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        logger.End("testClose");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#close(java.lang.Boolean)}
     * .
     */
    @Test
    public final void testCloseBoolean() {

        logger.Start("testCloseBoolean");

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
            // close the session without purge
            session.close(false);

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
                    // logger.assertEqual("check whole string", reply,
                    // "SHPC-00421001:Java BVT");
                } catch (Throwable ex) {
                    logger.Error("Error in process request %s", ex.toString());

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();
            // close the session without purge
            session.close(false);

        } catch (Throwable e1) {
            logger.Error("Exception is thrown %s", e1.toString());
            e1.printStackTrace();
        }

        try {
            session = DurableSession.attachSession(attInfo);
            logger.Info("Session %d is attached.", sessionId);

            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            logger.Info("Retrieving responses...");

            for (BrokerResponse<EchoResponse> response : client.getResponses(EchoResponse.class)) {
                try {
                    ComputerInfo reply = response.getResult().getEchoResult()
                            .getValue();
                    logger.Info("\tReceived response for request %s: %s",
                            response.getUserData(), reply);
                    logger.assertTrue("check response",
                            reply.getRefID() == refId);
                    // logger.assertEqual("check whole string", reply,
                    // "SHPC-00421001:Java BVT");
                } catch (Throwable ex) {
                    logger.Error("Error in process request %s", ex.toString());

                }

            }
            logger.Info("Done retrieving %d responses", config.NbOfCalls);
            client.close();
            session.close();

        } catch (Throwable e1) {
            logger.Error("Exception is thrown %s", e1.toString());
            e1.printStackTrace();
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e1) {
                logger.Error("Exception is thrown %s", e1.toString());
                e1.printStackTrace();
            }
        }

        logger.End("testCloseBoolean");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#close(boolean, int)}
     * .
     */
    @Test
    public final void testCloseBooleanInt() {

        logger.Start("testCloseBooleanInt");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        DurableSession dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            dSession.close(true, 5000);
        } catch (SessionException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            dSession.close(true, 0);

        } catch (SessionException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        // boundary

        dSession = null;
        try {
            dSession = DurableSession.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (dSession == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            dSession.close(true, -1);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession.close(true, 1);
            logger.Error("EE is not thrown");
        } catch (SessionException e) {
            logger.Info("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Info("Session exception when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession.close(true, Integer.MIN_VALUE);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a durable session");
            e.printStackTrace();
        }

        try {
            dSession.close(false, Integer.MAX_VALUE);
        } catch (SessionException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        logger.End("testCloseBooleanInt");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#attachInternal(com.microsoft.hpc.scheduler.session.SessionAttachInfo, int)}
     * .
     */
    @Ignore
    public final void testAttachInternalSessionAttachInfoInt() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#attachInternal(com.microsoft.hpc.scheduler.session.SessionAttachInfo, com.microsoft.hpc.scheduler.session.Timeout)}
     * .
     */
    @Ignore
    public final void testAttachInternalSessionAttachInfoTimeout() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getTimeout(java.util.Date)}
     * .
     */
    @Ignore
    public final void testGetTimeout() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#checkCredential(com.microsoft.hpc.scheduler.session.SessionStartInfo)}
     * .
     */
    @Ignore
    public final void testCheckCredential() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#GetServiceVersions(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testGetServiceVersions() {
        logger.Start("testGetServiceVersions");
        try {
            logger.assertEqual("ccpechosvc version", DurableSession
                    .GetServiceVersions(config.Scheduler, config.ServiceName,
                            config.UserName, config.Password)[0].toString(),
                    "3.2");
        } catch (SessionException e) {
            logger.Error("Session exception");
            e.printStackTrace();
        }

        // get other service name
        String serviceName = config.getValue("ServiceName");
        if (serviceName != "") {
            try {
                Version[] vers = DurableSession.GetServiceVersions(
                        config.Scheduler, serviceName, config.UserName,
                        config.Password);
                for (Version ver : vers) {
                    logger.Info(ver.toString());
                }

            } catch (SessionException e) {
                logger.Error("Session exception");
                e.printStackTrace();
            }

        }

        // boundary
        Version[] versions = null;

        try {
            versions = DurableSession.GetServiceVersions("",
                    config.ServiceName, config.UserName, config.Password);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Info("SessionException is thrown");
            e.printStackTrace();
        }

        try {
            versions = DurableSession.GetServiceVersions("abcxyz087879",
                    config.ServiceName, config.UserName, config.Password);
            logger.Error("EE is not thrown");
        } catch (WebServiceException e) {
            logger.Info("WebServiceException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("SessionException is thrown");
            e.printStackTrace();
        }

        try {
            versions = DurableSession.GetServiceVersions(config.Scheduler, "",
                    config.UserName, config.Password);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("SessionException is thrown");
            e.printStackTrace();
        }

        try {
            versions = DurableSession.GetServiceVersions(config.Scheduler,
                    "abcxyz2329083", config.UserName, config.Password);
            logger.assertEqual("versions", versions.length, 0);
        } catch (IllegalArgumentException e) {
            logger.Error("IllegalArgumentException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("SessionException is thrown");
            e.printStackTrace();
        }

        logger.End("testGetServiceVersions");

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
     * Test method for {@link java.lang.Object#finalize()}.
     */
    @Ignore
    public final void testFinalize() {
        // we do not test it here
    }

    private static Config config;
    private static Logger logger;

}
