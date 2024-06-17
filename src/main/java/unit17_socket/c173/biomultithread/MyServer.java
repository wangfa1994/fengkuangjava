package unit17_socket.c173.biomultithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alvin
 * @date 2020-05-01 15:39
 */
public class MyServer {
    /**
     * 定义保存所有 Socket 的集合
     */
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        final ServerSocket ss = new ServerSocket(30000);
        while (true) {
            // 阻塞，将一直等待别人的连接
            final Socket s = ss.accept();
            socketList.add(s);
            // 每当客户端连接后启动一个 ServerThread 线程为该客户端服务
            new Thread(new ServerThread(s)).start();
        }
    }
}
