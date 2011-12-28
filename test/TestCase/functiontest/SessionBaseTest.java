/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;

import javax.xml.ws.WebServiceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionBase;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.Version;
import com.microsoft.hpc.sessionlauncher.ISessionLauncherGetServiceVersionsSessionFaultFaultFaultMessage;

/**
 * @author yutongs
 * 
 */
public class SessionBaseTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("SessionBaseTest");
        logger = new Logger(true, true, "SessionBaseTest");

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
        logger.Start("SessionBaseTest");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        logger.End("SessionBaseTest");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getId()}.
     */
    @Test
    public final void testGetId() {
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase sb = null;
        try {
            sb = DurableSession.createSession(info);
            logger.Info("Session id %d", sb.getId());
            sb.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            sb = Session.createSession(info);
            logger.Info("Session id %d", sb.getId());
            sb.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getEndpointReference()}
     * .
     */
    @Test
    public final void testGetEndpointReference() {
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase sb = null;
        try {
            sb = DurableSession.createSession(info);
            logger.Info("EPR is %s", sb.getEndpointReference());
            sb.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            sb = Session.createSession(info);
            logger.Info("EPR is %s", sb.getEndpointReference());
            sb.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getClientVersion()}
     * and
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#getServerVersion()}
     * .
     */
    @Test
    public final void testGetClientVersion() {
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase sb = null;
        try {
            sb = DurableSession.createSession(info);
            logger.assertEqual("client version", sb.getClientVersion()
                    .toString(), "3.2"); 
            /*            logger.assertEqual("server version", sb.getServerVersion()
                    .toString(), "3.2");
*/           logger.assertTrue("server version",sb.getServerVersion().toString().startsWith("3.2"));


            sb.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            sb = Session.createSession(info);
            logger.assertEqual("client version", sb.getClientVersion()
                    .toString(), "3.2");
            /*logger.assertEqual("server version", sb.getServerVersion()
                    .toString(), "3.2");
*/
            logger.assertTrue("server version",sb.getServerVersion().toString().startsWith("3.2"));

            sb.close();
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#checkSessionStartInfo(com.microsoft.hpc.scheduler.session.SessionStartInfo)}
     * .
     */
    @Ignore
    public final void testCheckSessionStartInfo() {
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
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#close(java.lang.Boolean)}
     * .
     */
    @Test
    public final void testCloseBoolean() {
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase session = null;
        try {
            session = DurableSession.createSession(info);
            session.close(true);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info);
            session.close(false);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#close(boolean, int)}
     * .
     */
    @Test
    public final void testCloseBooleanInt() {
        // functional
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SessionBase session = null;
        try {
            session = DurableSession.createSession(info);
            session.close(true, 5000);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info);
            session.close(true, 5000);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        }

        try {
            session = Session.createSession(info);
            session.close(true, 0);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
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

        try {
            session.close(false, -1000);
            logger.Error("EE is not thrown");
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        }

        try {
            session.close(true, 1);
            logger.Error("EE is not thrown 1");
        } catch (SocketTimeoutException e) {
            logger.Info("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.Error("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        }

        try {
            session.close(false, Integer.MIN_VALUE);
            logger.Error("EE is not thrown");
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a durable session");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        }

        try {
            session.close(true, Integer.MAX_VALUE);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a durable session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("IllegalArgumentException when creating a durable session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionBase#GetServiceVersions(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testGetServiceVersions() {

        try {
            logger.assertEqual("ccpechosvc version", SessionBase
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
                Version[] vers = SessionBase.GetServiceVersions(
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
            versions = SessionBase.GetServiceVersions("", config.ServiceName,
                    config.UserName, config.Password);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("UE is thrown");
            e.printStackTrace();
        }

        try {
            versions = SessionBase.GetServiceVersions("abcxyz087879",
                    config.ServiceName, config.UserName, config.Password);
            logger.Error("EE is not thrown");
        } catch (WebServiceException e) {
            logger.Info("SessionException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("SessionException is thrown");
            e.printStackTrace();
        }

        try {
            versions = SessionBase.GetServiceVersions(config.Scheduler, "",
                    config.UserName, config.Password);
            logger.Error("EE is not thrown");
        } catch (IllegalArgumentException e) {
            logger.Info("SessionException is thrown");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("SessionException is thrown");
            e.printStackTrace();
        }
        
        try {
            versions = SessionBase.GetServiceVersions(config.Scheduler,
                    "abcxyz2329083", config.UserName, config.Password);
            logger.assertEqual("empty versions", versions.length, 0);
            // logger.Error("EE is not thrown"); 
        } catch (SessionException e) {
            logger.Info("SessionException is thrown");
            e.printStackTrace();
        }

    }

    /**
     * Test method for NoServiceVersion
     */
    @Test
    public final void testNoServiceVersion() {
    	logger.assertEqual("noServiceVersion", SessionBase.NoServiceVersion, new Version(0,0));
    	
    }
    
    private static Config config;
    private static Logger logger;

}
