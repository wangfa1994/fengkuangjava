package unit15_javaio.c7;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Alvin.Li
 */
public class AppendContent {
	public static void main(String[] args) {
		try(RandomAccessFile raf = new RandomAccessFile("out.txt", "rw")) {
			// 指针定位到最后
			raf.seek(raf.length());
			raf.write("追加的内容\r\naaaaaaa\r\n".getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
