/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
public class SessionTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("DurableSessionTest");
        logger = new Logger(true, true, "DurableSessionTest");

        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSessionIdleTimeout(Integer.MAX_VALUE);
        info.setClientIdleTimeout(Integer.MAX_VALUE);

        iSession = Session.createSession(info);

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        iSession.close();
        logger.close();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        logger.Start("SessionTest");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        logger.End("SessionTest");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#dispose()}.
     */
    @Ignore
    public final void testDispose() {
        // we do not test it here
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#getAutoCloseJob()}.
     */
    @Test
    public final void testGetAutoCloseJob() {
        logger.Info(iSession.getAutoCloseJob().toString()); // BUG what's this
                                                            // for?

    }

    
    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#netTcpEndpointReference()}
     * .
     */
    @Ignore
    public final void testNetTcpEndpointReference() {
        // deprecated
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#httpEndpointReference()}
     * .
     */
    @Test
    public final void testHttpEndpointReference() {
        logger.Info(iSession.httpEndpointReference());
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#createSession(com.microsoft.hpc.scheduler.session.SessionStartInfo)}
     * .
     */
    @Test
    public final void testCreateSessionSessionStartInfo() {
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        Session session = null;
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            session.close();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        }

        // boundary
        try {
            session = Session.createSession(null);
            logger.Error("EException is not thrown.");
        } catch (Throwable e) {
            logger.Info("Timeout when creating a durable session");
            e.printStackTrace();
        }

        SessionStartInfo info2 = new SessionStartInfo("abc",
                config.ServiceName, config.UserName, config.Password);

        // info2.setHeadnode("abcxyz1341235");

        try {
            session = Session.createSession(info2);
            logger.Error("EException is not thrown.");
        } catch (Throwable e) {
            logger.Info("Timeout when creating a durable session");
            e.printStackTrace();
        }

        // more boundary test case for invalid startInfo would be included in
        // SessionStartInfo test

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#createSession(com.microsoft.hpc.scheduler.session.SessionStartInfo, com.microsoft.hpc.scheduler.session.Timeout)}
     * .
     */
    @Test
    public final void testCreateSessionSessionStartInfoTimeout() {

        // BUG why need Timeout here.

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#createSession(com.microsoft.hpc.scheduler.session.SessionStartInfo, int)}
     * .
     */
    @Test
    public final void testCreateSessionSessionStartInfoInt() {
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        Session session = null;
        try {
            session = Session.createSession(info, 5000);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a   session");
            e.printStackTrace();
        }

        try {
            session.close();
        } catch (SessionException e) {
            logger.Error("SessionException when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info, Integer.MAX_VALUE);
            session.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a   session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info, 0);
            session.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a   session");
            e.printStackTrace();
        }

        // boundary

        try {
            session = Session.createSession(info, -1);
            logger.Error("EException is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a  session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a   session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info, 1);
            logger.Error("EException is not thrown.");
        } catch (SocketTimeoutException e) {
            logger.Info("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a   session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info, Integer.MIN_VALUE);
            logger.Error("EException is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a  session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a   session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#attachSession(com.microsoft.hpc.scheduler.session.SessionAttachInfo)}
     * .
     */
    @Test
    public final void testAttachSessionSessionAttachInfo() {
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        Session session = null;
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a  session");
            e.printStackTrace();
        }

        if (session == null) {
            logger.Error("creating dSession failed");
            return;
        }

        SessionAttachInfo attachInfo = new SessionAttachInfo(config.Scheduler,
                session.getId(), config.UserName, config.Password);

        Session session_attached = null;
        try {
            session_attached = Session.attachSession(attachInfo);
        } catch (SessionException e1) {
            logger.Error("Timeout when attaching a  session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a  session");
            e1.printStackTrace();
        }

        try {
            session_attached.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a  session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a  session");
            e.printStackTrace();
        }

        // boundary

        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a  session");
            e.printStackTrace();
        }

        if (session == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            session_attached = Session.attachSession(null);
            logger.Error("EE is not thrown.");
        } catch (NullPointerException e1) {
            logger.Info("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SessionException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            session.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.Session#attachSession(com.microsoft.hpc.scheduler.session.SessionAttachInfo, int)}
     * .
     */
    @Test
    public final void testAttachSessionSessionAttachInfoInt() {
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        Session session = null;
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (session == null) {
            logger.Error("creating dSession failed");
            return;
        }

        SessionAttachInfo attachInfo = new SessionAttachInfo(config.Scheduler,
                session.getId(), config.UserName, config.Password);

        Session session_attached = null;
        try {
            session_attached = Session.attachSession(attachInfo, 5000);
        } catch (SessionException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            session_attached = Session.attachSession(attachInfo, 0);
        } catch (SessionException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Error("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            session_attached.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

        // boundary

        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        if (session == null) {
            logger.Error("creating dSession failed");
            return;
        }

        try {
            session_attached = Session.attachSession(attachInfo, -1);
            logger.Error("EE is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a session");
            e.printStackTrace();
        }

        try {
            session_attached = Session.attachSession(attachInfo, 1);
            logger.Error("EE is not thrown.");
        } catch (SessionException e1) {
            logger.Info("Timeout when attaching a durable session");
            e1.printStackTrace();
        } catch (SocketTimeoutException e1) {
            logger.Info("Timeout when attaching a durable session");
            e1.printStackTrace();
        }

        try {
            session_attached = Session.attachSession(attachInfo,
                    Integer.MIN_VALUE);
            logger.Error("EE is not thrown.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a session");
            e.printStackTrace();
        } catch (Throwable e) {
            logger.Error("UE when creating a session");
            e.printStackTrace();
        }

        try {
            session.close();
        } catch (SessionException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when closing a durable session");
            e.printStackTrace();
        }

    }

    private static Session iSession = null;
    private static Config config;
    private static Logger logger;

}
