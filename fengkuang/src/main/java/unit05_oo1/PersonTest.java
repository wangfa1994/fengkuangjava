package unit05_oo1;

class Person
{
    // 实例变量
    public String name;
    // 类变量
    public static int eyeNum;
}
public class PersonTest {
    public static void main(String[] args) {
        System.out.println("Person的eyeNum类变量值：" + Person.eyeNum);
        // 创建Person对象
        Person p = new Person();
        System.out.println("p变量的name:" + p.name + "; p变量eyeNun:" + p.eyeNum);
        p.name = "孙悟空";
        p.eyeNum = 2;
        System.out.println("p变量的name:" + p.name + "; p变量eyeNun:" + p.eyeNum);
        System.out.println("Person的eyeNum类变量值：" + Person.eyeNum);
        Person p2 = new Person();
        // 2
        System.out.println("p2对象的eyeNum类变量值：" + p2.eyeNum);
    }
}
