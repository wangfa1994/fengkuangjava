package unit17_socket.c173.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;

/**
 * @author alvin
 * @date 2020-05-01 21:46
 */
public class NServer {
    // 用于监测所有 Channel 状态的 Selector
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");
    ServerSocketChannel ssc = null;

    public void init() throws IOException {
        // 获取 Selector 对象
        selector = Selector.open();
        // 通过 open 方法来打开一个未绑定的 ServerSocketChannel 实例
        ssc = ServerSocketChannel.open();
        final InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        // 将该 ServerSocketChannel 绑定到指定 IP 地址
        ssc.bind(isa);
        // 设置 ServerSocketChannel 以非阻塞方式工作
        ssc.configureBlocking(false);
        // 将 server 注册到指定的 Selector 对象
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        // select 阻塞
        while (selector.select() > 0) {
            System.out.println("select()>0");
            System.out.println(selector.selectedKeys());
            // 处理已选择的 SelectionKey
            for(SelectionKey sk : selector.selectedKeys()) {
                System.out.println("attachment："+sk.attachment());
                // 从 selector 上已选择的key集合删除当前key
                selector.selectedKeys().remove(sk);
                // 如果 sk 对应的 Channel 包含客户端的连接请求
                if(sk.isAcceptable()){
                    System.out.println("sk isAcceptable");
                    // 调用 accept 方法接收连接，产生服务端的 SocketChannel
                    SocketChannel sc = ssc.accept();
                    // 设置采用非阻塞模式
                    sc.configureBlocking(false);
                    // 将该 SocketChannel 也注册到 selector
                    sc.register(selector, SelectionKey.OP_READ);
                    // 将 sk 对应的 Channel 设置成准备接收其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }
                // 如果 sk 对应的 Channel 有数据需要读取
                if(sk.isReadable()) {
                    System.out.println("sk isReadable");
                    sk.attach("sk server read attach");
                    // 读取该 SelectKey 对应的 Channel
                    SocketChannel sc = (SocketChannel) sk.channel();
                    // 定义准备执行读取数据的 ByteBuffer
                    final ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    // 开始读取数据
                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content += charset.decode(buff);
                        }
                        // 打印从该 sk 对应的 Channel 里读取到的数据
                        System.out.println("读取的数据：" + content);
                        // 将 sk 对应的 Channel 设置成准备下一次读取
                        sk.interestOps(SelectionKey.OP_READ);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        // 从 Selector 中删除指定的 SelectionKey
                        sk.cancel();
                        if(sk.channel() != null) {
                            sk.channel().close();
                        }
                    }
                    // 如果 content 的长度大于 0，即聊天信息不为空
                    if(content.length()>0) {
                        // 遍历该 selector 里注册的所有 SelectionKey
                        for(SelectionKey key : selector.keys()) {
                            // 获取该key对应的 Channel
                            Channel targetChannel = key.channel();
                            // 如果该 Channel 是 SocketChannel 对象
                            if(targetChannel instanceof SocketChannel){
                                // 将读到的内容写入还 Channel 中
                                SocketChannel dest = (SocketChannel)targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }

                }
            }
        }
        System.out.println("====> app finished...");
    }

    public static void main(String[] args) throws IOException {
        new NServer().init();
    }
}
