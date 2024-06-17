package unit18_classload.c2;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author alvin
 * @date 2020-05-04 14:26
 */
public class ClassLoaderPropTest {
    public static void main(String[] args) throws IOException {
        // 获取系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemLoader);
        /*
        * 获取系统类加载器的加载路径--通常由 CLASSPATH 环境变量指定
        * 如果操作系统没有指定 CLASSPATH 环境变量，则默认以当前路径作为
        * 系统类加载器的加载路径
        * */
        Enumeration<URL> eml = systemLoader.getResources("");
        while (eml.hasMoreElements()){
            System.out.println(eml.nextElement());
        }
        // 获取系统类加载器的父类加载器，得到扩展类加载器
        final ClassLoader extensionLoader = systemLoader.getParent();
        System.out.println("扩展类加载器：" + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" +
                System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的 parent：" +
                extensionLoader.getParent());
    }
}
