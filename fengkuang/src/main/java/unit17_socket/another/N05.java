package unit17_socket.another;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class N05 {

    // BIO 形式下的 发送文件
    static class Client{

        public static void main(String[] args) {

            try(FileInputStream inputStream = new FileInputStream("text.txt"))
            {
                Socket socket = new Socket("127.0.0.1",9999);
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputStream);
                dos.writeUTF(".txt");

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                FileChannel inputStreamChannel = inputStream.getChannel();
                int read;
                while ((read = inputStreamChannel.read(buffer)) != -1){
                    buffer.flip();
                    dos.write(buffer.array(),0,buffer.remaining());
                    buffer.clear();
                }

                dos.flush();
                Scanner scanner  = new Scanner(System.in);
                String s = scanner.nextLine();
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

                DataInputStream dataInputStream = new DataInputStream(inputStream); //  DataInputStream 会read 阻塞 对吗？

                String suffix = dataInputStream.readUTF();

                FileOutputStream outputStream = new FileOutputStream(UUID.randomUUID().toString()+suffix);
                FileChannel channel = outputStream.getChannel();
                byte[] content  = new byte[1024];

                int length;
                while ((length = dataInputStream.read(content))!=-1){ // 真正的阻塞是Socket的阻塞
                    outputStream.write(content,0,length);
                }
                outputStream.close();
                System.out.println("save file ok");
            } catch (IOException e) {
                System.out.println("服务端发生异常:");
            }
        }
    }

}
