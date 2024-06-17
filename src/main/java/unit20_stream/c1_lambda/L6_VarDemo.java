package unit20_stream.c1_lambda;

import java.util.function.Consumer;

/**
 * lambda 表达式变量引用
 */
public class L6_VarDemo {
    public static void main(String[] args) {
        // JDK13 不加 final 也可以
        final String str = "我们的时间";
        // lambda表达式实际上相当于匿名内部类，匿名内部类引用变量时必须是final的。（java参数是传值）
        Consumer<String> consumer = s-> System.out.println(s + str);
        consumer.accept("1211");
    }
}
