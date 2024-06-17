package unit15_javaio.c1;

import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		// Windows 路径分隔用反斜杠（\）表示，但反斜杠在Java 中表示转义，所以需要用双反斜杠(\\)；
		// 或者，Java 程序支持将斜线（/）当成平台无关的路径分隔符
		File file = new File("aaa.txt");
		System.out.println(file.createNewFile());
		
		// 访问文件名相关（对象所对应文件或目录名称、路径名、绝对路径、绝对路径名、父目录名、重命名）
		System.out.println("访问文件名相关:");
		System.out.println(file.getName());
		System.out.println(file.getPath());
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParent());
		System.out.println(file.getAbsoluteFile().getParent());
		//System.out.println(file.renameTo(new File("c")));
		
		// 文件检测相关（对象所对应的文件或目录是否存在、是否可写、是否可读、是否可执行、是否文件、是否目录、是否绝对路径）
		System.out.println("文件检测相关:");
		System.out.println(file.exists());
		System.out.println(file.canWrite());
		System.out.println(file.canRead());
		System.out.println(file.canExecute());
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.isAbsolute());
		
		// 文件常规信息（最后修改时间、长度）
		System.out.println("文件常规信息:");
		System.out.println(file.lastModified());
		System.out.println(file.length());
		
		// 文件操作相关（新增、删除）
		System.out.println("文件操作相关:");
		//System.out.println(file.delete());
		File tmpFile = File.createTempFile("aaa", ".txt", new File(".//src"));
		tmpFile.deleteOnExit();
		
		System.out.println("目录操作相关:");
		System.out.println(new File("aaa").mkdir());
		System.out.println("当期路径下的所有文件和路径如下:");
		String[] fileList = new File(".").list();
		for (String fileName : fileList) {
			System.out.println(fileName);
		}
		System.out.println("系统所有根路径如下：");
		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println(root);
		}
		
	}
}
