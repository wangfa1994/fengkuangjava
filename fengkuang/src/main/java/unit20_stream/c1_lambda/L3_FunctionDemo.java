package unit20_stream.c1_lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * 7 个函数接口
 * 1、断言。T -> boolean
 * 2、消费数据。T ->
 * 3、普通函数。T -> R
 * 4、提供数据 () -> T
 * 5、一元函数。输入输出相同 T -> T
 * 6、二元函数。输入输出相同，但是有两个输入 (T,T) -> T
 * 7、两个输入的函数。(T,U) -> R
 */
public class L3_FunctionDemo {
    public static void main(String[] args) {
        // 断言函数接口
        Predicate<Integer> predicate = i -> i>0;
        System.out.println(predicate.negate().test(-9));

        // 消费函数接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");

        // 对于参数是基本类型的，java自带了一些带类型的函数，优先使用
        IntPredicate predicate1 = i -> i>0;
        // DoubleConsumer
    }
}
