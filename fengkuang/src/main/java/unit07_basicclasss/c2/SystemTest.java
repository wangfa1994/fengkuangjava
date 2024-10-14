package unit07_basicclasss.c2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-04-06 23:47
 */
public class SystemTest {
    public static void main(String[] args) throws IOException {
        // 获取系统所有环境变量
        Map<String, String> env = System.getenv();
        for (String name : env.keySet()){
            System.out.println(name + "==>" + env.get(name));
        }
        // 获取指定环境变量
        System.out.println(System.getenv("JAVA_HOME"));
        // 获取所有系统属性
        Properties props = System.getProperties();
        props.store(new FileOutputStream("props.txt"), "Sys Prop");
        // 获取执行的系统属性
        System.out.println(System.getProperty("os.name"));

        System.gc();
        System.runFinalization();

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());


        System.out.println("aaa");
        System.err.println("bbb");
        System.in.read();


    }
}
