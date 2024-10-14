package unit17_socket.c176;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author alvin
 * @date 2020-06-21 19:39
 * 模拟十万个连接线性连接服务端。测试服务端处理大量请求的性能。
 */
public class C10Kclient {
    public static void main(String[] args) {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        final InetSocketAddress serverAddr = new InetSocketAddress("192.168.238.66", 9090);
        for (int i = 10000; i < 65000; i++) {
            try {
                final SocketChannel client1 = SocketChannel.open();
                final SocketChannel client2 = SocketChannel.open();

                client1.bind(new InetSocketAddress("192.168.238.1", i));
                //  192.168.238.1：10000   192.168.238.66：9090
                client1.connect(serverAddr);
                boolean c1 = client1.isOpen();
                clients.add(client1);

//                client2.bind(new InetSocketAddress("192.168.0.5", i));
//                client2.connect(serverAddr);
//                boolean c2 = client2.isOpen();
//                clients.add(client2);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("clients " + clients.size());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
