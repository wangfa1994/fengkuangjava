package unit05_oo1;

class Parent
{
    public String tag = "疯狂 Java 讲义";
}
class Derived extends Parent
{
    // 定义一个私有的tag实例变量来隐藏父类的tag实例变量
    private String tag = "轻量级 Java EE 企业应用实战";
}
public class HideTest {
    public static void main(String[] args) {
        Derived d = new Derived();
        // 程序不可访问d的私有变量tag，编译报错
        // System.out.println(d.tag);
        System.out.println(((Parent)d).tag);
    }
}
