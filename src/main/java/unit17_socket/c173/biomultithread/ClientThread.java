package unit17_socket.c173.biomultithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 15:57
 */
public class ClientThread implements Runnable {
    // 该线程负责处理的 Socket
    private Socket s;
    // 该线程所处理的 Socket 对应的输入流
    BufferedReader br = null;
    public ClientThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    @Override
    public void run() {
        try {
            String content = null;
            // 不断地读取 Socket 输入流中的内容，并将这些内容打印输出
            while ((content = br.readLine()) != null) {
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
