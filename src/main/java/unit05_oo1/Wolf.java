package unit05_oo1;

class Creature
{
    public Creature()
    {
        System.out.println("Creature 无参的构造器");
    }
}
class Animal extends Creature
{
    public Animal(String name)
    {
        System.out.println("Animal 带一个参数的构造器，" +
                "该动物的name为" + name);
    }
    public Animal(String name, int age)
    {
        // 使用this调用重载构造器
        this(name);
        System.out.println("Animal 带两个参数的构造器，" +
                "其age为" + age);
    }
}
public class Wolf extends Animal{
    public Wolf()
    {
        // 显示调用父类有两个参数的构造器
        super("花太郎", 3);
        System.out.println("Wolf无参数的构造器");
    }

    public static void main(String[] args) {
        new Wolf();
    }
}
