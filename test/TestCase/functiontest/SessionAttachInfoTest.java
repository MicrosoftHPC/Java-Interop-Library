/**
 * 
 */
package functiontest;

import java.net.SocketTimeoutException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionAttachInfo;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;

/**
 * @author yutongs
 * 
 */
public class SessionAttachInfoTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("SessionAttachInfoTest");
        logger = new Logger(true, true, "SessionAttachInfoTest");

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
     * {@link com.microsoft.hpc.scheduler.session.SessionAttachInfo#SessionAttachInfo(java.lang.String, int)}
     * .
     */
    @Test
    public final void testSessionAttachInfoStringInt() {
    	logger.Start();
        SessionAttachInfo attachInfo = new SessionAttachInfo(config.Scheduler,
                1, config.UserName, config.Password);
        try {
            logger.assertEqual("hn", attachInfo.getHeadnode(), config.Scheduler);
            logger.assertEqual("id", attachInfo.getId(), 1);
        } catch (Throwable e) {
            logger.Error("Exception is thrown ", e);
            
        }
        logger.End();

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionAttachInfo#SessionAttachInfo(java.lang.String, int, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testSessionAttachInfoStringIntStringString() {
    	logger.Start();
        int sessionId = dSession.getId();
        SessionAttachInfo attachInfo = new SessionAttachInfo(config.Scheduler,
                sessionId, config.UserName, config.Password);

        try {
            logger.assertEqual("hn", attachInfo.getHeadnode(), config.Scheduler);
            logger.assertEqual("id", attachInfo.getId(), sessionId);
        } catch (Throwable e) {
            logger.Error("Exception is thrown ", e);
            
        }

        DurableSession dSession_attached = null;
        try {
            dSession_attached = DurableSession.attachSession(attachInfo);
        } catch (SessionException e1) {
            logger.Error("SessionException when attaching a durable session", e1);
            
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session", e1);
            
        } catch (Exception e1) {
            logger.Error("Exception when attaching a durable session", e1);
            
        }

        // boundary
        try {
            attachInfo = new SessionAttachInfo("", 0, "", "");
            dSession_attached = DurableSession.attachSession(attachInfo);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e1) {
            logger.Info("Invalid sessionAttachInfo when attaching a durable session");
            
        } catch (Exception e1) {
            logger.Error("Invalid sessionAttachInfo when attaching a durable session", e1);
            
        }

        attachInfo = new SessionAttachInfo(config.Scheduler, 0,
                config.UserName, config.Password);
        try {
            dSession_attached = DurableSession.attachSession(attachInfo);
            logger.Error("EE is not thrown");
        } catch (Exception e1) {
            logger.Info("Invalid sessionAttachInfo when attaching a durable session");
            
        }

        attachInfo = new SessionAttachInfo(config.Scheduler, -1, "", "");
        try {
            dSession_attached = DurableSession.attachSession(attachInfo);
            logger.Error("EE is not thrown");
        } catch (Exception e1) {
            logger.Info("Invalid sessionAttachInfo when attaching a durable session");
            
        }

        attachInfo = new SessionAttachInfo(config.Scheduler, Integer.MAX_VALUE,
                "abc", "xyz");
        try {
            dSession_attached = DurableSession.attachSession(attachInfo);
            logger.Error("EE is not thrown");
        } catch (Exception e1) {
            logger.Info("Invalid sessionAttachInfo when attaching a durable session");
            
        }
        
        logger.End();

    }

    private static Session iSession = null;
    private static DurableSession dSession = null;

    private static Config config;
    private static Logger logger;

}
