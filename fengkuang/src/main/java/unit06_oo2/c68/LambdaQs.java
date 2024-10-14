package unit06_oo2.c68;

interface Eatable
{
    void taste();
}
interface Flyable
{
    void fly(String weather);
}
interface Addable
{
    int add(int a, int b);
}
public class LambdaQs {
    public void eat(Eatable e)
    {
        //System.out.println(e);
        e.taste();
    }
    public void drive(Flyable f)
    {
        //System.out.println("我正在驾驶：" + f);
        f.fly("[碧空如洗的晴日]");
    }
    public void test(Addable add)
    {
        System.out.println("5与3的和为:" + add.add(5, 3));
    }
    public static void main(String[] args) {
        LambdaQs lq = new LambdaQs();
        // Lambda 代码块只有一条语句，可以省略花括号
        lq.eat(() -> System.out.println("苹果的味道不错！"));
        // Lambda 形参只有一个，可以省略圆括号
        lq.drive(weather -> {
            System.out.println("今天天气是：" + weather);
            System.out.println("直升机飞行平稳");
        });
        lq.test((a, b)-> a+b);
    }
}
