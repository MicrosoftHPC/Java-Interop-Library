//------------------------------------------------------------------------------
// <copyright file="HelloWorld.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Simple SOA test using Java.
// </summary>
//------------------------------------------------------------------------------

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import org.tempuri.*;
import com.microsoft.hpc.scheduler.session.*;
import sample.common.*;

public class HelloWorld {

    private static String username = "[Domain\\User]"; // valid username to submit SOA job
    private static String password = "[Password]";  // valid user password 
    private static String headnode = "[HeadNode]"; // HPC cluster headnode hostname
    private static int nrequests = 10;

    final private static String serviceName = "SoamSvc";
    final private static int nHTRequests = 100000;
    final private static int batchCount = 16;
    final private static int nbatchrequests = nHTRequests / batchCount;
    final private static int nbatchthreads = 2;
    final private static int timeout = 3600 * 1000; // 1 hour
    final static ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4096000);

    public static void main(String[] args) {
        int nerrs = 0;
        nerrs += ParseCmdLine(args);
        if (nerrs == 0) {
            try {
                //nerrs += RunHighThroughputTest();
                //nerrs += RunSoamTest();
                nerrs += RunBasicTest();
                //nerrs += RunCommonDataTest();
                //nerrs += RunResponseHandlerTest();
            } catch (Exception e) {
                nerrs++;
                e.printStackTrace();
            }
        }
        System.out.println("TotalErrors: " + nerrs);
    }

    private static void Usage() {
        System.out.println("Usage: HelloWorld /scheduler scheduler /username user /password pwd /nrequests N");
    }

    private static int ParseCmdLine(String[] args) {
        int nerrs = 0;
        int i = 0;
        String arg;
        while (i < args.length && args[i].startsWith("/")) {
            arg = args[i++];
            if (arg.equals("/scheduler")) {
                headnode = args[i++];
            } else if (arg.equals("/username")) {
                username = args[i++];
            } else if (arg.equals("/password")) {
                password = args[i++];
            } else if (arg.equals("/nrequests")) {
                nrequests = Integer.parseInt(args[i++]);
            } else {
                Usage();
                nerrs++;
                break;
            }
        }

        return nerrs;
    }

    private static int RunSoamTest() {
        int nerrs = 0;
        int nresponses = 0;

        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);
        
        System.out.printf("Creating a session for %s...\n", serviceName);

        try {
            // Prepare 1k binary data
            byte[] data = new byte[1024];
            Random r = new Random();
            r.nextBytes(data);

            // Create common data client
            String dataClientId = java.util.UUID.randomUUID().toString();
            DataClient dataClient = DataClient.create(dataClientId, headnode, username, password);
            System.out.printf("new common data client id = %s\n", dataClientId);
            // Write data to Windows HPC Cluster
            dataClient.writeRawBytesAll(data, true);

            // pass data client id to session
            info.setCommonDataClientId(dataClientId);

            Session session = Session.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());

            BrokerClient<SoamSvc> client = new BrokerClient<SoamSvc>(session, SoamSvc.class);
            System.out.printf("Sending %d requests...\n", nrequests);
            long timeMark1 = System.nanoTime();

            for (int i = 0; i < nrequests; i++) {
                MyInput input = new MyInput();
                ObjectFactory of = new ObjectFactory();
                SoamInvoke request = of.createSoamInvoke();
                request.setSoamInputObject(input);
                client.sendRequest(request, i + 1);
//                System.out.printf("Sent request %s: %s%n", i + 1, input);
            }
            client.endRequests();

            long timeMark2 = System.nanoTime();
            double elapsedTimeSec = (timeMark2 - timeMark1) / 1e9;
            System.out.println("Done calling endRequests() ...throughput=" + (nrequests / elapsedTimeSec));

            System.out.println("Retrieving responses...");

            for (BrokerResponse<SoamInvokeResponse> response : client.<SoamInvokeResponse>getResponses(SoamInvokeResponse.class)) {
                nresponses++;
                try {
                    MyOutput reply = new MyOutput();
                    response.getResult().getSoamOutputObject(reply);
//                    System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
                } catch (Exception ex) {
                    nerrs++;
                    System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                }
            }

            long timeMark3 = System.nanoTime();
            elapsedTimeSec = (timeMark3 - timeMark2) / 1e9;
            System.out.printf("Done retrieving %d responses ... throughput=%f %n", nresponses, (nresponses / elapsedTimeSec));

            elapsedTimeSec = (timeMark3 - timeMark1) / 1e9;
            System.out.printf("total throughput=%f %n", (nresponses / elapsedTimeSec));

            client.close();
            session.close();
        } catch (Throwable e) {
            nerrs++;
            e.printStackTrace();
        }
        return nerrs;
    }

    private static int RunCommonDataTest() {
        int nerrs = 0;
        int nresponses = 0;

        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);
        System.out.printf("Creating a session for %s...\n", serviceName);

        try {
            // Prepare 500m binary data
            byte[] data = new byte[500 * 1024 * 1024];
            Random r = new Random();
            r.nextBytes(data);

            // Create common data client
            String dataClientId = java.util.UUID.randomUUID().toString();
            DataClient dataClient = DataClient.create(dataClientId, headnode, username, password);
            System.out.printf("new common data client id = %s\n", dataClientId);
            // Write data to Windows HPC Cluster
            dataClient.writeRawBytesAll(data, true);

            // pass data client id to session
            info.setCommonDataClientId(dataClientId);
            

            Session session = Session.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());

            BrokerClient<SoamSvc> client = new BrokerClient<SoamSvc>(session, SoamSvc.class);
            System.out.printf("Sending %d requests...\n", nrequests);
            long timeMark1 = System.nanoTime();

            for (int i = 0; i < nrequests; i++) {
                ObjectFactory of = new ObjectFactory();
                SoamCommonData request = of.createSoamCommonData();
                request.setDataClientId(of.createSoamCommonDataDataClientId(dataClientId));
                client.sendRequest(request, i);
//                System.out.printf("Sent request %s: %s%n", i + 1, dataClientId);
            }
            client.endRequests();

            long timeMark2 = System.nanoTime();
            double elapsedTimeSec = (timeMark2 - timeMark1) / 1e9;
            System.out.println("Done calling endRequests() ...throughput=" + (nrequests / elapsedTimeSec));

            System.out.println("Retrieving responses...");

            for (BrokerResponse<SoamCommonDataResponse> response : client.<SoamCommonDataResponse>getResponses(SoamCommonDataResponse.class)) {
                nresponses++;
                try {
                    int reply = response.getResult().getSoamCommonDataResult();
//                    System.out.printf("\tReceived response for request %s: %d%n", response.getUserData(), reply);
                } catch (Exception ex) {
                    nerrs++;
                    System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                }
            }

            long timeMark3 = System.nanoTime();
            elapsedTimeSec = (timeMark3 - timeMark2) / 1e9;
            System.out.printf("Done retrieving %d responses ... throughput=%f %n", nresponses, (nresponses / elapsedTimeSec));

            elapsedTimeSec = (timeMark3 - timeMark1) / 1e9;
            System.out.printf("total throughput=%f %n", (nresponses / elapsedTimeSec));

            client.close();
            session.close();
        } catch (Throwable e) {
            nerrs++;
            e.printStackTrace();
        }
        return nerrs;
    }

    private static int RunHighThroughputTest() {
        int nerrs = 0;

        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);
        info.setSecure(Boolean.FALSE);
        System.out.printf("Creating a session for %s...\n", serviceName);

        List<BrokerClient<SoamSvc>> clientList = new ArrayList<BrokerClient<SoamSvc>>();

        try {
            Session session = Session.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());

            System.out.printf("Sending %d requests...\n", nHTRequests);
            long timeMark1 = System.nanoTime();

            sendRequests(session, clientList);

            long timeMark2 = System.nanoTime();
            double elapsedTimeSec = (timeMark2 - timeMark1) / 1e9;
            System.out.println("Done calling endRequests() ...throughput=" + (nHTRequests / elapsedTimeSec));

            System.out.println("Retrieving responses...");

            getResponses(clientList);

            long timeMark3 = System.nanoTime();
            elapsedTimeSec = (timeMark3 - timeMark2) / 1e9;
            System.out.printf("Done retrieving %d responses ... throughput=%f %n", nHTRequests, (nHTRequests / elapsedTimeSec));

            elapsedTimeSec = (timeMark3 - timeMark1) / 1e9;
            System.out.printf("total throughput=%f %n", (nHTRequests / elapsedTimeSec));

            session.close();
        } catch (Throwable e) {
            nerrs++;
            e.printStackTrace();
        }

        return nerrs;
    }

    private static void sendRequests(final SessionBase session, final List<BrokerClient<SoamSvc>> clientList) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
