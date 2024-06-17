package unit15_javaio.c8.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author alvin
 * @date 2020-05-12 20:56
 * 自定义对象序列化
 */
public class PersonCustom implements Serializable {
    private String name;
    private int age;
    /**
     * 注意此处没有提供无参构造器
     */
    public PersonCustom(String name, int age) {
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("invoke writeObject");
        // 将 name 实例变量值反转后写入二进制流
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        System.out.println("invoke readObject");
        // 将读取的字符串反转后赋给 name 实例变量
        this.name = ((StringBuffer)in.readObject()).reverse().toString();
        this.age = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
