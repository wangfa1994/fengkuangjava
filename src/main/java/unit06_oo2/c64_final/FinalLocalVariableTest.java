package unit06_oo2.c64_final;

public class FinalLocalVariableTest
{
    public void test(final int a)
    {
        // 不能对 final 形参赋值，非法
        // a = 5;
    }
    public static void main(String[] args)
    {
        // 局部变量，定义时赋初始值
        final String str = "hello";
        // 局部变量，str 已经赋值，非法
        // str = "Java";
        // 局部变量，定义没有赋初始值
        final double d;
        // 局部变量，可以赋值一次
        d = 5.6;
        // 局部变量，d 已经赋值，非法
        // d = 3.4;
        // 普通局部变量，可以多次赋值
        double e;
        e = 1;
        e = 3;
    }
}
