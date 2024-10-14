package unit15_javaio.c10;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Alvin.Li
 * 找文件
 */
public class FileVisitorTest {
	public static void main(String[] args) throws IOException {
		Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				System.out.println("正在访问" + file + "文件");
				if (file.endsWith("FileVisitorTest.java"))
				{
					System.out.println("已经找到目标文件");
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
			{
				System.out.println("正在访问：" + dir + "目录");
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
