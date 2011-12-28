package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.soap.SOAPFaultException;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tempuri.CcpEchoSvc;
import org.tempuri.Echo;
import org.tempuri.EchoResponse;
import org.tempuri.GenerateLoad;
import org.tempuri.GenerateLoadResponse;
import org.tempuri.IEchoSvc;
import org.tempuri.StatisticInfo;

import com.microsoft.hpc.genericservice.GenericOperation;
import com.microsoft.hpc.genericservice.GenericService;
import com.microsoft.hpc.scheduler.session.BrokerClient;
import com.microsoft.hpc.scheduler.session.BrokerResponse;
import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.ResponseListener;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionBase;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.Version;

public class SessionTest
{

    static String serverName = "junsuwin";
    static String userName = "fareast\\wsdcta";
    static String password = "Pa55word$$4";

    @BeforeClass
    public static void before() {
        HpcJava.setPassword(password);
        HpcJava.setUsername(userName);
    }

    /**
     * Creates a request class using the specified objects as input
     * 
     * The order of input should be the order they appear in the
     * <code>TRequest<code> class.
     * The function works by iterating through all <code>set[requestClass]</code>
     * methods and call the corresponding ObjectFactory, if necessary, to create
     * the input.
     * 
     * @param <TRequest>
     *            return type
     * @param requestClass
     *            instance to create
     * @param args
     *            input objects
     * @return A request to be sent to the broker using SendRequest
     * @see BrokerClient#sendRequest(Object)
     */
    public static <TRequest> TRequest createRequest(
            Class<TRequest> requestClass, Object... args) {
        return createRequest(requestClass, null, args, false);
    }

    /**
     * Creates a request class using the specified objects as input
     * 
     * The order of input should be the order they appear in the
     * <code>TRequest<code> class
     * The function works by iterate through all <code>set[requestClass]</code>
     * methods and call the corresponding ObjectFactory, if necessary, to create
     * the input.
     * 
     * @param <TRequest>
     *            return type
     * @param requestClass
     *            instance to create
     * @param classNames
     *            class names to check each argument against
     * @param args
     *            input objects
     * @return A request to be sent to the broker using SendRequest
     * @see BrokerClient#sendRequest(Object)
     */
    public static <TRequest> TRequest createRequest(
            Class<TRequest> requestClass, Class<?> classNames[], Object args[]) {
        return createRequest(requestClass, classNames, args, true);
    }

    /**
     * Creates a request class using the specified objects as input
     * 
     * @param <TRequest>
     *            return type
     * @param requestClass
     *            instance to create
     * @param classNames
     *            class names to check against
     * @param args
     *            input objects
     * @return A request to be sent to the broker using SendRequest
     * @see BrokerClient#sendRequest(Object)
     */
    private static <TRequest> TRequest createRequest(
            Class<TRequest> requestClass, Class<?> classNames[], Object args[],
            boolean checkClassArgs) {
        TRequest request;
        try {
            if (checkClassArgs && (args.length != classNames.length))
                throw new Exception();
            request = requestClass.newInstance();
            Class<?> ObjectFactory = Class.forName(requestClass.getPackage()
                    .getName() + ".ObjectFactory");
            Object of = ObjectFactory.newInstance();
            int i = 0;
            for (Method t : requestClass.getDeclaredMethods()) {
                if (t.getName().startsWith("set")) {
                    String parameter = t.getName().substring("set".length());
                    if (t.getParameterTypes()[0] == JAXBElement.class) {
                        for (Method ob : ObjectFactory.getDeclaredMethods()) {
                            String methodName = ob.getName();
                            boolean nameisValid = methodName
                                    .startsWith("create"
                                            + requestClass.getSimpleName()
                                            + parameter)
                                    && !methodName.contains("Response");
                            // && methodName.contains(parameter);

                            if (nameisValid) {
                                if (!checkClassArgs
                                        || ob.getParameterTypes()[0] == classNames[i])
                                    t.invoke(request, ob.invoke(of, args[i]));
                                else
                                    throw new Exception();
                            }
                        }
                    } else if (!checkClassArgs
                            || (t.getParameterTypes()[0] == classNames[i])) {
                        t.invoke(request, args[i]);
                    } else {
                        throw new Exception();
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return request;
    }

    @Test
    public void testCreateSession() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc",
                userName, password);
        Session session = null;
        try {
            session = Session.createSession(info);
            session.close(true);
            return;
        } catch (SocketTimeoutException e) {
            fail("unexpect failure:" + e.getMessage());
        } catch (SessionException e) {
            fail("unexpect failure:" + e.getMessage());
        }

        assertNotNull("Create Interactive Session Failed", session);
    }

    @Test
    public void testCreateSession2() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc",
                new Version(2, 0));
        Session session = null;
        try {
            session = Session.createSession(info);
            session.close(true);
            return;
        } catch (SocketTimeoutException e) {
            fail("unexpect failure:" + e.getMessage());
        } catch (SessionException e) {
            fail("unexpect failure:" + e.getMessage());
        }

        assertNotNull("Create Interactive Session Failed", session);
    }

