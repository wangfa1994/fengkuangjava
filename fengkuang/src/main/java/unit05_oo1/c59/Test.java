package unit05_oo1.c59;

class Root
{
    static {
        System.out.println("Root 的静态初始化块");
    }
    {
        System.out.println("Root 的普通初始化块");
    }
    public Root()
    {
        System.out.println("Root 的无参构造器");
    }
}
class Mid extends Root
{
    static {
        System.out.println("Mid 的静态初始化块");
    }
    {
        System.out.println("Mid 的普通初始化块");
    }
    public Mid()
    {
        System.out.println("Mid 的无参构造器");
    }
    public Mid(String msg)
    {
        this();
        System.out.println("Mid 的带参数构造器，参数值：" + msg);
    }
}
class Leaf extends Mid
{
    static {
        System.out.println("Leaf 的静态初始化块");
    }
    {
        System.out.println("Leaf 的普通初始化块");
    }
    public Leaf()
    {
        super("疯狂 Java 讲义");
        System.out.println("执行 Leaf 的构造器");
    }
}
public class Test {
    public static void main(String[] args) {
        new Leaf();
        System.out.println("============================");
        new Leaf();
    }
}
