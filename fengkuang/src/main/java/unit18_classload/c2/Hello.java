package unit18_classload.c2;

/**
 * @author alvin
 * @date 2020-05-04 15:55
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("hello");
        for(String arg : args) {
            System.out.println("运行 Hello 的参数：" + arg);
        }
    }
}
