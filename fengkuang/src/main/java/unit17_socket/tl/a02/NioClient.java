package unit17_socket.tl.a02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {

    public static void main(String[] args) throws IOException {


        ByteBuffer dsts = ByteBuffer.allocate(1024);
        // 客户端使用 SocketChannel进行open处理
        /*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",9000));*/
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));
        socketChannel.configureBlocking(false);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.next();
            dsts.put(msg.getBytes());
            dsts.flip();
            socketChannel.write(dsts);
            dsts.clear();
        }

    }
}
