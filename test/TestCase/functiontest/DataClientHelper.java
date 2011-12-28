/**
 * 
 */
package functiontest;

import com.microsoft.hpc.scheduler.session.*;
import com.microsoft.hpc.exceptions.DataException;

import java.util.ArrayList;

/**
 * @author mingqw
 * 
 */

public class DataClientHelper {

    // To Do: might need lock for this
    public static ArrayList<String> CreatedDataClients = new ArrayList<String>();
    private static Config config = new Config();

    public static DataClient Create() throws DataException {
        return Create(CommonDataHelper.defaultDataClientId);
    }

    public static DataClient Create(String dataClientId) throws DataException {
        CreatedDataClients.add(dataClientId);
        return DataClient.create(dataClientId, config.Scheduler,
                config.UserName, config.Password);
    }
    
    public static DataClient Create(String dataClientId, String[] allowedUsers) throws DataException {
        CreatedDataClients.add(dataClientId);
        return DataClient.create(dataClientId, config.Scheduler,
                config.UserName, config.Password, allowedUsers);
    }

    public static DataClient Create(int sessionId) throws DataException {
        return Create(CommonDataHelper.defaultDataClientId, sessionId);
    }

    public static DataClient Create(String dataClientId, int sessionId)
            throws DataException {
        CreatedDataClients.add(dataClientId);
        DataClient client = DataClient.create(dataClientId, config.Scheduler,
                config.UserName, config.Password);
        client.setDataLifeCycle(new DataLifeCycle(sessionId));
        return client;
    }

    public static DataClient Open() throws DataException {
        return Open(CommonDataHelper.defaultDataClientId);
    }

    public static DataClient Open(String dataClientId) throws DataException {
        return DataClient.open(dataClientId, config.Scheduler, config.UserName,
                config.Password);
    }

    public static void Delete() throws DataException {
        DataClient.delete(CommonDataHelper.defaultDataClientId,
                config.Scheduler, config.UserName, config.Password);
    }

    public static void Delete(String dataClientId) throws DataException {
        DataClient.delete(dataClientId, config.Scheduler, config.UserName,
                config.Password);
    }

    public static void WriteAll(byte[] data, int sessionId)
            throws DataException {
        WriteAll(CommonDataHelper.defaultDataClientId, data, sessionId);
    }

    public static void WriteAll(String dataClientId, byte[] data, int sessionId)
            throws DataException {
        DataClient dataClient = Create(dataClientId, sessionId);
        dataClient.writeRawBytesAll(data);

    }

    public static void WriteAll(String dataClientId, byte[] data,
            int sessionId, boolean compress) throws DataException {
        DataClient dataClient = Create(dataClientId, sessionId);
        dataClient.writeRawBytesAll(data, compress);

    }
    
    public static void WriteAll(String dataClientId, byte[] data,
            int sessionId, boolean compress, String[] allowedUsers) throws DataException {
        DataClient dataClient = Create(dataClientId, allowedUsers);
        dataClient.setDataLifeCycle(new DataLifeCycle(sessionId));
        dataClient.writeRawBytesAll(data, compress);

    }

}
