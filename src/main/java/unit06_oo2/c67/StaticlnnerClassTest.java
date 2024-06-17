package unit06_oo2.c67;

public class StaticlnnerClassTest {
    private int prop1 = 5;
    private static int prop2 = 9;
    static class StaticInnerClass
    {
        // 静态内部类里可以包含静态成员
        private static int age;
        // 也可以包含非静态成员
        private double money;
        public void accessOuterProp()
        {
            // 静态内部类不可访问外部类实例变量
            // System.out.println(prop1);
            System.out.println(prop2);
        }
    }
}
