package unit17_socket.tl.a02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 同步非阻塞，服务器实现模式为一个线程可以处理多个请求(连接)，
 * 客户端发送的连接请求都会注册到多路复用器selector上，多路复用器轮询到连接有IO请求就进行处理，JDK1.4开始引入。
 *
 *
 * NIO方式适用于连接数目多且连接比较短（轻操作） 的架构， 比如聊天服务器， 弹幕系统， 服务器间通讯，编程比较复杂
 *
 * 如果连接数太多的话，会有大量的无效遍历，
 * 假如有10000个连接，其中只有1000个连接有写数据，但是由于其他9000个连接并没有断开，
 * 我们还是要每次轮询遍历一万次，其中有十分之九的遍历都是无效的，这显然不是一个让人很满意的状态。
 *
 *
 */

public class NioServer {
    // 保存客户端连接
    static List<SocketChannel> channelList = new ArrayList<>();

    // 没有进行使用select 多路复用器

    public static void main(String[] args) throws IOException, InterruptedException {

        // 创建NIO ServerSocketChannel,与BIO的serverSocket类似
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000)); // 这个是1.4引入的serverSocketChannel

        // ServerSocket serverSocket = new ServerSocket(9000); // 这个是1.0的ServerSocket

        // 设置ServerSocketChannel为非阻塞
        serverSocket.configureBlocking(false); // true 表示为阻塞的形式
        System.out.println("服务启动成功");

        while (true) { // 进行断点调试，会发现一直在轮训处理，
            // 非阻塞模式accept方法不会阻塞，否则会阻塞
            // NIO的非阻塞是由操作系统内部实现的，底层调用了linux内核的accept函数
            SocketChannel socketChannel = serverSocket.accept(); // 这里如果是设置false 不会进行阻塞，
            if (socketChannel != null) { // 如果有客户端进行连接
                System.out.println("连接成功");
                // 设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                // 保存客户端连接在List中
                channelList.add(socketChannel);
            }
            // 遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator(); // 这里是遍历所有的链接，进行数据读取，有可能没有数据
            while (iterator.hasNext()) { // 循环所有的socketchannel ，不管是否存在数据
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                // 非阻塞模式read方法不会阻塞，否则会阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据，把数据打印出来
                if (len > 0) {
                    System.out.println("接收到消息：" + new String(byteBuffer.array(),0,len));
                } else if (len == -1) { // 如果客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    } // 使用 telnet 127.0.0.1 9000 进行链接，然后发送消息
}
