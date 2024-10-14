package unit18_classload.c4;

import java.lang.reflect.Field;

/**
 * @author alvin
 * @date 2020-05-04 22:22
 */
class Person{
    private String name;
    private int age;
    public String toString() {
        return "Person[name:" + name + ", age:" + age + "]";
    }
}
public class FieldTest {
    public static void main(String[] args) throws Exception {
        // 创建一个 Person 对象
        Person p = new Person();
        // 获取对应 Class 对象
        Class<Person> personClazz = Person.class;
        // 获取 Person 的名为 name 的成员变量
        final Field nameField = personClazz.getDeclaredField("name");
        // 设置取消访问权限检查
        nameField.setAccessible(true);
        // 调用 set() 方法为 p 对象的 name 赋值
        nameField.set(p, "Alvin");

        final Field ageField = personClazz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(p, 26);
        System.out.println(p);
    }
}
