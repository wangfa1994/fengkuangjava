package unit15_javaio.c8.serializemutable;

import unit15_javaio.c8.entity.PersonNormal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-10 8:44
 * 由于 Java 对象序列化机制，同一个对象只会序列化一次，但是当序列化可变对象时，这就有问题了。
 * 如何解决此问题？
 *
 */
public class SerializeMutable {
    public static void main(String[] args) {
        // 创建一个 ObjectOutputStream 输出流
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./src/main/java/unit15_javaio/c8/serializemutable/mutable.txt"));
            // 创建一个 ObjectInputStream 输入流
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("./src/main/java/unit15_javaio/c8/serializemutable/mutable.txt"))) {

            PersonNormal per = new PersonNormal("孙悟空", 500);
            // 系统将 per 对象转换成字节序列并输出
            oos.writeObject(per);
            // 改变 per 对象的 name 实例变量值
            per.setName("周八戒");
            per.setAge(100);
            // 系统只是输出序列化编号,所以改变后的 name 不会被序列化
            oos.writeObject(per);
            PersonNormal p1 = (PersonNormal)ois.readObject();
            PersonNormal p2 = (PersonNormal)ois.readObject();
            // true
            System.out.println(p1 == p2);
            // 孙悟空
            System.out.println(p2.getName());
            System.out.println(p2.getAge());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
