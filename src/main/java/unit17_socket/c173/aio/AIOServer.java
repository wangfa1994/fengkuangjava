package unit17_socket.c173.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author alvin
 * @date 2020-05-02 12:34
 */
public class AIOServer {
    static final int PORT = 30000;
    final static String UTF_8 = "utf-8";
    static List<AsynchronousSocketChannel> channelList = new ArrayList<>();
    public void startListen() throws Exception {
        // 创建一个线程池
        final ExecutorService executor = Executors.newFixedThreadPool(20);
        // 以指定线程池来创建一个 AsynchronousChannelGroup
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup
                .withThreadPool(executor);
        // 以指定线程池来创建一个 AsynchronoutServerSocketChannel
        AsynchronousServerSocketChannel assc
                = AsynchronousServerSocketChannel.open(channelGroup)
                // 指定监听本机的 PORT 端口
                .bind(new InetSocketAddress("127.0.0.1", PORT));
        // 使用 CompletionHandler 接收来自客户端的连接请求
        assc.accept(null, new AcceptHandler(assc));
        // Thread.sleep(10000);
    }

    public static void main(String[] args) throws Exception {
        AIOServer server = new AIOServer();
        server.startListen();
        Thread.sleep(10000);
    }
}
// 实现自己的 CompletionHandler 类
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

    private AsynchronousServerSocketChannel assc;
    public AcceptHandler(AsynchronousServerSocketChannel assc){
        this.assc = assc;
    }

    // 定义一个 ByteBuffer 准备读取数据
    ByteBuffer buff = ByteBuffer.allocate(1024);

    // 当实际 IO 操作完成时触发该方法
    @Override
    public void completed(AsynchronousSocketChannel sc, Object attachment) {
        // 记录新连接进来的 Channel
        AIOServer.channelList.add(sc);
        // 准备接收客户端的下一次连接
        assc.accept(null, this);
        sc.read(buff, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
                // 将 buff 中的内容转换为字符串
                String content = StandardCharsets.UTF_8.decode(buff).toString();
                // 遍历每个 Channel,将收到的信息写入各 Channel 中
                for(AsynchronousSocketChannel c : AIOServer.channelList){
                    try {
                        c.write(ByteBuffer.wrap(content.getBytes(AIOServer.UTF_8)))
                                .get();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                buff.clear();
                // 读取下一次数据
                sc.read(buff, null, this);
            }
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败:" + exc);
                // 从该 Channel 中读取数据失败,就将该 Channel 删除
                AIOServer.channelList.remove(sc);
            }
        });
    }
    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println("连接失败:" + exc);
    }
}
