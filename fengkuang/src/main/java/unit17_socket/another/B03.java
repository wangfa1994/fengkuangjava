package unit17_socket.another;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// 多个客户端。一个服务端。客户端可以多次发消息
public class B03 {

    static class Client{

        // 多次启动，表示多个客户端
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

    static class Server {

        public static void main(String[] args) throws Exception {
            ServerSocket serverSocket = null;

            serverSocket = new ServerSocket(9999);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端连接"+socket.getRemoteSocketAddress());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("server Listen");
                            InputStream inputStream = socket.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                System.out.println("服务端收到信息"+socket.getRemoteSocketAddress()+":" + line);
                            }
                        } catch (IOException e) {
                            System.out.println("服务端发生异常:");
                        }
                    }
                }).start();

            }

        }

    }

}
