package unit17_socket.another.a08niochat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOChat {

    public static void main(String[] args) throws Exception {
        ByteBuffer dsts = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 3333));
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
    // 监听客户端连接


