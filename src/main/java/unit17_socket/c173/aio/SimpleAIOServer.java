package unit17_socket.c173.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @author alvin
 * @date 2020-05-02 9:30
 */
public class SimpleAIOServer {
    static final int PORT = 30000;

    public static void main(String[] args) {
        try(
            // 1. 创建 AsnychronousServerSocketChannel 对象
            AsynchronousServerSocketChannel serverChannel =
                    AsynchronousServerSocketChannel.open())
        {
            // 2. 绑定指定地址、端口
            serverChannel.bind(new InetSocketAddress("127.0.0.1",PORT));
            while (true) {
                // 接收客户端连接
                Future<AsynchronousSocketChannel> future = serverChannel.accept();
                // 获取连接完成后返回的 channel
                AsynchronousSocketChannel socketChannel = future.get();
                // 执行输出
                socketChannel.write(ByteBuffer.wrap("欢迎来到AIO世界！"
                        .getBytes("UTF-8"))).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
