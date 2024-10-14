package unit07_basicclasss.c1;

import java.util.Scanner;

public class ScannerLongTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 如果输入不是 long 则程序退出
		while (sc.hasNextLong()) {
			System.out.println(sc.nextLong());
		}
	}

}
