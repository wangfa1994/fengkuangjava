package unit09_T.c92;

/**
 * @author alvin
 * @date 2020-04-23 23:52
 */
public class StaticTest<T> {
    // 下面代码错误，不能在静态变量声明中使用泛型形参
    // static T info;
    T age;
    // 下面代码错误，不能再静态方法声明中使用泛型形参
    // public static void bar(T msg){}
}
