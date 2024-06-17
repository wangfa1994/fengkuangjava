package unit06_oo2.c68;

import javax.swing.*;

@FunctionalInterface
interface Converter
{
    Integer convert(String from);
}
@FunctionalInterface
interface MyTest
{
    String test(String a, int b, int c);
}
@FunctionalInterface
interface YoutTest
{
    JFrame win(String title);
}
public class MethodRefer {
    // 1. 引用类方法
    Converter converter1 = from -> Integer.valueOf(from);
    // 方法引用代替 Lambda 表达式
    Converter converter2 = Integer::valueOf;

    // 2. 引用特定对象的实例方法
    Converter converter3 = from -> "fkit.org".indexOf(from);
    Converter converter4 = "fkit.org"::indexOf;

    // 3. 引用某类对象的实例方法
    MyTest mt = (a,b,c)-> a.substring(b,c);
    MyTest mt1 = String :: substring;

    // 4. 引用构造器
    YoutTest yt = (String a) -> new JFrame(a);
    YoutTest yt1 = JFrame::new;
}
