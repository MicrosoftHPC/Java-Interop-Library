/**
 * 
 */
package functiontest;

import com.microsoft.hpc.exceptions.FaultException;
import com.microsoft.hpc.exceptions.DataException;
import com.microsoft.hpc.scheduler.session.*;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.datacontract.schemas._2004._07.services.ComputerInfo;
import org.tempuri.AITestLibService;
import org.tempuri.Echo;
import org.tempuri.EchoResponse;
import org.tempuri.GetCommonData;
import org.tempuri.GetCommonDataResponse;
import org.tempuri.ITestService;
import org.tempuri.ObjectFactory;

/**
 * @author mingqw
 * 
 */

public class CommonDataHelper {

    private static Config config = new Config("CommonDataTest");
    public static String[] validDataClientId = new String[] {
            "0123456789",
            "abcdefghijklmnopqrstuvwxyz",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "_",
            "-",
            " -",
            "{",
            "}",
            "looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong" };
    public static String[] invalidDataClientId = new String[] {
            "^",
            "[",
            "]",
            "*",
            "$",
            "~",
            "`",
            "!",
            "@",
            "#",
            "%",
            "&",
            "(",
            ")",
            "+",
            "=",
            "|",
            "\\",
            ";",
            ":",
            "\"",
            "'",
            "<",
            ">",
            "/",
            "?",
            ".",
            ",",
            " ",
            "loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong" };
    public static String defaultDataClientId = "defaultId";
    public static byte[] simpleCommonData = new byte[] { 0, 1, 2, 3, 4, 5, 6,
            7, 8, 9 };
    public static int defaultSize = 100 * 1024 * 1024;

    public static CommonDataBytes GenerateCommonDataBytes(int size) {
        Random r = new Random();
        byte[] data = new byte[size];
        r.nextBytes(data);
        String md5Hash = Md5HashString(data);
        return new CommonDataBytes(data, md5Hash);
    }

