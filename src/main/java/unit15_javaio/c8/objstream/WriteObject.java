package unit15_javaio.c8.objstream;

import unit15_javaio.c8.entity.PersonNormal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

/**
 * @author Alvin.Li
 * 使用对象流序列化
 */
public class WriteObject {
	public static void main(String[] args) {
		// 创建对象流
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/java/unit15_javaio/c8/objstream/object.txt"))) {
			// 对象序列化
			PersonNormal per = new PersonNormal("孙悟空", 500);
			per.setHeight(new BigDecimal("105"));
			oos.writeObject(per);

			PersonNormal per1 = new PersonNormal("alvin", 100);
			per1.setHeight(new BigDecimal("165"));
			oos.writeObject(per1);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
