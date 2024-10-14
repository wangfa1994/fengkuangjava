package unit15_javaio.c4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Alvin.Li
 * 处理流的用法，构造器参数为节点流
 */
public class DealStreamTest {
	public static void main(String[] args) {
		// 节点流
		try(FileOutputStream fos = new FileOutputStream("text.txt");
			// 包装为处理流
			PrintStream ps = new PrintStream(fos)) {
			ps.println("普通字符串");
			ps.println(new DealStreamTest());
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
