// THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF
// ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// Copyright (c) Microsoft Corporation. All rights reserved.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
// This namespace is defined in the HPC Server 2008 R2 SDK
// which includes the HPC SOA Session API.   
using Microsoft.Hpc.Scheduler.Session;
// This namespace is defined in the "SoamInvokeService" SoamInvokeClientProxy.cs
using SoamService;
using System.ServiceModel;
using System.Threading;
using System.Net;

namespace SampleClient
{
    class Program
    {
        //change the headnode name here
        const string headnode = "SOATEST-HN";
        const string serviceName = "SoamSvcLinux";
        const int numRequests = 100000;
        const int numBatchs = 32;
        const int numBatchRequests = numRequests / numBatchs;

        public static void Main(string[] args)
        {
            // improve http performance
            ServicePointManager.DefaultConnectionLimit = 1000;
            ServicePointManager.Expect100Continue = false;
            ServicePointManager.UseNagleAlgorithm = false;

            SessionStartInfo info = new SessionStartInfo(headnode, serviceName);
            info.Username = "hpc\\hpcadmin";
            info.Password = "!!123abc";
            info.TransportScheme = TransportScheme.Http;
            info.UseAzureQueue = false;
            info.Secure = false;

            Console.Write("Creating a session for SoamInvokeService...");

            List<BrokerClient<ISoamSvc>> clientList = new List<BrokerClient<ISoamSvc>>();

            // Create a durable session 
            // Request and response messages in a durable session are persisted so that
            // in event of failure, no requests nor responses will be lost.  Another authorized
            // client can attached to a session with the same session Id and retrieve responses
            using (Session session = Session.CreateSession(info))
            {
                Console.WriteLine("done session id = {0}", session.Id);
                //NetTcpBinding binding = new NetTcpBinding(SecurityMode.Transport);

                Console.WriteLine("Sending {0} requests...", numRequests);
                DateTime timeMark1 = DateTime.Now;

                SendRequests(session, clientList);

                DateTime timeMark2 = DateTime.Now;
                double elapsedTimeSec = (timeMark2 - timeMark1).TotalMilliseconds / 1000.0;
                Console.WriteLine("Done sending {0} requests ... throughput={1}", numRequests, numRequests / elapsedTimeSec);

                Console.WriteLine("Retrieving responses...");

                GetResponses(clientList);

                DateTime timeMark3 = DateTime.Now;
                elapsedTimeSec = (timeMark3 - timeMark2).TotalMilliseconds / 1000.0;
                Console.WriteLine("Done retrieving {0} responses ... throughput={1}", numRequests, numRequests / elapsedTimeSec);

                elapsedTimeSec = (timeMark3 - timeMark1).TotalMilliseconds / 1000.0;
                Console.WriteLine("Total throughput={0}", numRequests / elapsedTimeSec);

                //explict close the session to free the resource
                session.Close();
            }

            Console.WriteLine("Press any key to exit.");
            Console.ReadKey();
        }

        public static void SendRequests(Session session, List<BrokerClient<ISoamSvc>> clientList)
        {
            ThreadStart runnalbe = new ThreadStart(() =>
            {

                // Create a BrokerClient proxy
                // This proxy is able to map One-Way, Duplex message exchange patterns 
                // with the Request / Reply Services.  As such, the client program can send the
                // requests, exit and re-attach to the session to retrieve responses (see the 
                // FireNRecollect project for details
                String clientId = Guid.NewGuid().ToString();
                BrokerClient<ISoamSvc> client = new BrokerClient<ISoamSvc>(clientId, session);
                clientList.Add(client);

                for (int i = 0; i < numBatchRequests; i++)
                {
                    // SoamInvokeRequest are created as you add Service Reference
                    // SoamInvokeService to the project
                    MyInput input = new MyInput();
                    SoamInvokeRequest request = new SoamInvokeRequest();
                    request.SetSoamInputObject(input);
                    client.SendRequest<SoamInvokeRequest>(request, i);
                    //Console.WriteLine("\tSent request {0}: {1}", i, input);
                }

                // Flush the message.  After this call, the runtime system
                // starts processing the request messages.  If this call is not called,
                // the system will not process the requests.  The client.GetResponses() will return
                // with an empty collection
                client.EndRequests();
            });

            Thread[] threads = new Thread[numBatchs];
            for (int i = 0; i < numBatchs; i++)
            {
                threads[i] = new Thread(runnalbe);
                threads[i].Start();
            }

            for (int i = 0; i < numBatchs; i++)
            {
                try
                {
                    threads[i].Join();
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                }
            }
        }

        private static void GetResponses(List<BrokerClient<ISoamSvc>> clientList)
        {
            Thread[] threads = new Thread[numBatchs];
            for (int i = 0; i < numBatchs; i++)
            {
                BrokerClient<ISoamSvc> client = clientList[i];

                threads[i] = new Thread(new ThreadStart(() =>
                {
                    // GetResponses from the runtime system
                    // SoamInvokeResponse class is created as you add Service Reference "SoamInvokeService"
                    // to the project
                    foreach (var response in client.GetResponses<SoamInvokeResponse>())
                    {
                        try
                        {
                            MyOutput reply = new MyOutput();
                            response.Result.GetSoamOutputObject(reply);
                            //Console.WriteLine("\tReceived response for request {0}: {1}", response.GetUserData<int>(), reply);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine("Error occured while processing {0}-th request: {1}", response.GetUserData<int>(), ex.Message);
                        }
                    }

                }));
                threads[i].Start();
            }

            for (int i = 0; i < numBatchs; i++)
            {
                try
                {
                    threads[i].Join();
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                }
            }

            for (int i = 0; i < numBatchs; i++)
            {
                BrokerClient<ISoamSvc> client = clientList[i];
                try
                {
                    client.Close(true);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                }
            }        
        }

    }

}
