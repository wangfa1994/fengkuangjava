package unit05_oo1.c59;

public class Person {
    {
        int a = 6;
        if (a > 4)
        {
            System.out.println("Person 初始化块，a 大于4");
        }
        System.out.println("Person的初始化块");
    }
    {
        System.out.println("Person的第二个初始化块");
    }
    public Person()
    {
        System.out.println("Person类的无参构造器");
    }

    public static void main(String[] args) {
        new Person();
    }
}
