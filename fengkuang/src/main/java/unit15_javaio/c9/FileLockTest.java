package unit15_javaio.c9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 共享锁：可读不可写 必须要有 readChannel
 * 1. new FileInputStream().getChannel().tryLock(0L, Long.MAX_VALUE, true)
 * 2. new RandomAccessFile（“r”）.getChannel().tryLock(0L, Long.MAX_VALUE, true)
 * 3. RandomAccessFile（“rw”）.getChannel().tryLock(0L, Long.MAX_VALUE, true)
 * 独占锁：持有者可读写，非持有者不可读写 必须要有 writeChannel
 * 1. new FileOutputStream("copy.txt", true).getChannel().tryLock()
 * 2. RandomAccessFile（“rw”）.getChannel().tryLock()
 */
public class FileLockTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		try(
			@SuppressWarnings("resource")
			FileChannel channel = new FileOutputStream("./src/main/java/unit15_javaio/c9/copy.txt", true).getChannel())
		{
			// 使用非阻塞的方式对指定文件加锁
			FileLock lock = channel.tryLock(0L, Long.MAX_VALUE, false);
			Thread.sleep(10000);
			lock.release();
		}
	}
}
