package unit15_javaio.c4;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author Alvin.Li
 * 访问字符串：StringReader、StringWriter
 * StringReader：直接以 String 作为构造器参数
 */
public class StringNodeTest {
	public static void main(String[] args) {
		String src = "从明天起，做一个幸福的人\n"
				+ "喂马，劈柴，周游世界\n"
				+ "从明天起，关心粮食和蔬菜\n"
				+ "我有一所房子，面朝大海，春暖花开\n";
		char[] buffer = new char[32];
		int hasRead = 0;
		try (StringReader sr = new StringReader(src)) {
			// 采用循环读取的方式读取字符串
			while((hasRead = sr.read(buffer)) > 0) {
				System.out.print(new String(buffer, 0, hasRead));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try(StringWriter sw = new StringWriter()) {
			sw.write("有一个美丽的新世界，\n");
			sw.write("她在远方等我，\n");
			sw.write("那里有天真的孩子，\n");
			sw.write("还有姑娘的酒窝");
			System.out.println("----下面是 sw 字符串节点里的内容----");
			System.out.println(sw.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
