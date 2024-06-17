package unit15_javaio.c8.externaliable;

import unit15_javaio.c8.entity.PersonExternal;
import unit15_javaio.c8.entity.PersonNormal;

import java.io.*;
import java.math.BigDecimal;

/**
 * @author Alvin.Li
 * 使用对象流序列化-Externalizable 方式
 */
public class ReadWriteObject {
	public static void main(String[] args) throws ClassNotFoundException {
		// 创建对象流
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/java/unit15_javaio/c8/externaliable/object.txt"));
			 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/java/unit15_javaio/c8/externaliable/object.txt"))) {
			// 对象序列化
			PersonExternal per = new PersonExternal("孙悟空", 500);
			PersonExternal per1 = new PersonExternal("alvin", 100);
			oos.writeObject(per);
			oos.writeObject(per1);

			PersonExternal monkey = (PersonExternal)ois.readObject();
			System.out.println(monkey.getName());
			PersonExternal alvin = (PersonExternal)ois.readObject();
			System.out.println(alvin.getName());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
