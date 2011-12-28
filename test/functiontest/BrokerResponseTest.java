/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

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
import org.tempuri.GenerateLoad;
import org.tempuri.GenerateLoadResponse;

import com.microsoft.hpc.scheduler.session.BrokerClient;
import com.microsoft.hpc.scheduler.session.BrokerResponse;
import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;

/**
 * @author yutongs
 * 
 */
public class BrokerResponseTest {

    private static Echo request = Util.generateEchoRequest();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("BrokerResponseTest");
        logger = new Logger(true, true, "BrokerResponseTest");

        // init sessions and clients
        SessionStartInfo info = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        info.setSessionIdleTimeout(Integer.MAX_VALUE);
        info.setClientIdleTimeout(Integer.MAX_VALUE);

        dSession = DurableSession.createSession(info);
        iSession = Session.createSession(info);

        dClient = new BrokerClient<AITestLibService>(dSession,
                AITestLibService.class);
        iClient = new BrokerClient<AITestLibService>(iSession,
                AITestLibService.class);

        dClient.sendRequest(request, 2);
        dClient.endRequests();
        dResponse = dClient.getResponses(EchoResponse.class).iterator().next();

        iClient.sendRequest(request, 1);
        iClient.endRequests();
        iResponse = iClient.getResponses(EchoResponse.class).iterator().next();

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dClient.close();
        iClient.close();
        dSession.close();
        iSession.close();
        logger.close();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        logger.Start("BrokerResponseTest");

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        logger.End("BrokerResponseTest");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerResponse#getMessage()}.
     */
    @Test
    public final void testGetMessage() {
        logger.Info(iResponse.getMessage().toString());
        logger.Info(iResponse.getMessage().getContentDescription());

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerResponse#getResult()}.
     */
    @Test
    public final void testGetResult() {
        try {
            ComputerInfo value = dResponse.getResult().getEchoResult()
                    .getValue();
            logger.assertTrue("message compare",
                    value.getRefID().compareTo(request.getRefID()) == 0);
        } catch (SOAPFaultException e) {
            logger.Error("response getresult error");
            e.printStackTrace();
        } catch (SOAPException e) {
            logger.Error("response getresult error");
            e.printStackTrace();
        }

        try {
            ComputerInfo value = iResponse.getResult().getEchoResult()
                    .getValue();
            logger.assertTrue("message compare",
                    value.getRefID().compareTo(request.getRefID()) == 0);
        } catch (SOAPFaultException e) {
            logger.Error("response getresult error");
            e.printStackTrace();
        } catch (SOAPException e) {
            logger.Error("response getresult error");
            e.printStackTrace();
        }

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerResponse#getUserData()}.
     */
    @Test
    public final void testGetUserData() {

        logger.assertEqual("get user data", iResponse.getUserData(), "1");
        logger.assertEqual("get user data", dResponse.getUserData(), "2");

    }

    /**
     * Test method for
     * {@link com.microsoft.hpc.scheduler.session.BrokerResponse#getSOAPMessage(java.lang.Object)}
     * .
     */
    @Test
    public final void testGetSOAPMessage() {

        logger.Info(iResponse.getMessage().toString());
        // it is not public, do not test it here
    }

    private static Session iSession = null;
    private static DurableSession dSession = null;
    private static BrokerClient<AITestLibService> iClient = null;
    private static BrokerClient<AITestLibService> dClient = null;

    private static BrokerResponse<EchoResponse> iResponse = null;
    private static BrokerResponse<EchoResponse> dResponse = null;

    private static Config config;
    private static Logger logger;

}
