package unit03_datatype;

import org.openjdk.jol.info.ClassLayout;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 添加了jvm参数 -Dfile.encoding=GBK
        char c = '严';


        System.out.println(System.getProperty("file.encoding"));

        String a = new String("𩸾");

        byte[] bytes = a.getBytes();
        System.out.println(Arrays.toString(bytes));

        ClassLayout classLayout = ClassLayout.parseInstance(a);
        System.out.println(classLayout.toPrintable());



       /* System.out.println(System.getProperty("file.encoding"));
        String s =  new String("abcd");
        String s1 = new String ("中");
        String s11 = new String ("中".getBytes(),StandardCharsets.UTF_8); // 这个会先中按照gbk进行转码，然后再utf8编码

        //String chineseStr = "𩸽𩸾𩸿";
        String s2 = new String("𩸾");
        String s22 = new String (s2.getBytes(),StandardCharsets.UTF_8); // 这个会先中按照gbk进行转码，然后再utf8编码


        System.out.println("abcd:"+s.length());
        System.out.println("中:"+s1.length());
        System.out.println("中:"+s11.length());

        System.out.println("𩸽:"+s2.length());
        System.out.println("𩸽:"+s22.length());

        System.out.println(Arrays.toString(s.getBytes()));
        System.out.println(Arrays.toString(s1.getBytes()));
        System.out.println(Arrays.toString(s11.getBytes()));
        System.out.println(Arrays.toString(s2.getBytes()));
        System.out.println(Arrays.toString(s22.getBytes()));*/

       /* ClassLayout classLayout = ClassLayout.parseInstance(s);
        System.out.println(classLayout.toPrintable());*/

    }
}
