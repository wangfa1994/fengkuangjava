package unit15_javaio.c9;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Alvin.Li
 * FileChannel 的使用
 */
public class RandomFileChannelTest {
	public static void main(String[] args) throws IOException {
		File f = new File("./src/main/java/unit15_javaio/c9/copy.txt");
		// a.txt 的 RandomAccessFile，可读可写
		try (RandomAccessFile raf = new RandomAccessFile(f, "rw");
			// 获取 channel
			FileChannel randomChannel = raf.getChannel())
		{
			// 映射只读的 buffer
			ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			// 设置 pos
			randomChannel.position(f.length());
			// buffer 中的数据写入 channel
			randomChannel.write(buffer);
		}
	}
}
