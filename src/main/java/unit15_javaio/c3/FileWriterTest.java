package unit15_javaio.c3;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Alvin.Li
 */
public class FileWriterTest {
	public static void main(String[] args) {
		try (FileWriter fw = new FileWriter("poem.txt")) {
			// windos 换行符 \r\n；linux 换行符 \n
			fw.write("锦瑟 - 李商隐\r\n");
			fw.write("锦瑟无端五十弦，一弦一柱思华年。\r\n");
			fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n");
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
