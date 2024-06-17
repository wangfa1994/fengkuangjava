package unit17_socket.c173.personal;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 16:55
 */
public class Server {

    private static final int SERVER_PORT = 30000;
    // 使用 UserMap 来保存每个客户名字和对应输出流之间的映射
    public static UserMap<String, PrintStream> clients = new UserMap<>();
    public void init() {
        try(// 创建一个 ServerSocket，用于监听客户端 Socket 的连接请求
            ServerSocket ss = new ServerSocket(SERVER_PORT))
        {
            while (true) {
                final Socket socket = ss.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("服务器启动失败。");
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }
}