//                        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(nbatchthreads, nbatchthreads, 60, TimeUnit.SECONDS, queue);

                    String clientId = UUID.randomUUID().toString();
                    final BrokerClient<SoamSvc> client = new BrokerClient<SoamSvc>(
                            clientId, session, SoamSvc.class);
                    clientList.add(client);

                    // Send request
                    for (int i = 0; i < nbatchrequests; i++) {
                        MyInput input = new MyInput();
                        ObjectFactory of = new ObjectFactory();
                        SoamInvoke request = of.createSoamInvoke();
                        request.setSoamInputObject(input);
                        client.sendRequest(request, i + 1);
                        //System.out.printf("Sent request %s: %s%n", i + 1, input);
//                            threadPool.execute(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        MyInput input = new MyInput();
//                                        ObjectFactory of = new ObjectFactory();
//                                        SoamInvoke request = of.createSoamInvoke();
//                                        request.setSoamInputObject(input);
//                                        client.sendRequest(request);
//                                        //System.out.printf("Sent request %s: %s%n", i + 1, input);
//                                    } catch (Throwable e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
                    }

//                        while (threadPool.getActiveCount() > 0) {
//                            //System.out.printf("Active: %d Completed: %d\n", threadPool.getActiveCount(), threadPool.getCompletedTaskCount());
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        threadPool.shutdown();
                    client.endRequests(timeout);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[batchCount];
        for (int i = 0; i < batchCount; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for (int i = 0; i < batchCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getResponses(final List<BrokerClient<SoamSvc>> clientList) {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(batchCount * nbatchthreads, batchCount * nbatchthreads, 60, TimeUnit.SECONDS, queue);

        Thread[] threads = new Thread[batchCount];
        for (int i = 0; i < batchCount; i++) {
            final BrokerClient<SoamSvc> client = clientList.get(i);

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (final BrokerResponse<SoamInvokeResponse> response : client.<SoamInvokeResponse>getResponses(SoamInvokeResponse.class)) {
                            // getResult is also expensive, better parallel to improve performance
                            threadPool.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        MyOutput reply = new MyOutput();
                                        response.getResult().getSoamOutputObject(reply);
                                        //System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
                                    } catch (Exception ex) {
                                        System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                                    }
                                }
                            });
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < batchCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (threadPool.getActiveCount() > 0) {
            //System.out.printf("Active: %d Completed: %d\n", threadPool.getActiveCount(), threadPool.getCompletedTaskCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();

//        System.out.printf("close client...\n");
        for (int i = 0; i < batchCount; i++) {
            BrokerClient<SoamSvc> client = clientList.get(i);
            try {
                client.close(true);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private static int RunBasicTest() {
        int nerrs = 0;
        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);

        System.out.printf("Creating a session for %s...\n", serviceName);

        try {
            Session session = Session.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());

            BrokerClient<SoamSvc> client = new BrokerClient<SoamSvc>(session, SoamSvc.class);
            System.out.printf("Sending %d requests...\n", nrequests);
            for (int i = 0; i < nrequests; i++) {
            	MyInput input = new MyInput();
                ObjectFactory of = new ObjectFactory();
                SoamInvoke request = of.createSoamInvoke();
                request.setSoamInputObject(input);
                //System.out.printf("Sending %d / %d request...\n", i + 1, nrequests);
                client.sendRequest(request, i);
                System.out.printf("Sent %d / %d request...\n", i + 1, nrequests);
            }
            System.out.println("call endRequests() ...");
            client.endRequests();

            System.out.println("Retrieving responses...");

            for (BrokerResponse<SoamInvokeResponse> response : client.<SoamInvokeResponse>getResponses(SoamInvokeResponse.class)) {
                try {
                	MyOutput reply = new MyOutput();
                    response.getResult().getSoamOutputObject(reply);
                    System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
                } catch (Exception ex) {
                    nerrs++;
                    System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                }
            }
            System.out.printf("Done retrieving %d responses%n", nrequests);
            client.close();
            session.close();
        } catch (Throwable e) {
            nerrs++;
            e.printStackTrace();
        }
        return nerrs;
    }

    private static int RunResponseHandlerTest() {
        int nerrs = 0;
        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);
        System.out.printf("Creating a session for %s...\n", serviceName);

        try {
            final DurableSession session = DurableSession.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());

            BrokerClient<SoamSvc> client = new BrokerClient<SoamSvc>(session, SoamSvc.class);

            client.setResponseHandler(SoamInvokeResponse.class,
                    new ResponseListener<SoamInvokeResponse>() {
                        int fetchedCount = 0;

                        @Override
                        public void endOfMessage() {
                            synchronized (session) {
                                session.notify();
                            }
                        }

                        @Override
                        public void raiseError(Exception e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void responseReturned(BrokerResponse<SoamInvokeResponse> response) {
                            try {
                            	MyOutput reply = new MyOutput();
                                response.getResult().getSoamOutputObject(reply);
                                System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
                            } catch (Exception ex) {
                                System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                            }
                        }
                        
                    });

            System.out.printf("Sending %d requests...\n", nrequests);
            for (int i = 0; i < nrequests; i++) {
            	MyInput input = new MyInput();
                ObjectFactory of = new ObjectFactory();
                SoamInvoke request = of.createSoamInvoke();
                request.setSoamInputObject(input);
                client.sendRequest(request, i);
                System.out.printf("Sent %d / %d request...\n", i + 1, nrequests);
            }
            System.out.println("call endRequests() ...");
            client.endRequests();

            System.out.printf("Done retrieving %d responses%n", nrequests);
            synchronized (session) {
                session.wait();
            }
            session.close(true);
        } catch (Throwable e) {
            nerrs++;
            e.printStackTrace();
        }

        return nerrs;
    }
}
