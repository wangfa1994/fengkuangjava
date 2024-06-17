package unit15_javaio.c8.version;

import java.io.*;

/**
 * 如果一个类升级后，只要它的 serialVersionUID 没变，序列化机制也会把他们当成同一个序列化版本。
 * 如果仅仅修改方法，反序列化不受影响，无需修改 serialVersionUID
 * 如果仅仅修改静态变量或瞬态变量，同上
 * 如果修改了非瞬态的实例变量
 *      类型修改：反序列失败，应该修改 serialVersionUID
 *      删除变量：序列化版本可以兼容，可以不更新 serialVersionUID
 *      新增变量：序列化版本可以兼容，可以不更新 serialVersionUID
 */
public class DerObj {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/java/unit15_javaio/c8/version/object.txt"))) {
            PersonVersion monkey = (PersonVersion)ois.readObject();
            System.out.println(monkey.getName());
            PersonVersion alvin = (PersonVersion)ois.readObject();
            System.out.println(alvin.getName());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
