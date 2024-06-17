package unit15_javaio.c9;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author alvin
 * @date 2020-05-17 19:10
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        // 创建文件输入流
        try (FileInputStream fis = new FileInputStream("./src/main/java/unit15_javaio/c9/ReadFile.java");
            // 创建一个 FileChannel
            FileChannel fcin = fis.getChannel())
        {
            // 定义一个 ByteBuffer 对象，用于重复取水
            ByteBuffer bbuff = ByteBuffer.allocate(256);
            // 将 FileChannel 中的数据放入 ByteBuffer 中
            while (fcin.read(bbuff) != -1) {
                // 锁定 Buffer 的空白区
                bbuff.flip();
                // 创建 Charset 对象
                final Charset gbk = Charset.forName("UTF-8");
                // 创建解码器
                final CharsetDecoder decoder = gbk.newDecoder();
                // 转码
                final CharBuffer cbuff = decoder.decode(bbuff);
                System.out.println(cbuff);
                // Buffer 初始化，为下一次读取数据做准备
                bbuff.clear();
            }
        }
    }
}
