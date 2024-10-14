package unit10_exception.c102;

import java.io.*;

/**
 * @author alvin
 * @date 2020-05-02 22:15
 */
public class AutoCloseTest {
    public static void main(String[] args) throws IOException {
        try(
                // 声明、初始化两个可关闭的资源
                BufferedReader br = new BufferedReader(new FileReader("a.txt"));
                PrintStream ps = new PrintStream(new FileOutputStream("b.txt")))
        {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("aaaaaabbbbbb");
        }
    }
}
