package unit15_javaio.c6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @author Alvin.Li
 * JVM 读写其他进程数据
 * 其他进程的输出，对于 JVM 来说是输入。
 */
public class ReadFromProcess {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec("javac");
		p.waitFor(1, TimeUnit.MILLISECONDS);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"))) {
			String buff = null;
			while((buff = br.readLine()) != null) {
				System.out.println(buff);
			}
		}
	}
}
