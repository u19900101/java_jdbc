import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author lppppp
 * @create 2021-01-03 19:02
 */
public class test {
    @Test
    public void T() throws UnsupportedEncodingException {
        String s = "解决kk";
        String encode = URLEncoder.encode(s, "utf-8");
        System.out.println(encode);

        String decode = URLDecoder.decode(encode, "utf-8");
        System.out.println(decode);

    }
}
