package unittest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.microsoft.hpc.scheduler.session.DurableSession;
import com.microsoft.hpc.scheduler.session.HpcJava;
import com.microsoft.hpc.scheduler.session.Session;
import com.microsoft.hpc.scheduler.session.SessionStartInfo;
import com.microsoft.hpc.scheduler.session.SessionUnitType;

public class SessionPoolTest
{
    static String schedulerName = "qingzhi-2008r2";
    static String userName = "fareast\\wsdcta";
    static String password = "Pa55word$$4";

    @BeforeClass
    public static void before()
    {
        HpcJava.setPassword(password);
        HpcJava.setUsername(userName);
    }

    @Test
    public void CheckInvalidParameter()
    {
        try
        {
            SessionStartInfo info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(false);
            info.setUseSessionPool(true);

            DurableSession session1 = DurableSession.createSession(info);
            session1.close();
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertTrue(e.getMessage().compareTo("Session pool is not supported by unshared session.") == 0);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("unexpected exception");
        }
    }

    @Test
    public void UseDurableSessionPoolForSameService()
    {
        try
        {
            SessionStartInfo info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(true);
            info.setUseSessionPool(true);

            DurableSession session1 = DurableSession.createSession(info);
            session1.close(false);

            info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(true);
            info.setUseSessionPool(true);
            DurableSession session2 = DurableSession.createSession(info);
            session2.close();

            assertTrue(session1.getId() == session2.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("unexpected exception");
        }
    }

    @Test
    public void UseInteractiveSessionPoolForSameService()
    {
        try
        {
            SessionStartInfo info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(true);
            info.setUseSessionPool(true);

            Session session1 = Session.createSession(info);
            session1.close(false);

            info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(true);
            info.setUseSessionPool(true);
            Session session2 = Session.createSession(info);
            session2.close();

            assertTrue(session1.getId() == session2.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("unexpected exception");
        }
    }

    @Test
    public void NotUseSessionPoolForSameService()
    {
        try
        {
            SessionStartInfo info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(true);
            info.setUseSessionPool(false);

            DurableSession session1 = DurableSession.createSession(info);
            session1.close(false);

            info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setShareSession(true);
            info.setUseSessionPool(false);
            DurableSession session2 = DurableSession.createSession(info);
            session2.close();

            assertTrue(session1.getId() != session2.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("unexpected exception");
        }
    }

    @Test
    public void NotUseSessionPoolForDiffService()
    {
        try
        {
            SessionStartInfo info = new SessionStartInfo(schedulerName, "aitestservicelib");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setUseSessionPool(true);
            info.setShareSession(true);
            DurableSession session1 = DurableSession.createSession(info);
            session1.close(false);

            info = new SessionStartInfo(schedulerName, "CcpEchoSvc");
            info.setResourceUnitType(SessionUnitType.Core);
            info.setMaxUnits(1);
            info.setMinUnits(1);
            info.setUseSessionPool(true);
            info.setShareSession(true);
            DurableSession session2 = DurableSession.createSession(info);
            session2.close();
            
            assertTrue(session2.getId() != session1.getId());
            session1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("unexpected exception");
        }
    }
}
