package unit06_oo2.c64_final;

public class FinaIErrorTest {
    // 定义 final 修饰的实例变量 age
    final int age;
    // 定义普通初始化块，用来初始化 age 的值
    {
        // 此时 age 还没有初始化，不可以直接访问成员变量
        // System.out.println(age);
        // 此时 age 还没有初始化，但是可以通过方法访问成员变量，值为0
        printAge();
        age = 6;
        System.out.println(age);
    }
    public void printAge(){
        System.out.println(age);
    }
}
