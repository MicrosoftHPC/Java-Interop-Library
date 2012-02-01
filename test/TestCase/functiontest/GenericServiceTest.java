/**
 * 
 */
package functiontest;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.*;

import javax.xml.bind.JAXBElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.xml.security.utils.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.tempuri.Echo;
import org.tempuri.EchoResponse;

import com.microsoft.hpc.genericservice.*;
import com.microsoft.hpc.genericservice.ObjectFactory;
import com.microsoft.hpc.scheduler.session.*;
import java.io.*;
/**
 * @author yutongs
 * 
 */
public class GenericServiceTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = new Config("GenericService");
		logger = new Logger(true, true, "GenericService");
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
		// todo: check if the target cluster is in ready state
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// todo: cleanup work for each case
	}

	/**
	 * create a durable session, send requests and get responses
	 */

	@Test
	public void test_DurableGenericService() {

		logger.Start("test_DurableGenericService");
		String serviceName = config.getValue("ServiceName");

		SessionStartInfo info = new SessionStartInfo(config.Scheduler,
				serviceName, config.UserName, config.Password);
		logger.Info("Creating a %s durable session.", serviceName);

		DurableSession session = null;
		try {
			session = DurableSession.createSession(info);
			logger.Info("Session %d is created.", session.getId());
			BrokerClient<GenericService> client = new BrokerClient<GenericService>(session, GenericService.class);
			logger.Info("Sending generic operation requests...");
			
			ObjectFactory of = new ObjectFactory(); 
			GenericOperation request = new GenericOperation(); 
			
			
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			DataOutputStream doo = new DataOutputStream(bo);
			doo.writeInt(0); // high byte first which is different with .net one
			doo.writeInt(0);
			doo.flush();
			bo.flush();
			 
			String bs = Base64.encode(bo.toByteArray());
			logger.Info(bs);
			bo.close();
			request.setArgs(of.createGenericOperationArgs(bs));
			
			client.sendRequest(request,1);
			
			logger.Info("Call endRequests()...");
			client.endRequests();

			logger.Info("Retrieving responses...");
			for (BrokerResponse<GenericOperationResponse> response : client
					.getResponses(GenericOperationResponse.class)) {
				try {
					String reply = response.getResult().getGenericOperationResult().getValue();
					logger.Info("\tReceived response for request %s: %s",
							response.getUserData(), reply);
					
				} catch (Throwable ex) {
					logger.Error("Error in process request", ex);


				}

			}
			logger.Info("Done retrieving responses");
			client.close();
			
		} catch (Throwable e) {
			logger.Error("Exception is thrown ", e);
			
		}
		
		try {
			session.close();
		} catch (Throwable e) {
			logger.Error("Exception is thrown ", e);
			
		}
		
		logger.End("test_DurableGenericService");

	}

	
	
	/**
	 * create a durable session, send requests and get responses - boundary case
	 */

	@Test
	public void test_DurableGenericService_2() {

		logger.Start("test_DurableGenericService");
		String serviceName = config.getValue("ServiceName");

		SessionStartInfo info = new SessionStartInfo(config.Scheduler,
				serviceName, config.UserName, config.Password);
		logger.Info("Creating a %s durable session.", serviceName);

		DurableSession session = null;
		try {
			session = DurableSession.createSession(info);
			logger.Info("Session %d is created.", session.getId());
			BrokerClient<GenericService> client = new BrokerClient<GenericService>(session, GenericService.class);
			logger.Info("Sending generic operation requests...");
			
			ObjectFactory of = new ObjectFactory(); 
			GenericOperation request = new GenericOperation(); 
			
			
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			DataOutputStream doo = new DataOutputStream(bo);
			doo.writeInt(1); // high byte first which is different with .net one
			doo.writeChars("hello");
			doo.flush();
			bo.flush();
			String bs = Base64.encode(bo.toByteArray());
			logger.Info(bs);
			bo.close();
			request.setArgs(of.createGenericOperationArgs(bs));
			
			client.sendRequest(request,1);
			
			logger.Info("Call endRequests()...");
			client.endRequests();

			logger.Info("Retrieving responses...");
			for (BrokerResponse<GenericOperationResponse> response : client
					.getResponses(GenericOperationResponse.class)) {
				try {
					String reply = response.getResult().getGenericOperationResult().getValue();
					logger.Info("\tReceived response for request %s: %s",
							response.getUserData(), reply);
					
				} catch (SOAPFaultException ee){
					logger.Info("EE is thrown. ", ee);
					
				}
				catch (Throwable ee) {
					logger.Error("Error in process request ", ee);
					

				}

			}
			logger.Info("Done retrieving responses");
			client.close();
			
		} catch (Throwable e) {
			logger.Error("Exception is thrown ", e);
			
		}
		
		try {
			session.close();
		} catch (Throwable e) {
			logger.Error("Exception is thrown ", e);
			
		}
		
		logger.End("test_DurableGenericService");

	}

	
	private static Config config;
	private static Logger logger;

}
