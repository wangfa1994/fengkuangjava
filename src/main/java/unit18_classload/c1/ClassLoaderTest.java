package unit18_classload.c1;

/**
 * @author alvin
 * @date 2020-05-04 14:10
 */
class Tester {
    static {
        System.out.println("Tester 类的静态初始化块...");
    }
}
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        // 下面语句仅仅是加载 Tester 类
        cl.loadClass("unit18_classload.c1.Tester");
        System.out.println("=========");
        Class.forName("unit18_classload.c1.Tester");
    }
}
