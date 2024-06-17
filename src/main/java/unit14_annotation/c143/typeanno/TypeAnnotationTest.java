package unit14_annotation.c143.typeanno;

import javax.swing.*;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

/**
 * @author alvin
 * @date 2020-05-04 10:31
 */
// 定义一个简单的类型注解，不带任何成员变量
@Target(ElementType.TYPE_USE)
@interface NotNull{}

// 定义类时使用类型注解
@NotNull
public class TypeAnnotationTest
    implements @NotNull /* implements 时使用类型注解 */ Serializable {
    // 方法形参中使用类型注解
    public static void main(@NotNull String[] args) {
        Object obj = "fkjava.org";
        // 强制类型转换时使用类型注解
        String str = (@NotNull String)obj;
        // 创建对象时使用类型注解
        Object win = new @NotNull JFrame("aaaa");
    }
    // 泛型中使用类型注解
    public void foo(List<@NotNull String> info){}
}
