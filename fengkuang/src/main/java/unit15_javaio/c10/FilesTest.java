package unit15_javaio.c10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alvin.Li
 */
public class FilesTest {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("./src/main/java/unit15_javaio/c10/FilesTest.java");
		// 复制文件
		Files.copy(p, new FileOutputStream("./src/main/java/unit15_javaio/c10/copy.txt"));
		System.out.println("是否为隐藏文件："+Files.isHidden(p));

		// 一次性读取所有行
		List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
		System.out.println("=============>一次读取所有行");
		lines.forEach(System.out::println);
		System.out.println("=============>一次读取所有行结束");

		// 判断指定文件的大小
		System.out.println("文件大小："+Files.size(p));

		// 直接写多行文字
		List<String> poem = new ArrayList<>();
		poem.add("莫愁前路无知己");
		poem.add("天下谁人不识君");
		Files.write(Paths.get("./src/main/java/unit15_javaio/c10/poem.txt"), poem, StandardCharsets.UTF_8);

		// 当前目录下的所有文件和子目录
		Files.list(Paths.get(".")).forEach(System.out::println);
		Files.lines(p, StandardCharsets.UTF_8).forEach(System.out::println);
		
		FileStore cStore = Files.getFileStore(Paths.get("C:"));
		System.out.println("c:共有空间：" + cStore.getTotalSpace());
		System.out.println("c:可用空间：" + cStore.getUsableSpace());
	}
}
