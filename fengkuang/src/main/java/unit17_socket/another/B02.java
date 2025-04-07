package unit17_socket.another;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// 一个客户端。一个服务端。客户端可以多次发消息
public class B02 {
    static class Client{

        public static void main(String[] args) {

            try {
                Socket socket = new Socket("127.0.0.1",9999);
                OutputStream outputStream = socket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);

                while (true){
                    Scanner scanner  = new Scanner(System.in);
                    String s = scanner.nextLine();
                    if(s.equals("exit")){
                        return;
                    }
                    printStream.println(s);
                }
            } catch (Exception e) {
                System.out.println("客户端发生异常");
            }

        }
    }

    static class Server{

        public static void main(String[] args) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(9999);

                Socket socket = serverSocket.accept();
                System.out.println("server Listen");
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line ;
                while ((line = bufferedReader.readLine())!=null){
                    System.out.println("服务端收到信息:"+line);
                }
            } catch (Exception e) {
                System.out.println("服务端发生异常");
            }
        }

    }
}
