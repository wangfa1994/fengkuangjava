package unit17_socket.another;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B04 {

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

    static class Server {

        // 交给线程池处理，避免我们针对线程的创建销毁等逻辑处理
        private static ExecutorService executorService = Executors.newFixedThreadPool(3);

        public static void main(String[] args) throws Exception {
            ServerSocket serverSocket = null;

            serverSocket = new ServerSocket(9999);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端连接"+socket.getRemoteSocketAddress());
                executorService.execute(new WorkRunnable(socket));
            }

        }

    }

    static class WorkRunnable implements Runnable{
        private  Socket socket;

        public WorkRunnable(Socket socket) {
            this.socket = socket;
        }

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
    }

}
