package unit17_socket.tl.a03;

// https://note.youdao.com/ynoteshare/index.html?id=916f44987d1fe0e35ec935bf5391d762&type=note&_time=1742694871159

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 异步非阻塞， 由操作系统完成后回调通知服务端程序启动线程去处理， 一般适用于连接数较多且连接时间较长的应用
 *
 * AIO方式适用于连接数目多且连接比较长(重操作)的架构，JDK7 开始支持
 *
 * AIO 也被称为NIO2.0 是对NIO 的一层包装
 * 在NIO中，我们需要进行SelectionKey的循环处理，而AIO进行了封装，可以直接返回我们对应的channel，然后直接进行处理，简化了我们的开发
 *
 * 而且还引入了多线程模型进行处理，使得NIO在一个线程中的处理，变成多个
 *
 *
 * 为什么Netty使用NIO而不是AIO？
 * 在Linux系统上，AIO的底层实现仍使用Epoll，没有很好实现AIO，因此在性能上没有明显的优势，而且被JDK封装了一层不容易深度优化，
 * Linux上AIO还不够成熟。Netty是异步非阻塞框架，Netty在NIO上做了很多异步的封装。
 *
 *
 * AIO 是java提供的异步非阻塞功能的mini框架
 * 而Netty 也是异步非阻塞框架
 *
 *
 */
public class AioServer {

    public static void main(String[] args) throws Exception {
        final AsynchronousServerSocketChannel serverChannel =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9000));

        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                try {
                    System.out.println("2--"+Thread.currentThread().getName());
                    // 再此接收客户端连接，如果不写这行代码后面的客户端连接连不上服务端
                    serverChannel.accept(attachment, this);
                    System.out.println(socketChannel.getRemoteAddress());
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            System.out.println("3--"+Thread.currentThread().getName());
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, result));
                            socketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer buffer) {
                            exc.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

        System.out.println("1--"+Thread.currentThread().getName());
        Thread.sleep(Integer.MAX_VALUE);
    }

}
