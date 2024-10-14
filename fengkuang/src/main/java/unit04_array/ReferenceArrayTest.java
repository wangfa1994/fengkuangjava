package unit04_array;

class Person{
    public int age;
    public double height;
    public void info(){
        System.out.println("我的年龄是：" + age
            + ", 我的身高是：" + height);
    }
}
public class ReferenceArrayTest {
    public static void main(String[] args) {
        // 定义一个数组变量
        Person[] students;
        // 执行动态初始化
        students = new Person[2];
        // 创建一个 Person 实例，并将这个实例赋给 zhang 变量
        Person zhang = new Person();
        // 为 zhang 所引用的对象的属性赋值
        zhang.age = 15;
        zhang.height = 158;
        Person lee = new Person();
        lee.age = 16;
        lee.height = 161;
        // 给数组元素赋值
        students[0] = zhang;
        students[1] = lee;
        // 下面两行代码结果一样，因为 lee 和 students[1] 指向同一个 person
        lee.info();
        students[1].info();
    }
}
