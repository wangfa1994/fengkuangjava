package unit15_javaio.c4;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * @author alvin
 * @date 2020-05-05 21:35
 * PushbackReader：推回输入流
 * unread 是其关键方法
 */
public class PushbackTest {
    public static void main(String[] args) {
        // 创建一个 PushBackReader 对象,指定推回缓冲区长度为 64
        try(PushbackReader pr = new PushbackReader(
                new FileReader("./src/main/java/unit15_javaio/c4/PushbackTest.java"), 64)) {

            char[] buf = new char[32];
            // 用以保存上次读取的字符串内容
            String lastContent = "";
            int hasRead =  0;

            // 循环读取文件内容
            while ((hasRead = pr.read(buf))>0) {
                // 将读取的内容转换成字符串
                String content = new String(buf, 0, hasRead);
                int targetIndex = 0;
                // 将上次读取的字符串和本次读取的字符串拼接起来
                // 查看是否包含目标字符串,如果包含
                if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                    // 将本次内容和上次内容一起推回缓冲区
                    pr.unread((lastContent+content).toCharArray());
                    // 重新定义一个长度为 targetIndex 的 char 数组
                    if(targetIndex > 32) {
                        buf = new char[targetIndex];
                    }
                    // 再次读取指定长度的内容(就是目标字符串之前的内容)
                    pr.read(buf, 0, targetIndex);
                    // 打印读取的内容
                    System.out.print(new String(buf, 0, targetIndex));
                    System.exit(0);
                } else {
                    // 打印上次读取的内容
                    System.out.print(lastContent);
                    // 将本次内容设置为上次读取内容
                    lastContent = content;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
