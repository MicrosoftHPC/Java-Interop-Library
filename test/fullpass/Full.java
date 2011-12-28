/**
 * 
 */
package fullpass;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import functiontest.*;
/**
 * @author yutongs
 * this class is used to run full passes or specific passes in linux shell
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			functiontest.BVT.class,
			functiontest.BrokerClientTest.class,
			functiontest.BrokerResponseTest.class,
			functiontest.CommonDataTestDurable.class,
			functiontest.CommonDataTestInteractive.class,
			functiontest.DataClientTest.class,
			functiontest.DurableSessionTest.class,
			functiontest.GenericServiceTest.class,
			functiontest.SessionAttachInfoTest.class,
			functiontest.SessionStartInfoTest.class,
			functiontest.SessionBaseTest.class,
			functiontest.SessionTest.class,
			functiontest.SessionPool.class
		}
		
)
public class Full {
}
