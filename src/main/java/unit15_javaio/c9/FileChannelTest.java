package unit15_javaio.c9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Alvin.Li
 * FileChannel 的使用
 */
public class FileChannelTest {
	public static void main(String[] args) {
		File f = new File("./src/main/java/unit15_javaio/c9/FileChannelTest.java");
		try (FileInputStream fis = new FileInputStream(f);
			// 获取 Channel
			FileChannel inChannel = fis.getChannel();
			FileChannel outChannel = new FileOutputStream("./src/main/java/unit15_javaio/c9/copy.txt").getChannel())
		{
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			outChannel.write(buffer);
			buffer.clear();
			// 创建解码器对象
			Charset charset = StandardCharsets.UTF_8;
			CharsetDecoder decoder = charset.newDecoder();
			// 使用解码器将 ByteBuffer 转换为 CharBuffer
			CharBuffer charBuffer = decoder.decode(buffer);
			System.out.println(charBuffer.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
