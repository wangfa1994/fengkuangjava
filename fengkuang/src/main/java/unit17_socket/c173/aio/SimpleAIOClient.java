package unit17_socket.c173.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author alvin
 * @date 2020-05-02 9:42
 */
public class SimpleAIOClient {
    static final int PORT = 30000;

    public static void main(String[] args) throws Exception {
        // 用于读取数据的 ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(1024);
        Charset utf = Charset.forName("UTF-8");
        try(
            // 1. 创建 AsynchronousSocketChannel 对象
            AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open())
        {
            // 2. 连接远程服务器
            clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
            buff.clear();
            // 3. 从 clientChannel 中读取数据
            clientChannel.read(buff).get();
            buff.flip();
            // 将 buff 中的内容转换为字符串
            String content = utf.decode(buff).toString();
            System.out.println("服务器信息:" + content);
        }
    }
}
