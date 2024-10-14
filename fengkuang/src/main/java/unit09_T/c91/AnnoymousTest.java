package unit09_T.c91;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Foo<T>{
    void test(T t);
}
/**
 * @author alvin
 * @date 2020-04-23 22:56
 */
public class AnnoymousTest {
    public static void main(String[] args) {
        // Java 7 开始，后面可以省略
        List<String> strList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        // Java 9 开始，创建匿名内部类时使用菱形语法
        // 指定 Foo 类中泛型为 String
        Foo<String> f = new Foo<>(){
            @Override
            public void test(String s) {
                System.out.println("test 方法的 s 参数为：" + s);
            }
        };
        // 使用泛型通配符，此时相当于通配符的上限为 Object
        Foo<?> fo = new Foo<>(){
            @Override
            public void test(Object o) {
                System.out.println("test 方法的 o 参数为：" + o);
            }
        };
        // 使用泛型通配符，通配符的上限为 Number
        Foo<? extends Number> fn = new Foo<>(){
            @Override
            public void test(Number number) {
                System.out.println("test 方法的 Number 参数为：" + number);
            }
        };
    }
}