    @Test
    public void testCreateDurableSession() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            session.close(true);
            return;
        } catch (SocketTimeoutException e) {
            fail("unexpect failure:" + e.getMessage());
        } catch (SessionException e) {
            fail("unexpect failure:" + e.getMessage());
        }

        assertNotNull("Create Interactive Session Failed", session);
    }

    @Test
    public void testV2client() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(false);
        Session session = null;
        try {
            session = Session.createSession(info);

            Service svc = Service.create(CcpEchoSvc.SERVICE);
            QName portName = new QName("http://hpc.microsoft.com",
                    "HpcBrokerClientHttpsPort");
            svc.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
                    session.getEndpointReference());
            IEchoSvc client = svc.getPort(portName, IEchoSvc.class);

            assertNotNull("return null", client.echo("HelloWorld"));

            session.close(true);
            return;
        } catch (SocketTimeoutException e) {
            fail("unexpect failure:" + e.getMessage());
        } catch (SessionException e) {
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void testBrokerClient() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(true);
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            echo.sendRequest(req, "");
            echo.endRequests();
            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                System.out.println(value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            fail("unexpect failure:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testGenericService() {
        SessionStartInfo info = new SessionStartInfo(serverName,
                "GenericService1");
        info.setSecure(false);
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);

            BrokerClient<GenericService> echo = new BrokerClient<GenericService>(
                    "clientid0", session, GenericService.class);
            GenericOperation req = SessionTest
                    .<GenericOperation> createRequest(GenericOperation.class,
                            "hello world");

            req = new GenericOperation();
            req.setArgs(new com.microsoft.hpc.genericservice.ObjectFactory()
                    .createGenericOperationArgs("A"));

            echo.sendRequest(req, "");
            echo.endRequests();
            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                System.out.println(value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            fail("unexpect failure:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testSecureBrokerClient() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(true);
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            echo.sendRequest(req);
            echo.endRequests();
            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                System.out.println(value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void testGetResponse() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(true);
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            echo.sendRequest(req, "SA");
            echo.endRequests();
            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                assertEquals(r.getUserData(), "SA");
                System.out.println(value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void testManyRequest() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(false);
        Session session = null;
        try {
            session = Session.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            for (int i = 0; i < 4000; i++) {
                echo.sendRequest(req);
            }

            echo.endRequests();
            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                // System.out.println(value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void testResponseHandler() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(false);

        try {
            final Session session = Session.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            echo.setResponseHandler(EchoResponse.class,
                    new ResponseListener<EchoResponse>() {
                        int fetchedCount = 0;

                        @Override
                        public void endOfMessage() {
                            Assert.assertEquals(10, fetchedCount);
                            synchronized (session) {
                                session.notify();
                            }
                        }

                        @Override
                        public void raiseError(Exception e) {
                            fail(e.getMessage());
                        }

                        @Override
                        public void responseReturned(
                                BrokerResponse<EchoResponse> response) {
                            try {
                                fetchedCount++;
                                String value = response.getResult()
                                        .getEchoResult().getValue();
                                assertNotNull("result is null", value);
                            } catch (SOAPFaultException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                fail("failed with exception: " + e.getMessage());
                            } catch (SOAPException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                fail("failed with exception: " + e.getMessage());
                            }
                        }

                    });

            for (int i = 0; i < 10; i++) {
                echo.sendRequest(req);
            }

            echo.endRequests();
            synchronized (session) {
                session.wait();
            }
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void testFlush() {
        int fetchedCount = 0;
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            echo.sendRequest(req);
            echo.flush();
            echo.sendRequest(req);
            echo.sendRequest(req);
            echo.flush();
            echo.sendRequest(req);
            echo.endRequests();
            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                fetchedCount++;
            }
            Assert.assertEquals(4, fetchedCount);
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void testTimeout() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(false);
        try {
            Session session;
            session = Session.createSession(info, 10);
            Assert.fail();
        } catch (SocketTimeoutException s) {
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }

        fail("didn't fail with timeout");
    }

    @Test
    public void testManyRequestWithMultiThreads() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        info.setSecure(false);
        Session session = null;
        try {
            Date d = new Date();
            session = Session.createSession(info);

            final BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            final Echo req = SessionTest.<Echo> createRequest(Echo.class,
                    "hello world");

            Runnable runnable = new Runnable() {
                public void run() {
                    for (int i = 0; i < 400; i++) {
                        try {
                            echo.sendRequest(req);
                        } catch (SessionException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (SocketTimeoutException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };

            Thread[] threads = new Thread[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(runnable);
                threads[i].start();
            }
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }

            echo.endRequests();

            Date end = new Date();
            System.out.println(end.getTime() - d.getTime());

            Iterable<BrokerResponse<EchoResponse>> p = echo
                    .getResponses(EchoResponse.class);
            for (BrokerResponse<EchoResponse> r : p) {
                String value = r.getResult().getEchoResult().getValue();
                assertNotNull("result is null", value);
                // System.out.println(value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void test64KMsg() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);

            BrokerClient<CcpEchoSvc> echo = new BrokerClient<CcpEchoSvc>(
                    "clientid0", session, CcpEchoSvc.class);
            GenerateLoad req = SessionTest.<GenerateLoad> createRequest(
                    GenerateLoad.class, (double) 100.0, new byte[1024 * 64],
                    null);

            echo.sendRequest(req);
            echo.endRequests();
            Iterable<BrokerResponse<GenerateLoadResponse>> p = echo
                    .getResponses(GenerateLoadResponse.class);
            for (BrokerResponse<GenerateLoadResponse> r : p) {
                StatisticInfo value = r.getResult().getGenerateLoadResult()
                        .getValue();
                assertNotNull("result is null", value);
            }
            session.close(true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            fail("unexpect failure:" + e.getMessage());
        }
    }

    @Test
    public void TestNoServiceVersion() {
        try {
            Version[] t = Session.GetServiceVersions(serverName, "CCPEchoSvc",
                    HpcJava.getUsername(), HpcJava.getPassword());
            assertEquals("t should be 1.", t.length, 1);
            assertEquals("t should be 1.", t[0], SessionBase.NoServiceVersion);
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void TestGetServerVersion() {
        SessionStartInfo info = new SessionStartInfo(serverName, "CCPEchoSvc");
        DurableSession session = null;
        try {
            session = DurableSession.createSession(info);
            Version v = session.getServerVersion();
            assertEquals("major version is 3.", v.getMajor(), 3);
            assertEquals("minor version is 2.", v.getMinor(), 2);
            assertEquals(v.toString(), "3.2.3674.0");
            session.close();
        } catch (SessionException e) {
            fail("unexpected exception");
        } catch (SocketTimeoutException e) {
            fail("unexpected exception");
        }
    }
}
