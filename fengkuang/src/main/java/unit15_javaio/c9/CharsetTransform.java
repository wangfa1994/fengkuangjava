package unit15_javaio.c9;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author alvin
 * @date 2020-05-17 19:46
 */
public class CharsetTransform {
    public static void main(String[] args) throws Exception{
        final Charset cn = StandardCharsets.UTF_8;
        final CharsetEncoder cnEncoder = cn.newEncoder();
        final CharsetDecoder cnDecoder = cn.newDecoder();
        // 创建一个 CharBuffer 对象
        final CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put('孙');
        cbuff.put('悟');
        cbuff.put('空');
        cbuff.flip();
        // 将 CharBuffer 中的字符序列转换成字节序列
        final ByteBuffer bbuff = cnEncoder.encode(cbuff);
        // 循环访问 ByteBuffer 中的每个字节
        for (int i = 0; i < bbuff.limit(); i++) {
            System.out.println(bbuff.get(i) + " ");
        }
        // 将 ByteBuffer 的数据解码成字符序列
        System.out.println("\n" + cnDecoder.decode(bbuff));
    }
}
