package unit17_socket.c173.biomultithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 15:43
 * 负责处理每个线程通信的线程类
 */
public class ServerThread implements Runnable{
    // 定义当前线程所处理的 socket
    Socket s = null;
    // 该线程所处理的 Socket 对应的输入流
    BufferedReader br = null;
    public ServerThread(Socket s) throws IOException {
        this.s = s;
        // 初始化该 socket 对应的输入流
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    @Override
    public void run() {
        try {
            String content = null;
            // 采用循环，不断从 socket 中读取客户端发送的数据
            while ((content = readFromClient()) != null) {
                // 遍历 socketList 中每个 Socket
                // 将读到的内容向每个 Socket 发送一次
                for (Socket s : MyServer.socketList) {
                    final PrintStream ps = new PrintStream(s.getOutputStream());
                    ps.println(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 定义读取客户端数据的方法
    private String readFromClient() {
        try {
            System.out.println("客户端说："+br.readLine());
            return br.readLine();
        } catch (IOException e) {
            // 如果报错，表明该 Socket 对应的客户端已经关闭，移除对应服务端 socket
            MyServer.socketList.remove(s);
        }
        return null;
    }
}
