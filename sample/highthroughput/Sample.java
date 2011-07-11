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

import org.tempuri.*;

import com.microsoft.hpc.scheduler.session.*;

public class Sample
{
    public static String username = "";
    public static String password = "";  
    private static String headnode = "";
    private static String serviceName = "CcpEchoSvc";
    private static int requestCount = 32;
    private static int batchCount = 8;
    private static int timeout = 3600; // 1 hour
    private static ThreadPoolExecutor threadPool = null;
    final static ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(409600);

    public static void main(String[] args)
    {
        int nerrs = 0;
        nerrs += ParseCmdLine(args);
        threadPool = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, queue);
        if (nerrs == 0) {
            try {
                nerrs += RunTests();                                
            }
            catch(Exception e) {
                nerrs++;
                e.printStackTrace();
            }
        }
        System.out.println("TotalErrors: " + nerrs);        
    }
    
    private static void Usage()
    {
        System.out.println("Usage: HelloWorld /scheduler scheduler /username user /password pwd /nrequests N");
    }


    private static int ParseCmdLine(String [] args)
    {
        int nerrs = 0;
        int i = 0;
        String arg;
        while (i < args.length && args[i].startsWith("/")) {
            arg = args[i++];
            if (arg.equals("/scheduler")) {
                headnode = args[i++];
            }
            else if (arg.equals("/username")) {
                username = args[i++];
            }
            else if (arg.equals("/password")) {
                password = args[i++];
            }
            else if (arg.equals("/nrequests")) {
                requestCount = Integer.parseInt(args[i++]);
            }           
            else {
                Usage();
                nerrs++;
                break;
            }
        }

        return nerrs;
    }


    private static int RunTests()
    {
        int nerrs = 0;
        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);    
        System.out.printf("Creating a session for %s...\n", serviceName);
        List<BrokerClient<CcpEchoSvc>> clientList = new ArrayList<BrokerClient<CcpEchoSvc>>(); 
        try
        {
            DurableSession session = DurableSession.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());
                    
            sendRequest(session, clientList);
            
            getResponse(clientList);
            
            session.close();
        } 
        catch (Throwable e)
        {
            nerrs++;
            e.printStackTrace();
        }       
        
        return nerrs;
    }
    
    private static void sendRequest(final SessionBase session, final List<BrokerClient<CcpEchoSvc>> clientList)
    {
        System.out.printf("Sending %d requests...\n", requestCount);
        Runnable runnable = new Runnable () {
            public void run() {
                int msgCount = requestCount / batchCount;
                String clientId = UUID.randomUUID().toString(); 
                BrokerClient<CcpEchoSvc> client = new BrokerClient<CcpEchoSvc>(
                clientId, session, CcpEchoSvc.class);
                clientList.add(client);
                // Send request
                System.out.printf("Client %s: Begin to send request.\n", clientId);
                sendRequest(client, msgCount);
                System.out.printf("Client %s: Begin to call EndOfMessage.\n", clientId);

                try {
                    client.endRequests(timeout);
                } catch (SocketTimeoutException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SessionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                System.out.printf("Client %s: EndOfMessage done.\n", clientId);
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    private static void sendRequest(BrokerClient<CcpEchoSvc> client, int msgCount) 
    {
        try {
            for(int i = 0; i < msgCount; i++)
            {
                ObjectFactory of = new ObjectFactory();
                Echo request = of.createEcho();
                request.setInput(of.createEchoInput("hello world!"));
                client.sendRequest(request, i);
            }
        } catch (SessionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void getResponse(final List<BrokerClient<CcpEchoSvc>> clientList) 
    {
        System.out.println("Retrieving responses...");

        Thread[] threads = new Thread[batchCount];
        for(int i = 0; i < batchCount; i++) 
        {
            final BrokerClient<CcpEchoSvc> client = clientList.get(i);
            threads[i] = new Thread(new Runnable() 
            {

                @Override
                public void run() {
                    try {
                        for(final BrokerResponse<EchoResponse> response : client.<EchoResponse>getResponses(EchoResponse.class))
                        {
                            // getResult is also expensive, better parallel to improve performance
                            threadPool.execute(new Runnable()
                            {

                                @Override
                                public void run() {
                                    try
                                    {
                                        String reply = response.getResult().getEchoResult().getValue();
                                        System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
                                    }
                                    catch(Exception ex)
                                    {
                                        System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                                    }
                                }
                            });
                         
                        }
                    } catch (SessionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
            });
            threads[i].start();
        }
        for(int i = 0; i < batchCount; i++)
        {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();    
            }
        }
        while(threadPool.getActiveCount() > 0) {
            System.out.printf("Active: %d Completed: %d\n", threadPool.getActiveCount(), threadPool.getCompletedTaskCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        
        System.out.printf("close client...\n");
        for (int i = 0; i < clientList.size(); i++) {
            BrokerClient<CcpEchoSvc> client = clientList.get(i);
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
    }
}
