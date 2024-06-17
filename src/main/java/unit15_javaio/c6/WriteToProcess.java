package unit15_javaio.c6;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Alvin.Li
 * 未成功
 */
public class WriteToProcess {
	public static void main(String[] args) throws IOException {
		Process p = Runtime.getRuntime().exec(
				"java E:\\01_code\\01_javasebasic\\javabasic\\waojava\\target\\classes\\unit15_javaio\\c6\\ReadStandard");
		// 以 p 进程的输出流创建 PrintStream
		// 这个 ps 对本程序是输出流,对p进程是输入流
		try (PrintStream ps = new PrintStream(p.getOutputStream())) {
			ps.println("啊啊啊啊啊啊");
			ps.println(new WriteToProcess());
		}
	}
}
