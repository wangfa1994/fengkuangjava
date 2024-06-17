package unit07_basicclasss.c6;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Arrays;

/**
 * @author alvin
 * @date 2020-05-24 18:45
 */
class User {
    String name;
    static int MAX_AGE;
}
public class VarHandleTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String[] sa = new String[]{"Java", "Kotlin", "Go"};
        // 获取一个 String[] 数组的 VarHandle 对象
        final VarHandle avh = MethodHandles.arrayElementVarHandle(String[].class);
        // 比较并设置：如果第三个元素是 Go，则该元素被设为 Lua
        boolean r = avh.compareAndSet(sa, 2, "Go", "Lua");
        // 输出比较结果
        System.out.println(r);
        // 看到第三个元素被替换为 Lua
        System.out.println(Arrays.toString(sa));

        // 获取 sa 数组的第二个元素
        System.out.println(avh.get(sa, 1));
        System.out.println(avh.getAndSet(sa, 2, "Swift"));
        // 看到第三个元素被替换成 Swift
        System.out.println(Arrays.toString(sa));

        // 获取 User 类中名为 nam、类型为 String 的实例变量
        VarHandle vh1 = MethodHandles.lookup().findVarHandle(User.class,
                "name", String.class);
        User user = new User();
        // 通过 VarHandle 获取实例变量的值，需要传入对象作为调用者
        System.out.println(vh1.get(user)); // null
        // 设置实例变量的值
        vh1.set(user, "孙悟空");
        System.out.println(user.name); // 孙悟空
        System.out.println(vh1.get(user)); // 孙悟空

        // 获取 User 类中名为 MAX_AGE、类型为 Integer 的类变量
        VarHandle vh2 = MethodHandles.lookup().findStaticVarHandle(User.class,
                "MAX_AGE", int.class);
        System.out.println(vh2.get()); // 0
        vh2.set(100);
        System.out.println(User.MAX_AGE); // 100
        System.out.println(vh2.get());
    }
}
