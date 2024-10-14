package unit10_exception.c102;

import java.io.*;

/**
 * @author alvin
 * @date 2020-05-02 22:24
 */
public class AutoCloseTest2 {
    public static void main(String[] args) throws IOException {
        // 有 final 修饰的资源
        final BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        // 没有显示使用 final，但只要不对该变量重新赋值，该变量就是有效的 final
        PrintStream ps = new PrintStream(new FileOutputStream("b.txt"));
        // 只要将两个资源放在 try 后的圆括号内即可
        try (br;ps) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("aaaaaabbbbbb");
        }
    }
}
