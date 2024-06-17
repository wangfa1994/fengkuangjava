package unit15_javaio.c4;

import java.io.*;

/**
 * @author Alvin.Li
 * InputStreamReader：转换流。字节输入流转为字符输入流
 * OutputStreamWriter：转换流。字节输出流转为字符输出流
 * BufferedReader：处理流
 */
public class ChangeByteToCharTest {
	public static void main(String[] args) throws IOException {
		try (InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(reader)) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.equals("exit")) {
					break;
					// System.exit(1);
				}
				System.out.println("输入内容为：" + line);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("================================");
		try (FileOutputStream fos = new FileOutputStream("./ops.test");
			OutputStreamWriter osw = new OutputStreamWriter(fos)) {
			osw.write("那么我为啥不直接用 FileWriter 呢？");
		}
	}
}
