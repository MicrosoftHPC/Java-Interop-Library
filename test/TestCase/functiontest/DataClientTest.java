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
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.WebServiceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.microsoft.hpc.scheduler.session.DataClient;
import com.microsoft.hpc.scheduler.session.SessionException;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.exceptions.DataErrorCode;
import com.microsoft.hpc.exceptions.DataException;

/**
 * @author mingqw
 * 
 */
public class DataClientTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        config = new Config("CommonDataTest");
        logger = new Logger(true, true, "DataClientTest");

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
        logger.Start("DataClientTest");
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
        DataClientHelper.CreatedDataClients.clear();
        logger.End("DataClientTest");
    }

    @Test(timeout = 600000)
    // headnode=NULL
    public final void DataClient_API_Create_1() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, null,
                    config.UserName, config.Password);
            logger.Error("No error got which is not expected");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }

    }

    @Test(timeout = 600000)
    // headnode=string.Empty
    public final void DataClient_API_Create_2() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, "", config.UserName,
                    config.Password);
            logger.Error("No error got which is not expected");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }

    }

    @Test(timeout = 600000)
    // headnode is not existed
    public final void DataClient_API_Create_3() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, "NotExistHeadNode",
                    config.UserName, config.Password);
            logger.Error("No error got which is not expected");
        } catch (WebServiceException e) {
            logger.Info("WebException when headnode not exist: %s", e
                    .getMessage());
        }
    }

    // @Test
    // HpcSession is down
    // public final void DataClient_API_Create_4() throws IOException,
    // InterruptedException
    // {
    // logger.Info("Test HpcSession of the headnode is down");
    // CommonDataHelper.runCmd(config.Scheduler, config.UserName,
    // config.Password, "net", "stop", "HpcSession");
    // try
    // {
    // DataClient dataClient = DataClientHelper.Create();
    // logger.Error("No error got which is not expected");
    // }
    // catch (DataException e)
    // {
    // // Assert(e.ErrorCode == DataErrorCode.GetDataServerInfoFailure,
    // // "Unexpected DataException error code: %s", e.ErrorCode);
    // logger.Info("DataException when session launcher is not started: %s",
    // e.getMessage());
    // }
    // CommonDataHelper.runCmd(config.Scheduler, config.UserName,
    // config.Password, "net", "start", "HpcSession");
    //
    // }

    @Test(timeout = 600000)
    // dataClientId=NULL
    public final void DataClient_API_Create_5() throws DataException {
        try {
            DataClient dataClient = DataClientHelper.Create(null);
            logger.Error("No error got which is not expected");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }
    }

    @Test(timeout = 600000)
    // dataClientId=string.Empty
    public final void DataClient_API_Create_6() throws DataException {
        try {
            DataClient dataClient = DataClientHelper.Create("");
            logger.Error("No error got which is not expected");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }
    }

    @Test(timeout = 600000)
    // valid dataClientId",
    public final void DataClient_API_Create_7() {
        for (String dataClientId : CommonDataHelper.validDataClientId) {
            logger.Info("Try to use DataClientId %s", dataClientId);
            try {
                DataClient dataClient = DataClientHelper.Create(dataClientId);
                dataClient.writeRawBytesAll(CommonDataHelper.simpleCommonData);
                byte[] rtn = dataClient.readRawBytesAll();
                logger.assertTrue("Unexpected return of the common data",
                        Arrays.equals(CommonDataHelper.simpleCommonData, rtn));
                DataClientHelper.Delete(dataClientId);
            } catch (Exception e) {
                e.printStackTrace();
                logger.Error("Unexpected exception: %s", e.getMessage());
            }
        }
    }

    @Test(timeout = 600000)
    // DataClient.Create - invalid dataClientId
    public final void DataClient_API_Create_8() {
        for (String dataClientId : CommonDataHelper.invalidDataClientId) {
            try {
                logger.Info("Try to use DataClientId %s", dataClientId);
                DataClient dataClient = DataClientHelper.Create(dataClientId);
                logger.Error("No error got which is not expected");
            } catch (IllegalArgumentException e) {
                logger.Info("IllegalArgumentException got as expected: %s", e
                        .getMessage());
            } catch (Exception e) {
                logger.Error("Unexpected exception: %s", e.getMessage());
            }
        }
    }

    @Test(timeout = 600000)
    // DataClient.Create - username=NULL
    public final void DataClient_API_Create_9() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, config.Scheduler,
                    null, config.Password);
            logger.Error("No error got which is not expected");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }
    }

    @Test(timeout = 600000)
    // DataClient.Create - username is empty
    public final void DataClient_API_Create_10() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, config.Scheduler, "",
                    config.Password);
            logger.Error("No error got which is not expected");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }
    }

    @Test(timeout = 600000)
    // DataClient.Create - password=NULL
    public final void DataClient_API_Create_11() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, config.Scheduler,
                    config.UserName, null);
            logger.Error("No error got which is not expected");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }
    }

    @Test(timeout = 600000)
    // DataClient.Create - password is empty
    public final void DataClient_API_Create_12() throws DataException {
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, config.Scheduler,
                    config.UserName, "");
            logger.Error("No error got which is not expected");
        } catch (javax.xml.ws.soap.SOAPFaultException e) {
            logger.Info(
                    "javax.xml.ws.soap.SOAPFaultException got as expected: %s",
                    e.toString());
        }
    }

    @Test(timeout = 600000)
    // DataClient.Create - create two DataClient with the same name
    public final void DataClient_API_Create_13() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            DataClient dataClient2 = DataClientHelper.Create();
            logger.Error("No error got which is not expected");
        } catch (DataException e) {
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientAlreadyExists
                            .getCode());
            logger.Info("DataException when data client already exists: %s", e
                    .getMessage());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll - object is NULL
    public final void DataClient_API_WriteRaw_1() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            dataClient.writeRawBytesAll(null);
            logger.Error("No error which is not expected.");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll - call WriteAll twice
    public final void DataClient_API_WriteRaw_2() throws DataException {
        DataClient dataClient = DataClientHelper.Create();

        CommonDataBytes data = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(data.bytes);
        try {
            dataClient.writeRawBytesAll(data.bytes);
            logger.Error("No error which is not expected");
        } catch (DataException e) {
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientNotWritable
                            .getCode());
            logger.Info("DataException when data client already exists: %s", e
                    .getMessage());
        }
        DataClientHelper.Delete();
    }

    
    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll - Should not write with the same dataClientId
    // if bind with different session Id
    public final void DataClient_API_WriteRaw_6() throws IllegalStateException,
            DataException {
        CommonDataBytes data = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        DataClient dataClient = DataClientHelper.Create(
                CommonDataHelper.defaultDataClientId, 1);
        dataClient.writeRawBytesAll(data.bytes);
        try {
            DataClient dataClient2 = DataClientHelper.Create(
                    CommonDataHelper.defaultDataClientId, 2);
            dataClient2.writeRawBytesAll(data.bytes);
            logger.Error("No error which is not expected.");
        } catch (DataException e) {
            logger.Info("Data exception got: %s", e.getMessage());
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientAlreadyExists
                            .getCode());

        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.ReadRawBytesAll - Read before write
    public final void DataClient_API_ReadRaw_1() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            byte[] rtn = dataClient.readRawBytesAll();
        } catch (DataException e) {
            logger
                    .assertTrue(String.format(
                            "Unexpected DataException error code: %s", e
                                    .getErrorCode()),
                            e.getErrorCode() == DataErrorCode.NoDataAvailable
                                    .getCode());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.ReadRawBytesAll - Multiple read
    public final void DataClient_API_ReadRaw_2() throws IllegalStateException,
            DataException {
        DataClient dataClient = DataClientHelper.Create();
        CommonDataBytes data = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(data.bytes);
        for (int i = 0; i < 10; i++) {
            byte[] rtn = dataClient.readRawBytesAll();
            logger.assertTrue("Common data is corrupted", CommonDataHelper
                    .ValidateCommonDataBytes(rtn, data.md5Hash));
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.ReadRawBytesAll - Read after deleted
    public final void DataClient_API_ReadRaw_4() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        CommonDataBytes data = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(data.bytes);
        DataClientHelper.Delete();
        try {
            byte[] rtn = dataClient.readRawBytesAll();
            logger.Error("No error which is not expected");
        } catch (DataException e) {
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientDeleted
                            .getCode());

        }

    }

    

    @Test(timeout = 600000)
    // DataClient.ReadRawBytesAll - Read after deleted
    public final void DataClient_API_ReadRaw_6() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        DataClient dataClient2 = DataClientHelper.Open();
        CommonDataBytes data = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(data.bytes);
        DataClientHelper.Delete();
        try {
            byte[] rtn = dataClient2.readRawBytesAll();
            logger.Error("No error which is not expected");
            DataClientHelper.Delete();
        } catch (DataException e) {
            logger.Info("DataException got: %s", e.getMessage());
            logger.assertTrue(String.format(
                    "Unexpected DataException error code: %s", DataErrorCode
                            .getEnum(e.getErrorCode())),
                    e.getErrorCode() == DataErrorCode.DataClientDeleted
                            .getCode());
        }

    }

    @Test(timeout = 600000)
    // DataClient.ReadRawBytesAll - Read raw from write raw could be
    // serializable
    public final void DataClient_API_ReadRaw_7() throws IOException,
            DataException, ClassNotFoundException {
        String object = "test";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.close();
        byte[] buf = bos.toByteArray();

        DataClient dataClient = DataClientHelper.Create();
        dataClient.writeRawBytesAll(buf);
        byte[] rtnBytes = dataClient.readRawBytesAll();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
                rtnBytes));
        String rtn = (String) in.readObject();

        logger.assertTrue("Common data is corrupted.", rtn.equals("test"));

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll - Could not write empty byte
    public void DataClient_API_WriteRaw_8() throws DataException {
        byte[] data = new byte[0];
        try {
            DataClient dataClient = DataClientHelper.Create();
            dataClient.writeRawBytesAll(data);
            logger.Error("No error which is not expected.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll - Could not write empty byte
    public void DataClient_API_WriteRaw_9() throws DataException {
        byte[] data = new byte[0];
        try {
            DataClient dataClient = DataClientHelper.Create();
            dataClient.writeRawBytesAll(data, true);
            logger.Error("No error which is not expected.");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll - Unauthorized write
    public void DataClient_API_WriteRaw_10() throws DataException {
        byte[] data = new byte[] { 0 };
        try {
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, config.Scheduler,
                    config.UserName2, config.Password2);
            dataClient.writeRawBytesAll(data);
            logger.Error("No error which is not expected.");
        } catch (AccessControlException e) {
            logger.Info("AccessControlException got as expected: %s", e
                    .getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected Exception: %s", e.toString());
        }
        DataClient.delete(CommonDataHelper.defaultDataClientId,
                config.Scheduler, config.UserName2, config.Password2);
    }

    @Test(timeout = 600000)
    // DataClient.Open - headnode=NULL
    public final void DataClient_API_Open_1() throws DataException {
        DataClient dataClient = DataClientHelper.Create();

        try {
            DataClient dataClient2 = DataClient.open(
                    CommonDataHelper.defaultDataClientId, null,
                    config.UserName, config.Password);
            logger.Error("No error got which is not expected");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.Open - headnode=string.Empty
    public final void DataClient_API_Open_2() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            DataClient dataClient2 = DataClient.open(
                    CommonDataHelper.defaultDataClientId, "", config.UserName,
                    config.Password);
            logger.Error("No error got which is not expected");
        }

        catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.Open - headnode is not existed
    public final void DataClient_API_Open_3() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            DataClient dataClient2 = DataClient.open(
                    CommonDataHelper.defaultDataClientId, "NotExistHeadNode",
                    config.UserName, config.Password);
            logger.Error("No error got which is not expected");
        } catch (WebServiceException e) {
            logger.Info("WebException when headnode not exist: %s", e
                    .getMessage());
        }

        DataClientHelper.Delete();
    }

    // to do
    // [TestCase("DataClient_API_Open_4",
    // Description = "DataClient.Open - HpcSession of the headnode is down",
    // Category = "CommonData")]
    // public void DataClient_API_Open_4()
    // {
    // using (DataClient dataClient = DataClientHelper.Create())
    // {
    // Info("Test HpcSession of the headnode is down");
    // ReliabilityHelper.stopService(Config.server, "HpcSession",
    // Config.userName, Config.passWord);
    // try
    // {
    // DataClient dataClient2 = DataClient.Open(Config.server,
    // CommonDataHelper.defaultDataClientId);
    // Error("No error got which is not expected");
    // }
    // catch (DataException e)
    // {
    // Assert(e.ErrorCode == DataErrorCode.GetDataServerInfoFailure,
    // "Unexpected DataException error code: %s", e.ErrorCode);
    // Info("DataException when session launcher is not started: %s",
    // e.Message);
    // }
    // ReliabilityHelper.startService(Config.server, "HpcSession",
    // Config.userName, Config.passWord);
    // }
    // DataClient.Delete(Config.server, CommonDataHelper.defaultDataClientId);
    // }

    @Test(timeout = 600000)
    // DataClient.Open - dataClientId=NULL
    public final void DataClient_API_Open_5() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            DataClient dataClient2 = DataClient.open(null, config.Scheduler,
                    config.UserName, config.Password);
            logger.Error("No error got which is not expected");
        } catch (NullPointerException e) {
            logger.Info("NullPointerException got as expected: %s", e
                    .getMessage());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.Open - dataClientId=string.Empty
    public final void DataClient_API_Open_6() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            DataClient dataClient2 = DataClient.open("", config.Scheduler,
                    config.UserName, config.Password);
            logger.Error("No error got which is not expected");
        } catch (IllegalArgumentException e) {
            logger.Info("IllegalArgumentException got as expected: %s", e
                    .getMessage());
        }

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.Open - valid dataClientId
    public final void DataClient_API_Open_7() throws DataException {
        for (String dataClientId : CommonDataHelper.validDataClientId) {
            DataClient dataClient = DataClientHelper.Create(dataClientId);
            dataClient.writeRawBytesAll(CommonDataHelper.simpleCommonData);
            logger.Info("Try to use DataClientId %s", dataClientId);
            try {
                DataClient dataClient2 = DataClientHelper.Open(dataClientId);
                byte[] rtn = dataClient2.readRawBytesAll();
                logger.assertTrue("Unexpected return of the common data",
                        Arrays.equals(CommonDataHelper.simpleCommonData, rtn));
            } catch (Exception e) {
                logger.Error("Unexpected exception: %s", e.getMessage());
            }
        }
        DataClientHelper.Delete();

    }

    @Test(timeout = 600000)
    // DataClient.Open - invalid dataClientId
    public void DataClient_API_Open_8() {

        for (String dataClientId : CommonDataHelper.invalidDataClientId) {
            try {
                logger.Info("Try to use DataClientId %s", dataClientId);
                DataClient dataClient = DataClientHelper.Open(dataClientId);
                logger.Error("No error got which is not expected");
            } catch (IllegalArgumentException e) {
                logger.Info("IllegalArgumentException got as expected: %s", e
                        .getMessage());
            } catch (Exception e) {
                logger.Error("Unexpected exception: %s", e.getMessage());
            }
        }
    }

    @Test(timeout = 600000)
    // DataClient.Open - Open dataClient which does not exist
    public final void DataClient_API_Open_9() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        try {
            DataClient dataClient2 = DataClientHelper.Open("test");
            logger.Error("No error got which is not expected");
        } catch (DataException e) {
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientNotFound
                            .getCode());
            logger
                    .Info(
                            "DataException when open dataClient which does not exist: %s",
                            e.getMessage());
        }

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.Open - multiple Open and Read
    public final void DataClient_API_Open_10() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        DataClient[] dataClients = new DataClient[10];
        dataClient.writeRawBytesAll(CommonDataHelper.simpleCommonData);
        for (int i = 0; i < 10; i++) {
            dataClients[i] = DataClientHelper.Open();
            byte[] rtn = (dataClients[i]).readRawBytesAll();
            logger.assertTrue("Common data is corrupted. ", Arrays.equals(rtn,
                    CommonDataHelper.simpleCommonData));
        }

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.Open - Open dataClient which has been closed
    public final void DataClient_API_Open_11() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        DataClientHelper.Delete();
        try {
            DataClient dataClient2 = DataClientHelper.Open();
            logger.Error("No error got which is not expected");
        } catch (DataException e) {
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientNotFound
                            .getCode());

            logger
                    .Info(
                            "DataException when open dataClient which does not exist: %s",
                            e.getMessage());
        }
    }

    @Test(timeout = 600000)
    // DataClient.Open - Opened dataClient is read only
    public final void DataClient_API_Open_12() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        DataClient dataClient2 = DataClientHelper.Open();
        try {
            dataClient2.writeRawBytesAll(CommonDataHelper.simpleCommonData);
        } catch (DataException e) {
            logger.assertTrue(String
                    .format("Unexpected DataException error code: %s", e
                            .getErrorCode()),
                    e.getErrorCode() == DataErrorCode.DataClientReadOnly
                            .getCode());
            logger.Info(
                    "DataException when wrting with a readonly dataClient: %s",
                    e.getMessage());
        }
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // CCP_SOADATASERVER - Invalid data server path - data server unreachable
    public final void DataClient_SOADataServer_1() throws IOException {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") == -1)
            return;
        String dataServer = "\\\\NotReachableHN\\DataServer";
        logger.Info("Try CCP_SOADATASERVER=%s", dataServer);
        String backup = CommonDataHelper.GetDataServer();
        CommonDataHelper.SetDataServer(dataServer);
        try {
            DataClient dataClient = DataClientHelper.Create();
        } catch (DataException e) {
            logger.assertTrue(String.format(
                    "Unexpected DataException error code: %s", DataErrorCode
                            .getEnum(e.getErrorCode())),
                    e.getErrorCode() == DataErrorCode.DataServerUnreachable
                            .getCode());
            logger
                    .Info(
                            "DataException when open dataClient which does not exist: %s",
                            e.getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
        CommonDataHelper.SetDataServer(backup);
    }

    @Test(timeout = 600000)
    // CCP_SOADATASERVER - Invalid data server path - data server is empty
    public final void DataClient_SOADataServer_2() throws IOException {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") == -1)
            return;
        String dataServer = "";
        logger.Info("Try CCP_SOADATASERVER=%s", dataServer);
        String backup = CommonDataHelper.GetDataServer();
        CommonDataHelper.SetDataServer(dataServer);
        try {
            DataClient dataClient = DataClientHelper.Create();
        } catch (DataException e) {
            logger.assertTrue(String.format(
                    "Unexpected DataException error code: %s", DataErrorCode
                            .getEnum(e.getErrorCode())),
                    e.getErrorCode() == DataErrorCode.NoDataServerConfigured
                            .getCode());
            logger
                    .Info(
                            "DataException when open dataClient which does not exist: %s",
                            e.getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
        CommonDataHelper.SetDataServer(backup);
    }

    @Test(timeout = 600000)
    // CCP_SOADATASERVER - Invalid data server path - multiple data server path
    public final void DataClient_SOADataServer_3() throws IOException {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") == -1)
            return;
        String backup = CommonDataHelper.GetDataServer();
        logger.Info("Try CCP_SOADATASERVER=%s", backup + ";" + backup);
        CommonDataHelper.SetDataServer(backup + ";" + backup);
        try {
            DataClient dataClient = DataClientHelper.Create();
        } catch (DataException e) {
            logger.assertTrue(String.format(
                    "Unexpected DataException error code: %s", DataErrorCode
                            .getEnum(e.getErrorCode())),
                    e.getErrorCode() == DataErrorCode.DataServerMisconfigured
                            .getCode());
            logger.Info("DataException when invalid data server path: %s", e
                    .getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
        CommonDataHelper.SetDataServer(backup);
    }

    @Test(timeout = 600000)
    // CCP_SOADATASERVER - Invalid data server path
    public void DataClient_SOADataServer_4() throws IOException {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") == -1)
            return;
        String dataServer = "!(&*#*#&*#&)#(";
        logger.Info("Try CCP_SOADATASERVER=%s", dataServer);
        String backup = CommonDataHelper.GetDataServer();
        CommonDataHelper.SetDataServer(dataServer);
        try {
            DataClient dataClient = DataClientHelper.Create();
        } catch (DataException e) {
            logger.assertTrue(String.format(
                    "Unexpected DataException error code: %s", DataErrorCode
                            .getEnum(e.getErrorCode())),
                    e.getErrorCode() == DataErrorCode.DataServerMisconfigured
                            .getCode());
            logger.Info("DataException when invalid data server path: %s", e
                    .getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
        CommonDataHelper.SetDataServer(backup);
    }

    @Test(timeout = 600000)
    // CCP_SOADATASERVER - Invalid data server path - local path
    public final void DataClient_SOADataServer_5() throws IOException {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") == -1)
            return;
        String dataServer = "c:\\data";
        logger.Info("Try CCP_SOADATASERVER=%s", dataServer);
        String backup = CommonDataHelper.GetDataServer();
        CommonDataHelper.SetDataServer(dataServer);
        try {
            DataClient dataClient = DataClientHelper.Create();
        } catch (DataException e) {
            logger.assertTrue(String.format(
                    "Unexpected DataException error code: %s", DataErrorCode
                            .getEnum(e.getErrorCode())),
                    e.getErrorCode() == DataErrorCode.DataServerMisconfigured
                            .getCode());
            logger.Info("DataException when invalid data server path: %s", e
                    .getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
        CommonDataHelper.SetDataServer(backup);
    }

    
    @Ignore
    // DataClient.Delete - Unauthorized delete
    public final void DataClient_API_Delete_3() throws IOException,
            DataException {
        try {
            CommonDataBytes data = CommonDataHelper
                    .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
            DataClient dataClient = DataClientHelper.Create();
            dataClient.writeRawBytesAll(data.bytes);
            int rtn = DataClient_API_Delete_2_Helper();
            logger.assertTrue("Case fail.", rtn == 1);
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
    }

    @Test(timeout = 600000)
    // DataClient.Delete - Authorized delete
    public final void DataClient_API_Delete_4() throws IOException,
            DataException {
        try {
            CommonDataBytes data = CommonDataHelper
                    .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
            DataClient dataClient = DataClient.create(
                    CommonDataHelper.defaultDataClientId, config.Scheduler,
                    config.UserName, config.Password,
                    new String[] { config.UserName2 });
            dataClient.writeRawBytesAll(data.bytes);
            int rtn = DataClient_API_Delete_2_Helper();
            logger.assertTrue("Case fail.", rtn == 0);
        } catch (Exception e) {
            logger.Error("Unexpected exception: %s", e.getMessage());
        }
    }

    public int DataClient_API_Delete_2_Helper() throws DataException {
        int rtn = -1;

        try {
            DataClient.delete(CommonDataHelper.defaultDataClientId,
                    config.Scheduler, config.UserName2, config.Password2);
            logger.Info("No error for DataClient.Delete");
            rtn = 0;
        } catch (AccessControlException e) {
            logger.Info("AccessControl got: %s", e.getMessage());
            rtn = 1;
        } catch (Exception e) {
            logger.Error("Unexpected Exception: %s", e.toString());
        }
        DataClientHelper.Delete();
        return rtn;
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll in compress
    public final void DataClient_API_WriteCompress_1() throws DataException {
        // use good compressible data per bug 12911
        byte[] commonData = new byte[CommonDataHelper.defaultSize];
        for (int i = 0; i < commonData.length; i++)
            commonData[i] = 0;
        String md5Hash = CommonDataHelper.Md5HashString(commonData);

        DataClient dataClient = DataClientHelper.Create();

        dataClient.writeRawBytesAll(commonData, true);
        byte[] rtn = dataClient.readRawBytesAll();
        long fileSize = CommonDataHelper
                .GetCommonDataFileSize(CommonDataHelper.defaultDataClientId);
        logger.Info("Common data size: %sB, acutal file size: %sB",
                CommonDataHelper.defaultSize, fileSize);
        logger.assertTrue("Data does not seem to be compressed.",
                fileSize < (long) CommonDataHelper.defaultSize);
        CommonDataHelper.ValidateCommonDataBytes(rtn, md5Hash);
        DataClientHelper.Delete();

    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll not in compress
    public final void DataClient_API_WriteCompress_2() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        CommonDataBytes commonData = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(commonData.bytes, false);
        byte[] rtn = dataClient.readRawBytesAll();
        long fileSize = CommonDataHelper
                .GetCommonDataFileSize(CommonDataHelper.defaultDataClientId);
        logger.Info("Common data size: %sB, acutal file size: %sB",
                CommonDataHelper.defaultSize, fileSize);
        logger.assertTrue("Data does not seem to be compressed.",
                fileSize >= (long) CommonDataHelper.defaultSize);
        CommonDataHelper.ValidateCommonDataBytes(rtn, commonData.md5Hash);
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.WriteRawBytesAll not in compress (by default)",
    public final void DataClient_API_WriteCompress_3() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        CommonDataBytes commonData = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(commonData.bytes);
        byte[] rtn = dataClient.readRawBytesAll();
        long fileSize = CommonDataHelper
                .GetCommonDataFileSize(CommonDataHelper.defaultDataClientId);
        logger.Info("Common data size: %sB, acutal file size: %sB",
                CommonDataHelper.defaultSize, fileSize);
        logger.assertTrue("Data does not seem to be compressed.",
                fileSize >= (long) CommonDataHelper.defaultSize);
        CommonDataHelper.ValidateCommonDataBytes(rtn, commonData.md5Hash);
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.GetFilePath - no data written
    public final void DataClient_API_FilePath_1() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        String filePath = dataClient.getStorePath();
        logger.Info("The data file path is %s", filePath);

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataClient.GetFilePath - data deleted
    public final void DataClient_API_FilePath_2() throws DataException {
        DataClient dataClient = DataClientHelper.Create();
        CommonDataBytes data = CommonDataHelper
                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
        dataClient.writeRawBytesAll(data.bytes);
        DataClientHelper.Delete();
        logger
                .Info("Still should be able to get common data file path after the data is deleted");
        String filePath = dataClient.getStorePath();
    }

    @Test(timeout = 600000)
    // DataClient.GetFilePath - data could be read by simple file solution
    public final void DataClient_API_FilePath_3() throws IOException,
            DataException, ClassNotFoundException {
        String object = "test";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.close();
        byte[] buf = bos.toByteArray();
        DataClient dataClient = DataClientHelper.Create();
        dataClient.writeRawBytesAll(buf);

        dataClient = DataClientHelper.Open();
        String filePath = dataClient.getStorePath();
        logger.Info("The data file path is %s", filePath);

        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
                bytes));
        String rtn = (String) in.readObject();

        logger.assertTrue("Common data is corrupted.", rtn.equals("test"));

        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // DataSecurity - Unauthorized read (by default)
    public final void DataClient_API_DataSecurity_2() throws DataException {
        byte[] data = new byte[] { 0 };
        DataClient dataClient = DataClient.create(
                CommonDataHelper.defaultDataClientId, config.Scheduler,
                config.UserName2, config.Password2);
        try {
            dataClient.readRawBytesAll();
        } catch (AccessControlException e) {
            logger.Info("AccessControlException got as expected: %s", e
                    .getMessage());
        } catch (Exception e) {
            logger.Error("Unexpected Exception: %s", e.toString());
        }
        DataClient.delete(CommonDataHelper.defaultDataClientId,
                config.Scheduler, config.UserName2, config.Password2);

    }

    @Test(timeout = 600000)
    // DataSecurity - Authorized read for another user
    public final void DataClient_API_DataSecurity_4() throws DataException,
            SocketTimeoutException, SessionException,
            DatatypeConfigurationException {
        SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        ssi.setRunAsUsername(config.UserName2);
        ssi.setRunAsPassword(config.Password2);
        CommonDataHelper.SimpleSendGetCommonData(logger, ssi, 10, false);
    }

    @Test(timeout = 600000)
    // Concurrency write
    public final void DataClient_Concurrency_1() throws InterruptedException,
            BrokenBarrierException, DataException {
        final int total = 100;
        final CyclicBarrier evt = new CyclicBarrier(total + 1);
        final AtomicInteger success = new AtomicInteger(0);
        for (int i = 0; i < total; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    logger.Info("Thread starts to run.");
                    try {
                        logger.Info("Try to create DataClient in thread");
                        DataClient dataClient = DataClientHelper.Create();
                        CommonDataBytes commonData = CommonDataHelper
                                .GenerateCommonDataBytes(CommonDataHelper.defaultSize);
                        logger.Info("Try to write data in thread");
                        dataClient.writeRawBytesAll(commonData.bytes);
                        logger.Info("Try to read data in thread");
                        byte[] rtn = dataClient.readRawBytesAll();
                        CommonDataHelper.ValidateCommonDataBytes(rtn,
                                commonData.md5Hash);
                        success.addAndGet(1);
                    } catch (DataException e) {
                        logger.Info("Catch DataException in thread");
                        logger
                                .assertTrue(
                                        String
                                                .format(
                                                        "Unexpected data error code: %s",
                                                        DataErrorCode
                                                                .getEnum(e
                                                                        .getErrorCode())),
                                        e.getErrorCode() == DataErrorCode.DataClientAlreadyExists
                                                .getCode());
                    } finally {
                        try {
                            evt.await();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
        evt.await();
        logger
                .assertTrue(
                        String
                                .format(
                                        "Unexpected success time: %s when total %s try to create/write with the same dataClientId.",
                                        success, total), success.get() == 1);
        DataClientHelper.Delete();
    }

    @Test(timeout = 600000)
    // Concurrency read and close
    public final void DataClient_Concurrency_2() throws InterruptedException,
            BrokenBarrierException {
        DataClient_Concurrency_2_Helper(CommonDataHelper.defaultDataClientId,
                false, 100);
    }

    class DataClient_Concurrency_3_Helper_Class {
        CyclicBarrier evt;
        String dataClientId;

        public DataClient_Concurrency_3_Helper_Class(CyclicBarrier _evt,
                String _dataClientId) {
            evt = _evt;
            dataClientId = _dataClientId;
        }

        public void QueueWorkerItem() {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        DataClient_Concurrency_2_Helper(dataClientId, false, 10);
                        evt.await();
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (BrokenBarrierException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();

                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    @Test(timeout = 600000)
    // Concurrency create, write, read and close
    public final void DataClient_Concurrency_3() throws InterruptedException,
            BrokenBarrierException {
        int totalDataClient = 10;
        final CyclicBarrier evt = new CyclicBarrier(totalDataClient + 1);
        int totalDataClientFinished = 0;
        for (int i = 0; i < totalDataClient; i++) {
            DataClient_Concurrency_3_Helper_Class worker = new DataClient_Concurrency_3_Helper_Class(
                    evt, String.valueOf(i));
            worker.QueueWorkerItem();
        }
        evt.await();
    }

    class DataClient_Concurrency_2_Helper_Class {
        boolean concurrentCreateOpen;
        int run;
        String md5Hash;
        String dataClientId;
        CyclicBarrier evt;

        public DataClient_Concurrency_2_Helper_Class(
                boolean _concurrentCreateOpen, int _run, String _md5Hash,
                String _dataClientId, CyclicBarrier _evt) {
            concurrentCreateOpen = _concurrentCreateOpen;
            run = _run;
            md5Hash = _md5Hash;
            dataClientId = _dataClientId;
            evt = _evt;
        }

        public void QueueWorkItem() {
            Thread t;
            Runnable runnable = null;
            if (concurrentCreateOpen && run == 0) {
                runnable = new Runnable() {
                    public void run() {
                        try {
                            logger.Info("Thread starts to run.");
                            logger.Info("Try to create DataClient in thread");
                            DataClient dataClient = DataClientHelper
                                    .Create(dataClientId);
                            CommonDataBytes commonData = CommonDataHelper
                                    .GenerateCommonDataBytes(5 * 1024 * 1024);
                            dataClient.writeRawBytesAll(commonData.bytes);
                        } catch (Exception e) {
                            logger.Error("Unexpected exception: %s", e
                                    .toString());
                        } finally {
                            try {
                                evt.await();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (BrokenBarrierException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                };
            } else {
                runnable = new Runnable() {
                    public void run() {
                        byte[] rtn = null;
                        logger.Info("Thread starts to run.");
                        try {
                            logger.Info("Try to open DataClient in thread");
                            DataClient dataClient = DataClient.open(
                                    dataClientId, config.Scheduler,
                                    config.UserName, config.Password);
                            try {
                                rtn = dataClient.readRawBytesAll();
                            } catch (DataException e) {
                                logger
                                        .Info("Catch DataException in thread for DataClient.Read");
                                boolean assertResult = false;
                                if (concurrentCreateOpen) {
                                    assertResult = (e.getErrorCode() == DataErrorCode.DataInconsistent
                                            .getCode() | e.getErrorCode() == DataErrorCode.DataClientBeingCreated
                                            .getCode());
                                } else {
                                    assertResult = (e.getErrorCode() == DataErrorCode.DataInconsistent
                                            .getCode() | e.getErrorCode() == DataErrorCode.DataClientDeleted
                                            .getCode());
                                }
                                logger
                                        .assertTrue(
                                                String
                                                        .format(
                                                                "Unexpected data error code for DataClient.Read: %s,%s",
                                                                DataErrorCode
                                                                        .getEnum(e
                                                                                .getErrorCode()),
                                                                e.getMessage()),
                                                assertResult);
                            }
                            try {
                                DataClientHelper.Delete(dataClientId);
                            } catch (DataException e) {
                                logger
                                        .Info("Catch DataException in thread for DataClient.Delete");
                                logger
                                        .assertTrue(
                                                String
                                                        .format(
                                                                "Unexpected data error code for DataClient.Delete: %s,%s",
                                                                DataErrorCode
                                                                        .getEnum(e
                                                                                .getErrorCode()),
                                                                e.getMessage()),
                                                e.getErrorCode() == DataErrorCode.DataClientBusy
                                                        .getCode());
                            }
                        } catch (DataException e) {
                            logger
                                    .Info("Catch DataException in thread for DataClient.Open");
                            logger
                                    .assertTrue(
                                            String
                                                    .format(
                                                            "Unexpected data error code for DataClient.Open: %s,%s",
                                                            DataErrorCode
                                                                    .getEnum(e
                                                                            .getErrorCode()),
                                                            e.getMessage()),
                                            e.getErrorCode() == DataErrorCode.DataClientNotFound
                                                    .getCode()
                                                    | e.getErrorCode() == DataErrorCode.DataClientBusy
                                                            .getCode());
                        } catch (Exception e) {
                            logger.Error("Unexpected exception: %s", e
                                    .toString());
                        }
                        try {
                            if (rtn != null)
                                CommonDataHelper.ValidateCommonDataBytes(rtn,
                                        md5Hash);
                        } catch (Exception e) {
                            logger
                                    .Error(
                                            "Unexpected exception when validating data: %s",
                                            e.toString());
                        } finally {
                            try {
                                evt.await();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (BrokenBarrierException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }
            t = new Thread(runnable);
            t.start();
        }
    }

    private void DataClient_Concurrency_2_Helper(String dataClientId,
            boolean concurrentCreateOpen, int totalThread)
            throws InterruptedException, BrokenBarrierException {
        String md5Hash = null;
        if (!concurrentCreateOpen) {
            try {
                DataClient dataClient = DataClientHelper.Create(dataClientId);
                CommonDataBytes commonData = CommonDataHelper
                        .GenerateCommonDataBytes(5 * 1024 * 1024);
                md5Hash = commonData.md5Hash;
                dataClient.writeRawBytesAll(commonData.bytes);

            } catch (Exception e) {
                logger.Error("Unexpected exception: %s", e.toString());
            }
        }
        final CyclicBarrier evt = new CyclicBarrier(totalThread + 1);
        for (int i = 0; i < totalThread; i++) {
            int run = i;
            DataClient_Concurrency_2_Helper_Class worker = new DataClient_Concurrency_2_Helper_Class(
                    concurrentCreateOpen, run, md5Hash, dataClientId, evt);
            worker.QueueWorkItem();
        }
        evt.await();
    }

    private static Config config;
    private static Logger logger;

}
