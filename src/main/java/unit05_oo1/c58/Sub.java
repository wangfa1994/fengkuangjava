package unit05_oo1.c58;
class Base
{
    public Base()
    {
        test();
    }
    public void test()
    {
        System.out.println("将被子类重写的方法");
    }
}
public class Sub extends Base{
    private String name;
    public void test()
    {
        System.out.println("子类重写父类方法，" +
                "其name长度为" + name.length());
    }
    public static void main(String[] args) {
        // 引发空指针异常
        Sub s = new Sub();
    }
}
