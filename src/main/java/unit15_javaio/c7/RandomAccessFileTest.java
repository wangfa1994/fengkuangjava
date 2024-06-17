package unit15_javaio.c7;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Alvin.Li
 * RandomAccessFile：可以实现追加、插入操作，主要是 seek 方法。
 * 局限性是只能操作文件节点，不可操作其他节点。
 */
public class RandomAccessFileTest {
	public static void main(String[] args)
	{
		try (RandomAccessFile raf = new RandomAccessFile("./src/main/java/unit15_javaio/c7/RandomAccessFileTest.java", "r")) {
			System.out.println("RandomAccessFile 的文件指针的初始位置：" + raf.getFilePointer());
			raf.seek(300);
			byte[] bbuf = new byte[1024];
			int hasRead = 0;
			while ((hasRead = raf.read(bbuf)) > 0)
			{
				System.out.print(new String(bbuf, 0, hasRead));
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
