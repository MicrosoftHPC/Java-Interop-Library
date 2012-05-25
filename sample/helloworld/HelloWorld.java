//------------------------------------------------------------------------------
// <copyright file="HelloWorld.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Simple SOA test using Java.
// </summary>
//------------------------------------------------------------------------------

import java.util.Random;

import org.tempuri.*;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

import com.microsoft.hpc.scheduler.session.*;

public class HelloWorld
{
    public static String username = ""; // valid username to submit SOA job
    public static String password = "";  // valid user password 
    private static String headnode = ""; // HPC cluster headnode hostname
    private static String serviceName = "JavaEchoSvc";
    private static int nrequests = 12;

    public static void main(String[] args)
    {
        int nerrs = 0;
        nerrs += ParseCmdLine(args);
        if (nerrs == 0) {
            try {
                nerrs += RunBasicTest();   
                nerrs += RunCommonDataTest();
                nerrs += RunResponseHandlerTest();
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
                nrequests = Integer.parseInt(args[i++]);
            }           
            else {
                Usage();
                nerrs++;
                break;
            }
        }

        return nerrs;
    }


    private static int RunBasicTest()
    {
        int nerrs = 0;
        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);    
        System.out.printf("Creating a session for %s...\n", serviceName);
        
        try
        {
            DurableSession session = DurableSession.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());
            
            BrokerClient<CcpEchoSvc> client = new BrokerClient<CcpEchoSvc>(session, CcpEchoSvc.class);
            System.out.printf("Sending %d requests...\n", nrequests);
            for(int i = 0; i < nrequests; i++)
            {
                ObjectFactory of = new ObjectFactory();
                Echo request = of.createEcho();
                request.setInput(of.createEchoInput("hello world!"));
                client.sendRequest(request, i);
            }
            System.out.println("call endRequests() ...");
            client.endRequests();       
            
            System.out.println("Retrieving responses...");
            
            for(BrokerResponse<EchoResponse> response : client.<EchoResponse>getResponses(EchoResponse.class))
            {
                try
                {
                    String reply = response.getResult().getEchoResult().getValue();
                    System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
                }
                catch(Exception ex)
                {
                    nerrs++;
                    System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                }
            }
            System.out.printf("Done retrieving %d responses%n", nrequests);
            client.close();
            session.close();
        }
        catch (Throwable e)
        {
            nerrs++;
            e.printStackTrace();
        }       
        return nerrs;
    }

    private static int RunCommonDataTest()
    {
        int nerrs = 0;
        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);    
        System.out.printf("Creating a session for %s...\n", serviceName);
        
        try
        {
            DurableSession session = DurableSession.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());
            
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
            
            BrokerClient<CcpEchoSvc> client = new BrokerClient<CcpEchoSvc>(session, CcpEchoSvc.class);
            System.out.printf("Sending %d requests...\n", nrequests);
            for(int i = 0; i < nrequests; i++)
            {
                ObjectFactory of = new ObjectFactory();
                EchoData request = of.createEchoData();
                request.setDataClientId(of.createEchoDataDataClientId(dataClientId));
                client.sendRequest(request, i);
            }
            System.out.println("call endRequests() ...");
            client.endRequests();       
            
            System.out.println("Retrieving responses...");
            
            for(BrokerResponse<EchoDataResponse> response : client.<EchoDataResponse>getResponses(EchoDataResponse.class))
            {
                try
                {
                    int reply = response.getResult().getEchoDataResult();
                    System.out.printf("\tReceived response for request %s: %d%n", response.getUserData(), reply);
                }
                catch(Exception ex)
                {
                    nerrs++;
                    System.out.printf("Error: process %s-th reuqest: %s%n", response.getUserData(), ex.toString());
                }
            }
            System.out.printf("Done retrieving %d responses%n", nrequests);
            client.close();
            session.close();
        }
        catch (Throwable e)
        {
            nerrs++;
            e.printStackTrace();
        }       
        return nerrs;
    }

    
    private static int RunResponseHandlerTest()
    {
        int nerrs = 0;
        SessionStartInfo info = new SessionStartInfo(headnode, serviceName, username, password);    
        System.out.printf("Creating a session for %s...\n", serviceName);
        
        try
        {
            final DurableSession session = DurableSession.createSession(info);
            System.out.printf("new session id = %d\n", session.getId());
                    
            BrokerClient<CcpEchoSvc> client = new BrokerClient<CcpEchoSvc>(session, CcpEchoSvc.class);

	    client.setResponseHandler(EchoResponse.class,
	    				new ResponseListener<EchoResponse>() {
						int fetchedCount = 0;

						@Override
						public void endOfMessage() {
							synchronized(session) {
								session.notify();
							}
						}

						@Override
						public void raiseError(Exception e) {
							e.printStackTrace();
						}

						@Override
						public void responseReturned(BrokerResponse<EchoResponse> response) {
							try {
                    						String reply = response.getResult().getEchoResult().getValue();
                    						System.out.printf("\tReceived response for request %s: %s%n", response.getUserData(), reply);
							} catch(SOAPFaultException e) {
								e.printStackTrace();
							} catch(SOAPException e) {
								e.printStackTrace();
							}

						}
							
					});

            System.out.printf("Sending %d requests...\n", nrequests);
            for(int i = 0; i < nrequests; i++)
            {
                ObjectFactory of = new ObjectFactory();
                Echo request = of.createEcho();
                request.setInput(of.createEchoInput("hello world!"));
                client.sendRequest(request, i);
            }
            System.out.println("call endRequests() ...");
            client.endRequests();       
            
            System.out.println("Retrieving responses...");
            
            System.out.printf("Done retrieving %d responses%n", nrequests);
	    synchronized(session) {
	    	session.wait();
	    }
            session.close(true);
        } 
        catch (Throwable e)
        {
            nerrs++;
            e.printStackTrace();
        }       
        
        return nerrs;
    }
   
}
