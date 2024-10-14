package unit15_javaio.c8.serialmechanism;

import unit15_javaio.c8.entity.PersonNormal;
import unit15_javaio.c8.entity.Teacher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-10 8:25
 * 实际上只序列化了3个对象，而且两个 Teacher 对象的 student 是同一个
 */
public class WriteTeacher {
    public static void main(String[] args) {
        // 创建一个 ObjectOutputStream 输出流
        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./src/main/java/unit15_javaio/c8/serialmechanism/teacher.txt"))) {
            PersonNormal per = new PersonNormal("孙悟空", 500);
            Teacher t1 = new Teacher("唐僧", per);
            Teacher t2 = new Teacher("菩提老祖", per);

            // 一次将四个对象写入输出流
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(per);
            oos.writeObject(t2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
