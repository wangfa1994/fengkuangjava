package unit06_oo2.c68;

@FunctionalInterface
interface Displayable
{
    // 定义一个抽象方法和默认方法
    void display();
    default int add(int a, int b)
    {
        return a + b;
    }
}
public class LambdaAndlnner {
    private int age = 12;
    private static String name = "疯狂软件教育中心";
    public void test()
    {
        String book = "疯狂Java讲义";
        Displayable dis = ()->{
            // 访问“effectively final”的局部变量
            System.out.println("book 局部变量为：" + book);
            System.out.println("外部类的age实例变量为："+age);
            System.out.println("外部类的name类变量为：" + name);
        };
        dis.display();
        System.out.println(dis.add(3,5));
    }
    public static void main(String[] args) {
        LambdaAndlnner lambda = new LambdaAndlnner();
        lambda.test();
    }
}
