package unit15_javaio.c5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Alvin.Li
 * 重定向标准输出
 */
public class RedirectOut {
	public static void main(String[] args) {
		try(PrintStream ps = new PrintStream(new FileOutputStream("out.txt"))) {
			// 本来标准输出是屏幕，现在重定向到 ps
			System.setOut(ps);
			System.out.println("普通字符串");
			System.out.println(new RedirectOut());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
