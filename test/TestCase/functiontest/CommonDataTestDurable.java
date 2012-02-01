/**
 * 
 */
package functiontest;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.tempuri.AITestLibService;

import com.microsoft.hpc.scheduler.session.BrokerClient;
import com.microsoft.hpc.scheduler.session.DataClient;
import com.microsoft.hpc.scheduler.session.DataLifeCycle;
import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.SessionUnitType;
import com.microsoft.hpc.exceptions.DataException;

/**
 * @author mingqw
 * 
 */
public class CommonDataTestDurable {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("CommonDataTestDurable");
        logger = new Logger(true, true, "CommonDataTestDurable");
        String[] tmp = config.getValue("DataSizeToCheck").split(",");
        dataSizeToCheck = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            dataSizeToCheck[i] = Integer.parseInt(tmp[i]);
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        for (String dataClientId : DataClientHelper.CreatedDataClients) {
            try {
                DataClient.delete(dataClientId, config.Scheduler,
                        config.UserName, config.Password);
            } catch (Exception e) {
                // ignore error
            }
        }
        
    }

    @Test(timeout = 600000)
    // Simple send and get common data - one common data across the session",
    public final void V3_DataClient_E2E_1() throws SocketTimeoutException,
            SessionException, DataException, DatatypeConfigurationException,
            InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            CommonDataHelper.SimpleSendGetCommonData(logger, dataSize, true);
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - multiple common data for each batch
    // within one session - serviceHost concurrent read
    public final void V3_DataClient_E2E_2() throws SocketTimeoutException,
            SessionException, DataException, DatatypeConfigurationException,
            InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            DurableSession session = DurableSession.createSession(ssi);
            for (int i = 1; i <= 3; i++) {
                logger.Info("Round %s", i);
                CommonDataBytes data = CommonDataHelper
                        .GenerateCommonDataBytes(dataSize);
                DataClientHelper.WriteAll(String.format("data-%s", i),
                        data.bytes, session.getId(), true);
                BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                        String.valueOf(i), session, AITestLibService.class);
                DatatypeFactory df = DatatypeFactory.newInstance();
                CommonDataHelper.SendGetCommonData(logger, client, String
                        .format("data-%s", i), data.md5Hash, df.newDuration(0),
                        df.newDuration(1000));
            }
            session.close();
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);

        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - multiple common data for each batch
    // within one session - serviceHost read with random time
    public final void V3_DataClient_E2E_3() throws SocketTimeoutException,
            SessionException, DataException, DatatypeConfigurationException,
            InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            DurableSession session = DurableSession.createSession(ssi);
            for (int i = 1; i <= 3; i++) {
                logger.Info("Round %s", i);
                CommonDataBytes data = CommonDataHelper
                        .GenerateCommonDataBytes(dataSize);
                DataClientHelper.WriteAll(String.format("data-%s", i),
                        data.bytes, session.getId());
                BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                        String.valueOf(i), session, AITestLibService.class);
                CommonDataHelper.SendGetCommonData(logger, client, String
                        .format("data-%s", i), data.md5Hash, null, null);

            }
            session.close();
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - one common data across the sessions -
    // one by one
    public final void V3_DataClient_E2E_6() throws DataException,
            SocketTimeoutException, SessionException,
            DatatypeConfigurationException, InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            CommonDataBytes data = CommonDataHelper
                    .GenerateCommonDataBytes(dataSize);
            DataClient dataClient = DataClientHelper.Create();
            dataClient.writeRawBytesAll(data.bytes);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            DurableSession session = DurableSession.createSession(ssi);

            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            DatatypeFactory df = DatatypeFactory.newInstance();
            CommonDataHelper.SendGetCommonData(logger, client,
                    CommonDataHelper.defaultDataClientId, data.md5Hash, df
                            .newDuration(0), df.newDuration(1000));

            session.close();
            Thread.sleep(10 * 1000);
            session = DurableSession.createSession(ssi);
            client = new BrokerClient<AITestLibService>(session,
                    AITestLibService.class);
            CommonDataHelper.SendGetCommonData(logger, client,
                    CommonDataHelper.defaultDataClientId, data.md5Hash, df
                            .newDuration(0), df.newDuration(1000));

            session.close();

            DataClientHelper.Delete();

            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - one common data across the sessions -
    // node allocation
    public final void V3_DataClient_E2E_8() throws SocketTimeoutException,
            SessionException, DataException, DatatypeConfigurationException,
            InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            ssi.setResourceUnitType(SessionUnitType.Node);
            CommonDataHelper.SimpleSendGetCommonData(logger, ssi, dataSize,
                    false);
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - one common data across the sessions -
    // socket allocation",
    public final void V3_DataClient_E2E_9() throws SocketTimeoutException,
            SessionException, DataException, DatatypeConfigurationException,
            InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            ssi.setResourceUnitType(SessionUnitType.Socket);
            CommonDataHelper.SimpleSendGetCommonData(logger, ssi, dataSize,
                    false);
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - one common data across the session -
    // runas User
    public final void V3_DataClient_E2E_10() throws SocketTimeoutException,
            SessionException, DataException, DatatypeConfigurationException,
            InterruptedException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            ssi.setRunAsUsername(config.UserName2);
            ssi.setRunAsPassword(config.Password2);
            CommonDataHelper.SimpleSendGetCommonData(logger, ssi, dataSize,
                    false);
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    @Test(timeout = 600000)
    // Simple send and get common data - Common data read in static constructor
    public final void V3_DataClient_E2E_11() throws DataException,
            SocketTimeoutException, SessionException, InterruptedException,
            DatatypeConfigurationException {
    	logger.Start();
        for (int dataSize : dataSizeToCheck) {
            logger.Info("Test common data size as %sB", dataSize);
            CommonDataBytes data = CommonDataHelper
                    .GenerateCommonDataBytes(dataSize);
            SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                    config.ServiceName, config.UserName, config.Password);
            ssi.addEnvironment("ValidateCommonDataBytesInStaticContructor",
                    CommonDataHelper.defaultDataClientId + ":" + data.md5Hash);
            DataClient dataClient = DataClientHelper.Create();
            logger.Info("Write data");
            dataClient.writeRawBytesAll(data.bytes, true);
            logger.Info("Begin to create session");
            DurableSession session = DurableSession.createSession(ssi);
            logger.Info("Bind data with session %s", session.getId());
            dataClient.setDataLifeCycle(new DataLifeCycle(session.getId()));
            BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                    session, AITestLibService.class);
            CommonDataHelper.SendGetCommonData(logger, client,
                    config.NbOfCalls, CommonDataHelper.defaultDataClientId,
                    data.md5Hash, null, null,
                    CommonDataTestActionId.No_Read_Raw_PerReq);
            session.close();
            // Sleep some time for data cleanup before next session
            Thread.sleep(10 * 1000);
        }
        logger.End();
    }

    private static Config config;
    private static Logger logger;
    private static int[] dataSizeToCheck;
}
