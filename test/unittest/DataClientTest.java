package unittest;

import junit.framework.Assert;

import org.junit.Test;

import com.microsoft.hpc.scheduler.session.DataClient;
import com.microsoft.hpc.scheduler.session.DataLifeCycle;
import com.microsoft.hpc.exceptions.DataException;
import com.microsoft.hpc.scheduler.session.SessionException;

public class DataClientTest
{
    static String serverName = "vincent-hpc";
    static String userName = "fareast\\wsdcta";
    static String password = "Pa55word$$4";

    @Test
    public void testBasic() {
        byte[] data = { 1, 2, 3, 4, 5, 6, 7 };
        try {
            DataClient c = DataClient.create("Test", serverName, userName,
                    password);
            c.writeRawBytesAll(data);
            c.close();

            DataClient d = DataClient.open("Test", serverName, userName,
                    password);
            byte[] result = d.readRawBytesAll();

            int i = 0;
            for (byte b : result) {
                Assert.assertEquals(data[i], b);
                i++;
            }
            d.close();

            DataClient.delete("Test", serverName, userName, password);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            Assert.fail(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testBasic2() {
        byte[] data = { 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3,
                4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3,
                4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3,
                4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 7 };
        try {
            DataClient c = DataClient.create("Test", serverName, userName,
                    password);
            c.writeRawBytesAll(data);
            c.close();

            DataClient d = DataClient.open("Test", serverName, userName,
                    password);
            byte[] result = d.readRawBytesAll();

            int i = 0;
            for (byte b : result) {
                Assert.assertEquals(data[i], b);
                i++;
            }
            d.close();
            DataClient.delete("Test", serverName, userName, password);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            Assert.fail(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testCompress() {
        byte[] data = { 1, 2, 3, 4, 5, 6, 7 };
        try {
            DataClient.delete("Test2", serverName, userName, password);
            DataClient c = DataClient.create("Test2", serverName, userName,
                    password);
            c.writeRawBytesAll(data, true);
            c.close();

            DataClient d = DataClient.open("Test2", serverName, userName,
                    password);
            byte[] result = d.readRawBytesAll();

            int i = 0;
            for (byte b : result) {
                Assert.assertEquals(data[i], b);
                i++;
            }
            d.close();
            DataClient.delete("Test2", serverName, userName, password);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testLifecycle() {
        byte[] data = { 1, 2, 3, 4, 5, 6, 7 };
        try {
            DataClient.delete("Test2", serverName, userName, password);
            DataClient c = DataClient.create("Test2", serverName, userName,
                    password);
            c.setDataLifeCycle(new DataLifeCycle(10));
            c.writeRawBytesAll(data, true);
            c.close();

            DataClient d = DataClient.open("Test2", serverName, userName,
                    password);
            byte[] result = d.readRawBytesAll();

            int i = 0;
            for (byte b : result) {
                Assert.assertEquals(data[i], b);
                i++;
            }
            d.close();

            DataClient.delete("Test2", serverName, userName, password);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCompress2() {
        byte[] data = { 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3,
                4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3,
                4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3,
                4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5,
                6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71,
                2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 61, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 61, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 61, 2, 3, 4, 5, 6, 7, 1, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 61, 2, 3, 4, 5, 6, 7,
                1, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 61, 2, 3, 4, 5,
                6, 7, 1, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2,
                3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4,
                5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6,
                71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6, 71, 2, 3, 4, 5, 6 };
        try {
            DataClient.delete("Test2", serverName, userName, password);
            DataClient c = DataClient.create("Test2", serverName, userName,
                    password);
            c.writeRawBytesAll(data, true);
            c.close();

            DataClient d = DataClient.open("Test2", serverName, userName,
                    password);
            byte[] result = d.readRawBytesAll();

            int i = 0;
            for (byte b : result) {
                Assert.assertEquals(data[i], b);
                i++;
            }
            d.close();
            DataClient.delete("Test2", serverName, userName, password);
        } catch (DataException e) {
            // TODO Auto-generated catch block
            Assert.fail(e.getMessage());
        }
    }
    
    String ConvertPath(String original, String runtimeShare)
    {
        int firstSlash = original.indexOf('\\', 3);
        int secondSlash = original.indexOf('\\', firstSlash + 1);
        
        if (secondSlash == -1)
        {
            return null;
        }
        
        String result = runtimeShare + original.substring(secondSlash);
        return result.replace('\\', '/');
    }
    
    @Test
    public void testConvertPath() {
        String data = "\\\\junsuwin\\runtime$\\soa\\data\\14\\1.dat";
        
        Assert.assertEquals("/mnt/smb/soa/data/14/1.dat",ConvertPath(data, "/mnt/smb"));
    }
}
