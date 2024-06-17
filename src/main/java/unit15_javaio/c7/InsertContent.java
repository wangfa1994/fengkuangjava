package unit15_javaio.c7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Alvin.Li
 */
public class InsertContent {
	public static void insert(String fileName, long pos, String insertContent) throws IOException {
		File tmp = File.createTempFile("tmp", null);
		tmp.deleteOnExit();
		try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			FileOutputStream tmpOut = new FileOutputStream(tmp);
			FileInputStream tmpIn = new FileInputStream(tmp)) {
			raf.seek(pos);
			// 将插入点后的内容读入临时文件中保存
			byte[] bbuf = new byte[64];
			int hasRead = 0;
			while ((hasRead = raf.read(bbuf)) > 0) 
			{
				tmpOut.write(bbuf, 0, hasRead);
			}
			// 追加需要插入的内容
			raf.seek(pos);
			raf.write(insertContent.getBytes());
			// 将插入点后的内容写回源文件
			while ((hasRead = tmpIn.read(bbuf)) > 0)
			{
				raf.write(bbuf, 0, hasRead);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		insert("./src/main/java/unit15_javaio/c7/InsertContent.java", 45, "插入的内容\r\t");
	}
}
