package unit15_javaio.c8.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author alvin
 * @date 2020-05-12 21:34
 */
public class PersonExternal implements Externalizable {
    private String name;
    private int age;
    // Externalizable 形式序列化注意必须提供无参构造器,否则反序列化时会失败
    public PersonExternal(){}
    public PersonExternal(String name, int age) {
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // 将 name 反转后写入二进制流
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // 将读取的字符串反转后赋值给 name 实例变量
        this.name = ((StringBuffer)in.readObject()).reverse().toString();
        this.age = in.readInt();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
