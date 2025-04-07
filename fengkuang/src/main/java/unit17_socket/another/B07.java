package unit17_socket.another;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B07 {
    //转发
    static class Client{

        public static void main(String[] args) {

            try {
                Socket socket = new Socket("127.0.0.1",9999);
                OutputStream outputStream = socket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);

                // 开一个线程用于接收转发的消息
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            byte[] content = new byte[1024];
                            int read ;
                            while ((read = inputStream.read(content)) !=-1){
                                System.out.println("接收到转发数据"+new String(content,0,read));
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                }).start();


                // 既可以读，又可以写
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


    static class RetranClient{
        // 只读
        public static void main(String[] args) {
            // 转发接收
            try {
                Socket socket = new Socket("127.0.0.1",9999);
                InputStream inputStream = socket.getInputStream();
                byte[] content = new byte[1024];
                int read ;
                while ((read = inputStream.read(content)) !=-1){
                    System.out.println("接收到转发数据"+new String(content,0,read));
                }

                // 如下方式不行，是因为接收格式不匹配问题
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("客户端收到转发信息" + socket.getRemoteSocketAddress() + ":" + line);
                }
            } catch (Exception e) {
                System.out.println("客户端发生异常");
            }

        }
    }


    static class Server {

        // 交给线程池处理，避免我们针对线程的创建销毁等逻辑处理
        private static ExecutorService executorService = Executors.newFixedThreadPool(3);

        public static final Set<Socket> allSocketSet = new HashSet<>();

        public static void main(String[] args) throws Exception {
            ServerSocket serverSocket = null;

            serverSocket = new ServerSocket(9999);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端连接"+socket.getRemoteSocketAddress());
                executorService.execute(new WorkRunnable(socket));
                System.out.println("上线保存："+allSocketSet.add(socket));
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

                    // 获取所有的socket，进行转发数据
                    for (Socket item : Server.allSocketSet) {
                        if(item != socket){
                            // 不等于当前的socket 进行转发
                            System.out.println("开始转发"+item.getRemoteSocketAddress());
                            OutputStream outputStream = item.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                            bufferedWriter.write("---"+line);
                            bufferedWriter.flush();
                            System.out.println("转发完成");
                        }
                    }


                }
            } catch (IOException e) {
                System.out.println("服务端发生异常:");
            }
        }
    }
}
