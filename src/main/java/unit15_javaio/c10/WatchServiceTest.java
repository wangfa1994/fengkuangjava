package unit15_javaio.c10;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * @author Alvin.Li
 * 监听文件的创建、删除、修改
 */
public class WatchServiceTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		// 为 C: 盘根路径注册监听
		Paths.get("D:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
		while (true) {
			System.out.println("start======>");
			// 获取下一个文件变化事件
			WatchKey key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println(event.context() + " 文件发生了 " + event.kind() + "事件");
			}
			// 重设 WatchKey
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
			System.out.println("end======>");
		}
	}
}
