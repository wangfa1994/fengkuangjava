package unit17_socket.c173.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author alvin
 * @date 2020-05-01 22:15
 */
public class NClient {
    // 定义监测 SocketChannel 的 Selector 对象
    private Selector selector = null;
    static final int PORT = 30000;
    // 定义处理编码和解码的字符集
    private Charset charset = Charset.forName("UTF-8");
    // 客户端 SocketChannel
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        // 调用 open 静态方法创建连接到指定主机的 SocketChannel
        sc = SocketChannel.open(isa);
        final int i = sc.validOps();
        System.out.println("sc validOps：" + i); // 1 + 4 + 8 = 13
        // 设置 sc 以非阻塞方式工作
        sc.configureBlocking(false);
        // 将 SocketChannel 对象注册到指定的 Selector
        sc.register(selector, SelectionKey.OP_READ);
        // 启动读取服务器端数据的线程
        new ClientThread().start();
        // 创建键盘输入流
        final Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // 读取键盘输入
            final String line = scan.nextLine();
            // 将键盘输入的内容输出到 SocketChannel 中
            sc.write(charset.encode(line));
        }
    }
    // 定义读取服务器端数据的线程
    private class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    // 遍历每个有而可用 IO 操作的 Channel 对应的 SelectionKey
                    for(SelectionKey sk : selector.selectedKeys()){
                        // 删除正在处理的 SelectionKey
                        selector.selectedKeys().remove(sk);
                        // 如果该 SelectionKey 对应的 Channel 中有可读的数据
                        if(sk.isReadable()) {
                            // 使用 NIO 读取 Channel 中的数据
                            SocketChannel sc = (SocketChannel)sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while (sc.read(buff) > 0) {
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            // 打印输出读取的内容
                            System.out.println("聊天信息：" + content);
                            // 为下一次读取做准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NClient().init();
    }
}
