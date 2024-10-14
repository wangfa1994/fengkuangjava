package unit07_basicclasss.c1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileTest {

	public static void main(String[] args) throws FileNotFoundException {
		// 文件作为输入
		Scanner sc = new Scanner(new File("./src/unit7_basicclasss/c71/ScannerFileTest.java"));
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}

}
