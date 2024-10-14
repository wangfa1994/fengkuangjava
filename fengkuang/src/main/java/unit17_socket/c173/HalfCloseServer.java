package unit17_socket.c173;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author alvin
 * @date 2020-05-01 21:04
 */
public class HalfCloseServer {
    public static void main(String[] args) throws IOException {
        final ServerSocket ss = new ServerSocket(30000);
        final Socket socket = ss.accept();
        final PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("服务器的第一行数据");
        ps.println("服务器的第二行数据");
        ps.println("服务器的第三行数据");
        // 关闭 socket 的输出流，表明 socket 还未关闭
        socket.shutdownOutput();
        // 如果关闭输入流，会导致对应的 socket 直接关闭
        // ps.close();
        // false，表明 socket 还没有关闭
        System.out.println(socket.isClosed());
        // 还可以继续读取输入
        final Scanner scan = new Scanner(socket.getInputStream());
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
        socket.close();
        ss.close();
    }
}
