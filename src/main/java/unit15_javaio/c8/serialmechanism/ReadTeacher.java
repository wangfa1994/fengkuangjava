package unit15_javaio.c8.serialmechanism;

import unit15_javaio.c8.entity.PersonNormal;
import unit15_javaio.c8.entity.Teacher;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author alvin
 * @date 2020-05-10 8:32
 */
public class ReadTeacher {
    public static void main(String[] args) {
        // 创建一个 ObjectInputStream 输入流
        try (final ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./src/main/java/unit15_javaio/c8/serialmechanism/teacher.txt"))) {
            // 依次读取 ObjectInputStream 输入流中的四个对象
            Teacher t1 = (Teacher)ois.readObject();
            Teacher t2 = (Teacher)ois.readObject();
            PersonNormal p = (PersonNormal)ois.readObject();
            Teacher t3 = (Teacher)ois.readObject();

            // 输出 true
            System.out.println("t1 的 student 引用是否和 p 相同:" + (t1.getStudent() == p));
            // 输出 true
            System.out.println("t2 的 student 引用是否和 p 相同:" + (t2.getStudent() == p));
            // 输出 true
            System.out.println("t2 和 t3 是否是同一个对象:" + (t2 == t3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
