package unit17_socket.another.a07chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerChat {

    public static final Map<Socket,String> onLineSockets = new HashMap<>();

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(Constants.PORT);

        while (true){
            Socket accept = serverSocket.accept();

            new ServerReader(accept).start();

        }

    }
}
