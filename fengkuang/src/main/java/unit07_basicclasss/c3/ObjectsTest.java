package unit07_basicclasss.c3;

import java.util.Objects;

/**
 * @author alvin
 * @date 2020-05-24 9:14
 */
public class ObjectsTest {
    // 定义一个 obj 变量,默认值为 null
    static ObjectsTest obj;
    public static void main(String[] args) {
        // null 对象的 hashCode 0
        System.out.println(Objects.hashCode(obj));
        // 输出 null
        System.out.println(Objects.toString(obj));
        System.out.println(Objects.requireNonNull(obj, "obj 参数不能是 null"));
    }
}
