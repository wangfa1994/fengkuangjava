package unit15_javaio.c6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Alvin.Li
 */
public class ReadStandard {
    public static void main(String[] args) {
        System.out.println("============>main");
        try (Scanner sc = new Scanner(System.in);
             PrintStream ps = new PrintStream(new FileOutputStream("out.txt"))) {
            sc.useDelimiter("\n");
            while(sc.hasNext()) {
                ps.println("键盘输入的内容是：" + sc.next());
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
