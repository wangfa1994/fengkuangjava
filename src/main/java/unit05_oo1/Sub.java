package unit05_oo1;

class Base
{
    public double size;
    public String name;
    public Base() {
        System.out.println("default super constructor....");
    }
    public Base(double size, String name)
    {
        this.size = size;
        this.name = name;
    }
}
public class Sub extends Base {
    public String color;
    public Sub(String color) {
        this.color = color;
    }
    public Sub(double size, String name, String color)
    {
        // 通过super调用父类构造器的初始化过程
         super(size, name);
        // 使用super、this调用构造器时都必须出现在构造器执行体的第一行
        // this(color);
        this.color = color;
    }

    public static void main(String[] args) {
        Sub s = new Sub(5.6, "测试对象", "红色");
        System.out.println(s.size + "--" + s.name + "--" + s.color);
    }
}
