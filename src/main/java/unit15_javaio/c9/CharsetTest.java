package unit15_javaio.c9;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.SortedMap;

/**
 * @author Alvin.Li
 */
public class CharsetTest {
	public static void main(String[] args) {
		// 获取Java 支持的全部字符集
		SortedMap<String,Charset> map = Charset.availableCharsets();
		for (String alias : map.keySet())
		{
			System.out.println(alias + "------>" + map.get(alias));;
		}
		Properties p = System.getProperties();
		System.out.println(p.getProperty("file.encoding"));
	}
}
