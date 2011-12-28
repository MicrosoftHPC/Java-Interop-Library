/**
 * 
 */
package functiontest;

import com.microsoft.hpc.scheduler.session.*;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;

import org.tempuri.Echo;
import org.tempuri.GenerateLoad;
import org.tempuri.ObjectFactory;

/**
 * @author yutongs
 * 
 */

public class Util {

    public static SessionBase CreateSession(SessionStartInfo info,
            boolean isDurable) throws SocketTimeoutException, SessionException {
        if (isDurable) {
            return DurableSession.createSession(info);
        } else {
            return Session.createSession(info);
        }

    }

    public static ArrayList<Integer> intValues = new ArrayList<Integer>();

    public static ArrayList<String> stringValues = new ArrayList<String>();

    static {
        intValues.add(0);
        intValues.add(1);
        intValues.add(-1);
        intValues.add(Integer.MAX_VALUE);
        intValues.add(Integer.MIN_VALUE);

        stringValues.add("");
        stringValues.add(" ");
        stringValues.add(null);
        stringValues.add("a");
        stringValues.add("a b c");
        stringValues.add("xyz123");
        stringValues.add("!!*^*(&*)@#%^&*()!+_}{?<>");
        stringValues.add("asdi088&*^&%$&");

    }

    public static int generateRandomInteger() {
        Random rand = new Random();
        return rand.nextInt(Integer.MAX_VALUE);
    }

    public static Echo generateEchoRequest() {
        return generateEchoRequest(generateRandomInteger());
    }

    public static Echo generateEchoRequest(int refId) {
        Echo request = new Echo();
        request.setRefID(refId);
        return request;
    }

    public static GenerateLoad generateGeneratedLoad(String commonDataPath,
            byte[] inputData, long milliSec, int refId) {
        GenerateLoad request = new GenerateLoad();
        ObjectFactory of = new ObjectFactory();
        request.setCommonDataPath(of
                .createGenerateLoadCommonDataPath(commonDataPath));
        request.setInputData(of.createGenerateLoadInputData(inputData));
        request.setMillisec(milliSec);
        request.setRefID(refId);
        return request;
    }
}
