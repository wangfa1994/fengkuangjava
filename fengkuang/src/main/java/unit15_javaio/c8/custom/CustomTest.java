package unit15_javaio.c8.custom;


import unit15_javaio.c8.entity.PersonCustom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-12 21:03
 * 通过重写 writeObject、readObject 实现自定义序列化
 */
public class CustomTest {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("./src/main/java/unit15_javaio/c8/custom/custom.txt"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/java/unit15_javaio/c8/custom/custom.txt"))) {
            PersonCustom per = new PersonCustom("Alvin", 26);
            oos.writeObject(per);
            PersonCustom p = (PersonCustom)ois.readObject();
            System.out.println(p.getName());
            System.out.println(p.getAge());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
