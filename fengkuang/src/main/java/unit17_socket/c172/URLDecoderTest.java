package unit17_socket.c172;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author alvin
 * @date 2020-05-01 10:31
 */
public class URLDecoderTest {
    public static void main(String[] args) {
        String keyWord = URLEncoder.encode("悟空", StandardCharsets.UTF_8);
        System.out.println(keyWord);
        String gbk = URLDecoder.decode(keyWord, StandardCharsets.UTF_8);
        System.out.println(gbk);
    }
}