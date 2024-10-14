package unit17_socket.c173.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author alvin
 * @date 2020-05-01 15:04
 */
public class Client {
    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("127.0.0.1", 30000);
        socket.setSoTimeout(5000);

        // 将 Socket 对应的输入流包装成 BufferedReader
        final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final String line = br.readLine();
        System.out.println("服务器说：" + line);
        final PrintStream ps = new PrintStream(socket.getOutputStream());


        // Thread.sleep(6000); // 验证 SoTimeout，让 Client 超时
        final BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String iLine = null;
        while ((iLine = bufr.readLine()) != null) {
            // ps.print("我是01号客户端，请求连接。");
            System.out.println("================");
            ps.print(iLine);
            ps.flush();
        }

        ps.close();
        br.close();
        socket.close();
    }
}