    public static String Md5HashString(byte[] data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] thedigest = md.digest(data);
        StringBuilder builder = new StringBuilder();
        for (byte d : thedigest) {
            builder.append(String.format("%2X", d));
        }
        String md5 = builder.toString();
        return md5.replace(' ', '0');
    }

    public static boolean ValidateCommonDataBytes(byte[] commonData,
            String expectedMd5Hash) {
        String commonDataMd5Hash = Md5HashString(commonData);
        return commonDataMd5Hash.equals(expectedMd5Hash);

    }

    public static void SetDataServer(String dataServer) throws IOException {
        List<String> cmds = new LinkedList<String>();
        cmds.add(System.getenv().get("CCP_HOME") + File.separator + "bin"
                + File.separator + "cluscfg");
        cmds.add("setenvs");
        cmds.add(String.format("HPC_RUNTIMESHARE=%s", dataServer));
        cmds.add(String.format("/scheduler:%s", config.Scheduler));
        runCmd(cmds);
        cmds = new LinkedList<String>();
        cmds.add("sc");
        cmds.add(String.format("\\\\%s", config.Scheduler));
        cmds.add("control");
        cmds.add("HpcSession");
        cmds.add("128");
        runCmd(cmds);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int runCmd(List<String> cmd) throws IOException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(cmd);
        Process p = pb.start();
        int rtnCode = Integer.MIN_VALUE;
        try {
            rtnCode = p.waitFor();
        } catch (InterruptedException e) {
            return rtnCode;
        }
        return rtnCode;
    }

    public static String GetDataServer() {
        // hack: how to retrieve cluster cfg?
        return config.getValue("DefaultDataServer");
    }

    public static long GetCommonDataFileSize(String defaultDataClientId)
            throws DataException {
        DataClient dataClient = DataClientHelper.Open(defaultDataClientId);
        String filePath = dataClient.getStorePath();
        File file = new File(filePath);
        return file.length();

    }

    public static void SimpleSendGetCommonData(Logger logger, int dataSize,
            boolean isDurable) throws SocketTimeoutException, SessionException,
            DataException, DatatypeConfigurationException {
        SessionStartInfo ssi = new SessionStartInfo(config.Scheduler,
                config.ServiceName, config.UserName, config.Password);
        SimpleSendGetCommonData(logger, ssi, dataSize, isDurable);
    }

    public static void SimpleSendGetCommonData(Logger logger,
            SessionStartInfo ssi, int dataSize, boolean isDurable)
            throws SessionException, SocketTimeoutException, DataException,
            DatatypeConfigurationException {
        CommonDataBytes commonData = CommonDataHelper
                .GenerateCommonDataBytes(dataSize);
        SessionBase session;
        if (isDurable)
            session = DurableSession.createSession(ssi);
        else
            session = Session.createSession(ssi);
        if (ssi.getRunAsUsername().equalsIgnoreCase(config.UserName)) {
            DataClientHelper.WriteAll(CommonDataHelper.defaultDataClientId,
                    commonData.bytes, session.getId(), isDurable);
        } else {
            DataClientHelper.WriteAll(CommonDataHelper.defaultDataClientId,
                    commonData.bytes, session.getId(), isDurable,
                    new String[] { config.UserName2 });
        }
        BrokerClient<AITestLibService> client = new BrokerClient<AITestLibService>(
                session, AITestLibService.class);
        DatatypeFactory df = DatatypeFactory.newInstance();
        Duration sleepBeforeGet = df.newDuration(0);
        Duration sleepAfterGet = df.newDuration(0);
        SendGetCommonData(logger, client, CommonDataHelper.defaultDataClientId,
                commonData.md5Hash, sleepBeforeGet, sleepAfterGet);
        session.close();
    }

    public static void SendGetCommonData(Logger logger,
            BrokerClient<AITestLibService> client, String dataClientId,
            String md5Hash, Duration sleepBeforeGet, Duration sleepAfterGet)
            throws SessionException, DatatypeConfigurationException,
            SocketTimeoutException {
        SendGetCommonData(logger, client, config.NbOfCalls, dataClientId,
                md5Hash, sleepBeforeGet, sleepAfterGet);
    }

    public static void SendGetCommonData(Logger logger,
            BrokerClient<AITestLibService> client, int reqCount,
            String dataClientId, String md5Hash, Duration sleepBeforeGet,
            Duration sleepAfterGet) throws SessionException,
            DatatypeConfigurationException, SocketTimeoutException {
        SendGetCommonData(logger, client, reqCount, dataClientId, md5Hash,
                sleepBeforeGet, sleepAfterGet,
                CommonDataTestActionId.Read_Raw_Bytes);
    }

    public static void SendGetCommonData(Logger logger,
            BrokerClient<AITestLibService> client, int reqCount,
            String dataClientId, String md5Hash, Duration sleepBeforeGet,
            Duration sleepAfterGet, CommonDataTestActionId testActionId)
            throws DatatypeConfigurationException, SessionException,
            SocketTimeoutException {
        Random r = new Random();
        ObjectFactory of = new ObjectFactory();
        DatatypeFactory df = DatatypeFactory.newInstance();
        logger.Info("Begin to send requests");
        for (int i = 0; i < reqCount; i++) {
            if (sleepBeforeGet == null) {

                sleepBeforeGet = df.newDuration(r.nextInt(2000));
            }
            if (sleepAfterGet == null) {
                sleepAfterGet = df.newDuration(r.nextInt(2000));
            }
            GetCommonData request = new GetCommonData();
            request.setDataClientId(of
                    .createGetCommonDataDataClientId(dataClientId));
            request.setExpectedMd5Hash(of
                    .createGetCommonDataExpectedMd5Hash(md5Hash));
            request.setRefID(i);
            request.setSleepAfterGet(sleepAfterGet);
            request.setSleepBeforeGet(sleepBeforeGet);
            request.setTestActionId(testActionId.getCode());
            client.sendRequest(request, String.valueOf(i));
        }
        client.endRequests();
        for (BrokerResponse<GetCommonDataResponse> response : client
                .getResponses(GetCommonDataResponse.class)) {
            String refID = null;
            try {
                refID = response.getUserData();
                ComputerInfo rtn = response.getResult()
                        .getGetCommonDataResult().getValue();

                // }catch (FaultException exception) {
                // exception.logger.Error(
                // "Common data is corrupted for req %s: %s", refID,
                // exception.Detail.Reason);
            } catch (Exception e) {
                logger.Error("Unexpected exception for req %s: %s", refID, e
                        .getMessage());
            }

        }
        logger.Info("All requests returned.");
    }

}
