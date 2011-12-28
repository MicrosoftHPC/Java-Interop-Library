/**
 * 
 */
package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.SessionUnitType;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.Version;

/**
 * @author junsu
 * 
 */
public class SessionInfoTest
{
    static String serverName = "junsuwin";
    static String userName = "fareast\\wsdcta";
    static String password = "Pa55word##3";

    @BeforeClass
    public static void before() {
        HpcJava.setPassword(password);
        HpcJava.setUsername(userName);
    }

    @Test
    public void TestBasicProperty() {
        SessionStartInfo info = new SessionStartInfo("A", "B");
        assertEquals("headnode is not match", info.getHeadnode(), "A");
        assertEquals("service name is not match", info.getServiceName(), "B");

        assertEquals("default security is not true", true, info.isSecure());
        assertEquals("default sharesession is not false", false,
                info.isShareSession());
    }

    @Test
    public void TestUnittype() {
        SessionStartInfo info = new SessionStartInfo("A", "V");
        info.setResourceUnitType(SessionUnitType.Socket);
        assertEquals("Resource Unit Type is not matched",
                SessionUnitType.Socket, info.getResourceUnitType());

        info.setClientIdleTimeout(3000);
        assertEquals("clientIdleTimeout", info.clientIdleTimeout(), 3000);
    }

    @Test
    public void TestEnv() {
        SessionStartInfo info = new SessionStartInfo("A", "V");
        info.addEnvironment("A", "B");
        info.addEnvironment("C", "D");

        HashMap<String, String> k = info.getEnvironments();
        assertEquals("env is wrong", "B", k.get("A"));
        assertEquals("env is wrong", "D", k.get("C"));
    }

    @Test
    public void TestVersion() {
        Version Version10 = new Version(1, 0);
        Version Version12 = new Version(1, 2);

        Version Version1000 = new Version(1, 0, 0, 0);
        Version Version1020 = new Version(1, 0, 2, 0);

        assertTrue(Version12.compareTo(Version10) > 0);
        assertTrue(Version1020.compareTo(Version10) > 0);
        assertTrue(Version10 == Version10);
    }

    @Test
    public void TestCrash() {
        SessionStartInfo info = new SessionStartInfo("A", "V");
        assertEquals("getResourceUnitType", info.getResourceUnitType(),
                SessionUnitType.Core);
        assertEquals("nodeGroupsStr", info.getNodeGroups(), null);
        assertEquals("serviceJobName", info.getServiceJobName(), null);
        assertEquals("isCanPreempt", info.isPreemptive(), false);
    }
}
