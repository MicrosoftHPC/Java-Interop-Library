/**
 * 
 */
package functiontest;

import com.microsoft.hpc.scheduler.session.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author mingqw
 * 
 */

public class CommonDataBytes {
    public byte[] bytes = null;
    public String md5Hash = null;

    public CommonDataBytes(byte[] _bytes, String _md5Hash) {
        bytes = _bytes;
        md5Hash = _md5Hash;
    }
}
