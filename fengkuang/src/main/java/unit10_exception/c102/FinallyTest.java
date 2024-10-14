package unit10_exception.c102;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author alvin
 * @date 2020-05-02 21:56
 */
public class FinallyTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("a.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            // return 语句强制返回
            // return ;
            // exit 退出虚拟机。JDK 9 以前会导致不会执行 finally，
            // 到了 jdk 12，即使退出虚拟机，也会 执行 finally
             System.exit(1);
        } finally {
            // 关闭磁盘文件，回收资源
            if(fis != null) {
                try{
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            System.out.println("执行了finally");
        }
    }
}
