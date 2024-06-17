package unit15_javaio.c8.version;

import java.io.*;

public class SerObj {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/java/unit15_javaio/c8/version/object.txt"))) {
            PersonVersion per = new PersonVersion("孙悟空", 500);
            PersonVersion per1 = new PersonVersion("alvin", 100);
            oos.writeObject(per);
            oos.writeObject(per1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
