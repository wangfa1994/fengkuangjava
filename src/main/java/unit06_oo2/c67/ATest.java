package unit06_oo2.c67;

interface A
{
    void test();
}
public class ATest {
    public static void main(String[] args) {
        int age = 8;
        A a = new A()
        {
            public void test()
            {
                // 在Java 8 以前下面语句将提示错误: age 必须使用final 修饰
                // Java 8开始，自动添加了 final 修饰
                System.out.println(age);
            }
        };
        a.test();
    }
}
