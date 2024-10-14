package unit17_socket.c173.biomultithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 15:52
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        final Socket s = new Socket("127.0.0.1", 30000);
        // 客户端启动 ClientThread 线程不断地读取来自服务器的数据
        new Thread(new ClientThread(s)).start();
        // 获取该 Socket 对应的输出流
        final PrintStream ps = new PrintStream(s.getOutputStream());
        String line = null;
        // 不断地读取键盘输入
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null) {
            // 将用户的键盘输入内容写入 Socket 对应的输入流
            ps.println(line);
        }
    }
}
