package unit06_oo2.c64_final;

public class FinalReplaceTest
{
    public static void main(String[] args)
    {
        // 宏变量
        final int a = 5 + 2;
        // 宏变量
        final double b = 1.2/5;
        // 宏变量
        final String str = "疯狂" + "Java";
        // 宏变量
        final String book = "疯狂 Java 讲义：" + 99.0;
        // 调用了方法，无法在编译时确定，所以不是宏变量
        final String book2 = "疯狂 Java 讲义：" + String.valueOf(99.0);
        System.out.println(book == "疯狂 Java 讲义：99.0"); // true
        System.out.println(book2 == "疯狂 Java 讲义：99.0");// false
    }
}
