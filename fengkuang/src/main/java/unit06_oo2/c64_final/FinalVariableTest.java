package unit06_oo2.c64_final;

/**
 * 1.成员变量包含：实例变量、类变量
 * 2.成员变量赋值的位置：声明变量时、初始化块（普通、静态块）、构造函数（仅实例变量）
 * 3.普通成员变量：如果没有赋初始值，系统会给默认值
 * 4.final 成员变量：Java 规定必须显示指定初始值
 */
public class FinalVariableTest
{
    // 实例变量，声明时分配了初始值，合法
    final int a = 6;
    // 实例变量，在普通初始化块中分配了初始值，合法
    final String str;
    {
        str = "Hello";
        // 实例变量 a 已经分配初始值，不可重新赋值，不合法
        // a = 9;
    }
    // 实例变量，在构造器中分配了初始值，合法
    final int c;
    public FinalVariableTest()
    {
        // str 已经分配初始值，不可重新赋值，不合法
        // str = "java";
        c = 5;
    }
    // 类变量，在静态初始化块中分配了初始值，合法
    final static double d;
    static
    {
        d = 5.6;
    }
    // 实例变量，在声明时、初始化块中、构造器中均没有分配初始值，不合法
    // final char ch;
    public void changeFinal()
    {
        // 普通方法不能为 final 修饰的成员变量（实例变量、类变量）赋值
        // d = 1.2;
        // ch = 'a';
    }
    public static void main(String[] args)
    {
        FinalVariableTest ft = new FinalVariableTest();
        System.out.println(ft.a);
        System.out.println(ft.c);
        System.out.println(ft.d);
    }
}
