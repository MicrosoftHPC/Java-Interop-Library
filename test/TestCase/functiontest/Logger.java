/**
 * 
 */
package functiontest;

import static java.lang.System.out;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import static org.junit.Assert.*;

/**
 * @author yutongs
 * 
 */
public class Logger {

    public Logger() {

    }

    public Logger(boolean toScreen, boolean toFile, String fileName)
            throws IOException {
        this.toScreen = toScreen;
        if (toFile) {
            this.toFile = true;
            UUID uuid = UUID.randomUUID();
            this.fileName = fileName + "_" + uuid.toString() + ".testdata";
            outPrint = new PrintWriter(new FileWriter(this.fileName), true);

        }
    }
    
    public void Start()   {
    	String message = Thread.currentThread().getStackTrace()[2].getMethodName();
    	Start(message);
    }
    

    public void Start(String message) {
        String time = "[" + df.format(new Date()) + "]";
        message = "[Start]" + time + message;
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
        errCount = 0;

    }

    public void Start(String fmt, Object... args) {
        String time = "[" + df.format(new Date()) + "]";
        String message = String.format("[Start]" + time + fmt, args);
        if (toScreen) {
            out.println(message);
        }
        if (toFile) {
            outPrint.println(message);
        }
        errCount = 0;

    }

    public void Info(String message) {
        String time = "[" + df.format(new Date()) + "]";
        message = "[Info]" + time + message;
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
    }
    
    public void Info(Object messageObj) {
        String time = "[" + df.format(new Date()) + "]";
        String message = "[Info]" + time + messageObj.toString();
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
    }

    public void Info(String fmt, Object... args) {
        String time = "[" + df.format(new Date()) + "]";
        String message = String.format("[Info]" + time + fmt, args);
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
    }
    
    public void Info(String message, Throwable e){
    	String time = "[" + df.format(new Date()) + "]";
        message = "[Info]" + time+ message ;
        if (toScreen)
        {
        	out.print(message);
        	e.printStackTrace(out);
        }
        if (toFile)
        {
        	outPrint.print(message);
        	e.printStackTrace(outPrint);
        }
        
    }
    
    public void Info(Throwable e){
    	String time = "[" + df.format(new Date()) + "]";
        String message = "[Error]" + time ;
        if (toScreen)
        {
        	out.print(message);
        	e.printStackTrace(out);
        }
        if (toFile)
        {
        	outPrint.print(message);
        	e.printStackTrace(outPrint);
        }
        // fail the test case if Error happened
        // fail(message);
        errCount++;
    }

    public void Error(String message) {
        String time = "[" + df.format(new Date()) + "]";
        message = "[Error]" + time + message;
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
        // fail the test case if Error happened
        // fail(message);
        errCount++;

    }

    public void Error(String fmt, Object... args) {
        String time = "[" + df.format(new Date()) + "]";
        String message = String.format("[Error]" + time + fmt, args);
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
        // fail the test case if Error happened
        // fail(message);
        errCount++;
    }
    
    public void Error(String message, Throwable e){
    	String time = "[" + df.format(new Date()) + "]";
        message = "[Error]" + time+ message ;
        if (toScreen)
        {
        	out.print(message);
        	e.printStackTrace(out);
        }
        if (toFile)
        {
        	outPrint.print(message);
        	e.printStackTrace(outPrint);
        }
        // fail the test case if Error happened
        // fail(message);
        errCount++;
    }
    
    public void Error(Throwable e){
    	String time = "[" + df.format(new Date()) + "]";
        String message = "[Error]" + time ;
        if (toScreen)
        {
        	out.print(message);
        	e.printStackTrace(out);
        }
        if (toFile)
        {
        	outPrint.print(message);
        	e.printStackTrace(outPrint);
        }
        // fail the test case if Error happened
        // fail(message);
        errCount++;
    }

    public void Warning(String message) {
        String time = "[" + df.format(new Date()) + "]";
        message = "[Warning]" + time + message;
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
    }

    public void Warning(String fmt, Object... args) {
        String time = "[" + df.format(new Date()) + "]";
        String message = String.format("[Warning]" + time + fmt, args);
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
    }
    
    public void End() {
    	String message = Thread.currentThread().getStackTrace()[2].getMethodName();
    	End(message);
    }

    public void End(String message) {

        String time = "[" + df.format(new Date()) + "]";
        if (errCount > 0) {
            message = "[End]" + time + " Fail (" + errCount + " error(s)) "
                    + message;
        } else {
            message = "[End]" + time + " Pass " + message;
        }
        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
        if (errCount > 0) {
            fail(message);
        }
    }

    public void End(String fmt, Object... args) {
        String time = "[" + df.format(new Date()) + "]";
        String message;
        if (errCount > 0) {
            message = String.format("[End]" + time + " Fail (" + errCount
                    + " error(s)) " + fmt, args);
        } else {
            message = String.format("[End]" + time + " Pass " + fmt, args);
        }

        if (toScreen)
            out.println(message);
        if (toFile)
            outPrint.println(message);
        if (errCount > 0) {
            fail(message);
        }
    }

    public void assertTrue(String message, boolean isTrue) {
        if (!isTrue) {
            Error("AssertTrue fail. " + message);
        }
    }

    public <T> void assertEqual(String message, T a, T b) {
        if (a != null && !a.equals(b)) {
            if (b != null) {
                Error("AsserEqual fail. " + message + " <" + a.toString()
                        + "><" + b.toString() + "> ");
            } else {
                Error("AsserEqual fail. " + message + " <" + a.toString()
                        + "><null> ");
            }
        } else if (a == null && b != null) {
            Error("AsserEqual fail. " + message + " <null><" + b.toString()
                    + "> ");
        }

    }

    public void assertNotNull(String message, Object a) {
        if (a == null) {
            Error("AsserNotNull fail. " + message);
        }

    }

    public void assertNull(String message, Object a) {
        if (a != null) {
            Error("AsserNotNull fail. " + message);
        }

    }

    public void close() {
        if (toFile) {
            outPrint.close();
        }
    }

    private boolean toScreen = true;
    private boolean toFile = false;
    private String fileName = null;
    private PrintWriter outPrint = null;
    private SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    private int errCount = 0;

}
