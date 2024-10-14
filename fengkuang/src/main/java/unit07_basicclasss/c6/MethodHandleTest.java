package unit07_basicclasss.c6;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author alvin
 * @date 2020-05-24 18:32
 */
public class MethodHandleTest {
    private static void hello() {
        System.out.println("Hello world!");
    }
    private String hello(String name) {
        System.out.println("执行带参数的 hello" + name);
        return name + ",您好";
    }

    public static void main(String[] args) throws Throwable {
        // 定义一个返回值为 void、不带形参的方法类型
        MethodType type = MethodType.methodType(void.class);
        // 获取类方法
        MethodHandle mtd = MethodHandles.lookup()
                .findStatic(MethodHandleTest.class, "hello", type);
        // 通过 MethodHandle 执行方法
        mtd.invoke();

        // 获取实例方法
        MethodHandle mtd2 = MethodHandles.lookup()
                .findVirtual(MethodHandleTest.class, "hello",
                MethodType.methodType(String.class, String.class));
        System.out.println(mtd2.invoke(new MethodHandleTest(), "孙悟空"));
    }
}
