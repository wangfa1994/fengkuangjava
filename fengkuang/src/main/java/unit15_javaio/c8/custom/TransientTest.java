package unit15_javaio.c8.custom;

import unit15_javaio.c8.entity.PersonTransient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

/**
 * @author alvin
 * @date 2020-05-12 20:40
 * 通过 transient 关键字修饰实例变量，表明序列化时忽略该字段。
 */
public class TransientTest {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./src/main/java/unit15_javaio/c8/custom/transient.txt"));
            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./src/main/java/unit15_javaio/c8/custom/transient.txt"))) {

            PersonTransient per = new PersonTransient("Alvin", 26);
            per.setHeight(new BigDecimal(1000));
            oos.writeObject(per);
            PersonTransient p = (PersonTransient)ois.readObject();
            System.out.println(p.getName());
            System.out.println(p.getAge());
            System.out.println(p.getHeight());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
