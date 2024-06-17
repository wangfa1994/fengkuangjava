package unit17_socket.c173.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 14:59
 * BIO：阻塞模型。Server 一次只能处理一个 client，必须等到当前 client 处理完成后，才能再次调用 ss.accept()，才能接受新的连接
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建一个 ServerSocket，用于监听客户端 Socket 的连接请求
        ServerSocket ss = new ServerSocket(30000);
        // 循环不断地接收来自客户端的请求
        while (true) {
            // 阻塞，等待客户端连接。每当接收到客户端 Socket 的请求时，服务端也会对应产生一个 Socket
            Socket s = ss.accept();
            // s.setSoTimeout(5000);

            // 将 Socket 对应的输出流包装成 PrintStream
            final PrintStream ps = new PrintStream(s.getOutputStream());
            // 进行普通的 IO 操作
            // Thread.sleep(6000); // 验证 SoTimeout，让 Client 超时
            ps.println("新年快乐");

            final BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            final String line = br.readLine();
            System.out.println("客户端说：" + line);

            // 关闭输出流，关闭 Socket
            ps.close();
            br.close();
            s.close();
        }
    }
}
