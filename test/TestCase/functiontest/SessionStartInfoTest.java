/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionBase;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.SessionUnitType;
import com.microsoft.hpc.scheduler.session.Version;

/**
 * @author yutongs
 * 
 */
public class SessionStartInfoTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        config = new Config("SessionStartInfoTest");
        logger = new Logger(true, true, "SessionStartInfoTest");

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
        logger.Start("SessionStartInfoTest");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        logger.End("SessionStartInfoTest");
    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionStartInfo#SessionStartInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testSessionStartInfoStringStringStringString() {
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        logger.assertEqual("allocationGrowLoadRatioThreshold",
                info.getAllocationGrowLoadRatioThreshold(), 0); 
        logger.assertEqual("allocationShrinkLoadRatioThreshold",
                info.getAllocationShrinkLoadRatioThreshold(), 0);
        logger.assertEqual("clientIdleTimeout", info.clientIdleTimeout(), 0);
        logger.assertEqual("getResourceUnitType",
                    info.getResourceUnitType(), SessionUnitType.Core); 
        logger.assertEqual("headnode", info.getHeadnode(), config.Scheduler);
        logger.assertEqual("isSecure", info.isSecure(), true);
        logger.assertEqual("isShareSession", info.isShareSession(), false);
        logger.assertEqual("jobTemplate", info.getJobTemplate(), "Default");
        logger.assertEqual("maxUnits", info.getMaxUnits(), 0);
        logger.assertEqual("minUnits", info.getMinUnits(), 0);
        logger.assertEqual("messagesThrottleStartThreshold",
                info.messagesThrottleStartThreshold(), 0); // TODO, is this
                                                           // default value ok?
        logger.assertEqual("messagesThrottleStopThreshold",
                info.messagesThrottleStopThreshold(), 0); // TODO, is this
                                                          // default value ok?
        logger.assertEqual("nodeGroupsStr", info.getNodeGroups(), null);
        // logger.assertEqual("password", info.getPassword(), config.Password);
        logger.assertEqual("priority", info.getPriority(), 0);
        logger.assertEqual("requestedNodesStr", info.getRequestedNodesStr(), null);
        logger.assertEqual("runtime", info.getRuntime(), 0); 
        logger.assertEqual("serviceJobName", info.getServiceJobName(), null);
        logger.assertEqual("serviceName", info.getServiceName(),
                config.ServiceName);
        logger.assertEqual("sessionIdleTimeout", info.sessionIdleTimeout(), 0);
        logger.assertEqual("username", info.getUsername(), config.UserName);
        logger.assertEqual("runas username", info.getRunAsUsername(), config.UserName);
        
        logger.assertEqual("isPreemptable", info.isPreemptive(), false);
        
        // BUG the default value is not consistent
        
        info = new SessionStartInfo(config.Scheduler,
                config.ServiceName,config.UserName ,config.Password );
        
        info.setAllocationGrowLoadRatioThreshold(10);
        info.setAllocationShrinkLoadRatioThreshold(2);
        info.setClientIdleTimeout(3000);
        info.setJobTemplate("Default");
        info.setMaxUnits(12);
        info.setMinUnits(1);
        info.setMessagesThrottleStartThreshold(4000);
        info.setMessagesThrottleStopThreshold(3000);
        info.setNodeGroupsStr("ComputeNodes");
        info.setRunAsPassword(config.Password2);
        info.setRequestedNodesStr("");
        info.setResourceUnitType(SessionUnitType.Socket);
        info.setSecure(true);
        info.setServiceJobName("service job name");
        info.setServiceJobProject("service job project"); // BUG no api to get
                                                          // this

        info.setServiceName(config.ServiceName); //BUG why we can set this but cannot set scheduler name u/p?
        info.setServiceVersion(new Version(3, 2));
        info.setSessionIdleTimeout(5000);
        info.setShareSession(true);
        info.setRunAsUsername(config.UserName2); 
        info.setRuntime(3600);
        info.setPriority(4);
        info.addEnvironment("Env1", "Value1");
        info.addEnvironment("Env2", "Value2");
        
        info.setPreemptive(true);

        logger.assertEqual("allocationGrowLoadRatioThreshold",
                info.getAllocationGrowLoadRatioThreshold(), 10);
        logger.assertEqual("allocationShrinkLoadRatioThreshold",
                info.getAllocationShrinkLoadRatioThreshold(), 2);
        logger.assertEqual("clientIdleTimeout", info.clientIdleTimeout(), 3000);
        logger.assertEqual("getResourceUnitType", info.getResourceUnitType(),
                SessionUnitType.Socket);
        logger.assertEqual("headnode", info.getHeadnode(), config.Scheduler);
        logger.assertEqual("isSecure", info.isSecure(), true);
        logger.assertEqual("isShareSession", info.isShareSession(), true);
        logger.assertEqual("jobTemplate", info.getJobTemplate(), "Default");
        logger.assertEqual("maxUnits", info.getMaxUnits(), 12);
        logger.assertEqual("minUnits", info.getMinUnits(), 1);
        logger.assertEqual("messagesThrottleStartThreshold",
                info.messagesThrottleStartThreshold(), 4000);
        logger.assertEqual("messagesThrottleStopThreshold",
                info.messagesThrottleStopThreshold(), 3000);
        logger.assertEqual("nodeGroupsStr", info.getNodeGroups(),
                "ComputeNodes");
        logger.assertEqual("priority", info.getPriority(), 4); 
        logger.assertEqual("requestedNodesStr", info.getRequestedNodesStr(),
                "");
        logger.assertEqual("runtime", info.getRuntime(), 3600); // BUG no set
                                                                // runtime

        logger.assertEqual("serviceJobName", info.getServiceJobName(),
                "service job name");
        logger.assertEqual("serviceJobName", info.getServiceJobProject(),
                "service job project");
        logger.assertEqual("serviceName", info.getServiceName(), config.ServiceName);
        logger.assertEqual("sessionIdleTimeout", info.sessionIdleTimeout(),
                5000);
        logger.assertEqual("username", info.getUsername(), config.UserName); 
        logger.assertEqual("runas username", info.getRunAsUsername(), config.UserName2); 
        logger.assertEqual("isPreemptive", info.isPreemptive(), true);
        // BUG no get ServerVersion
        HashMap<String, String> map = info.getEnvironments();
        Iterator<Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            logger.Info("key %s value %s", entry.getKey(), entry.getValue());
            logger.assertTrue("validate key and value", entry.getKey().startsWith("Env"));
            logger.assertTrue("validate key and value", entry.getValue().startsWith("Value"));
            }
        

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
        //should check the session created.
        if (session !=null) {
        	try {
        		session.close();
        	 } catch (SocketTimeoutException e) {
                 logger.Error("Timeout when creating a  session");
                 e.printStackTrace();
        	} catch (SessionException e) {
                logger.Error("Session exception when creating a  session");
                e.printStackTrace();
            }
        }
        

        // boundary
        info = new SessionStartInfo("", config.ServiceName, config.UserName,
                config.Password);
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Info("Session exception when creating a  session");
            e.printStackTrace();
        }
        
        

        info = new SessionStartInfo(config.Scheduler, "", config.UserName,
                config.Password);
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Info("Session exception when creating a  session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionStartInfo#SessionStartInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testSessionStartInfoStringStringStringString_1() {
        SessionStartInfo info = null;
        Session session = null;

        // boundary
        // invalid password
        info = new SessionStartInfo(config.Scheduler, config.ServiceName,
                "abcxyz", "abcxyz");
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Info("Session exception when creating a  session");
            e.printStackTrace();
        }

    }

    
    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionStartInfo#SessionStartInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testSessionStartInfoStringStringStringString_2() {
        SessionStartInfo info = null;
        Session session = null;

        // boundary
        // invalid password
        info = new SessionStartInfo(config.Scheduler, config.ServiceName,
                config.UserName, "");
        try {
            session = Session.createSession(info);
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Info("Session exception when creating a  session");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionStartInfo#SessionStartInfo(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testSessionStartInfoStringString() {
        // TODO
        HpcJava.setUsername(config.UserName);
        HpcJava.setPassword(config.Password);
    	SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName);
        logger.assertEqual("", info.getHeadnode(), config.Scheduler);
        //logger.assertEqual("", info.getPassword(), null);
        logger.assertEqual("", info.getServiceName(), config.ServiceName);
        logger.assertEqual("", info.getUsername(), config.UserName);

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

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error("Exception is thrown %s", e.toString());
                e.printStackTrace();
            }
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.SessionStartInfo#SessionStartInfo(java.lang.String, java.lang.String, com.microsoft.hpc.scheduler.session.Version)}
     * .
     */
    @Test
    public final void testSessionStartInfoStringStringVersion() {
        Version v = new Version(2, 0);
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, v, config.UserName, config.Password);

        logger.assertEqual("hn", info.getHeadnode(), config.Scheduler);
        // logger.assertEqual("pw", info.getPassword(), config.Password);
        logger.assertEqual("sn", info.getServiceName(), config.ServiceName);
        logger.assertEqual("un", info.getUsername(), config.UserName);
        // BUG should be able to get service version
        logger.assertEqual("sv", info.getServiceVersion().toString(), "2.0");

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

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error("Exception is thrown %s", e.toString());
                e.printStackTrace();
            }
        }
    }

    
    
    
    
    /**
     * Test method for
     * 
     */
    @Test
    public final void testSessionStartInfoStringStringVersionStringString() {
    	String serviceName = config.getValue("ServiceName");
        Version[] vs = null;
		try {
			vs = SessionBase.GetServiceVersions(config.Scheduler, serviceName, config.UserName, config.Password);
		} catch (SessionException e1) {
			logger.Error("Exception when getting service version");
			e1.printStackTrace();
		}
        for (Version v : vs) {
        	logger.Info(v);
        }
        Version latest = vs[0];
    	SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                serviceName, latest, config.UserName, config.Password);

        Session session = null;
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.",session.getId());
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a  session");
            e.printStackTrace();
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error("Exception is thrown %s", e.toString());
                e.printStackTrace();
            }
        }
    }

    
    /**
     * Test method for
     * 
     */
    @Test
    public final void testSessionStartInfoStringStringVersionStringString_noVersion() {
    	Version[] vs = null;
		try {
			vs = SessionBase.GetServiceVersions(config.Scheduler, config.ServiceName, config.UserName, config.Password);
		} catch (SessionException e1) {
			logger.Error("Exception when getting service version");
			e1.printStackTrace();
		}
        for (Version v : vs) {
        	logger.Info(v);
        }
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, SessionBase.NoServiceVersion, config.UserName, config.Password);

        Session session = null;
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.",session.getId());
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Error("Session exception when creating a  session");
            e.printStackTrace();
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error("Exception is thrown %s", e.toString());
                e.printStackTrace();
            }
        }
    }

    
    /**
     * Test method for
     * 
     */
    @Test
    public final void testSessionStartInfoStringStringVersionStringString_Er() {
    	String serviceName = config.getValue("ServiceName");
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                serviceName, new Version(-1,0), config.UserName, config.Password);

        Session session = null;
        try {
            session = Session.createSession(info);
            logger.Info("Session %d is created.",session.getId());
        } catch (SocketTimeoutException e) {
            logger.Error("Timeout when creating a  session");
            e.printStackTrace();
        } catch (SessionException e) {
            logger.Info("Session exception when creating a  session");
            e.printStackTrace();
        }

        if (session != null) {
            try {
                session.close();
            } catch (Throwable e) {
                logger.Error("Exception is thrown %s", e.toString());
                e.printStackTrace();
            }
        }
    }

    
    private static Config config;
    private static Logger logger;

}
