package unit17_socket.c173.personal;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author alvin
 * @date 2020-05-01 17:28
 */
public class Client {
    private static final int SERVER_PORT = 30000;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;
    public void init() {
        try {
            // 初始化代表键盘的输入流
            keyIn = new BufferedReader(new InputStreamReader(System.in));
            // 连接到服务器
            socket = new Socket("127.0.0.1", SERVER_PORT);
            // 获取该 Socket 对应的输入流和输出流
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tip = "";
            while (true) {
                String userName = JOptionPane.showInputDialog(tip
                  + "输入用户名");
                // 在用户输入用户民前后增加协议字符串后发送给服务器
                ps.println(ChatProtocol.USER_ROUND + userName + ChatProtocol.USER_ROUND);;
                // 读取服务器的响应
                final String result = brServer.readLine();
                // 如果用户名重复，则开始下次循环
                if(result.equals(ChatProtocol.NAME_REP)) {
                    tip = "用户名重复！请重新";
                    continue;
                }
                // 如果服务器返回成功，则循环结束
                if(result.equals(ChatProtocol.LOGIN_SUCCESS)) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("找不到远程服务器，请确定服务器已经启动");
            closeRs();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("网络异常！请重新登录");
            closeRs();
            System.exit(1);
        }
        // 以该 Socket 对应的输入流启动 ClientThread 线程
        new ClientThread(brServer).start();
    }
    // 定义一个读取键盘输出，并向网络发送的方法
    private void readAndSend() {
        try{
            // 不断服务键盘输入
            String line = null;
            while ((line = keyIn.readLine()) != null) {
                // 如果发送的信息中有冒号，且以 // 开头，则认为私聊
                if(line.indexOf(":")>0 && line.startsWith("//")){
                    line = line.substring(2);
                    ps.println(ChatProtocol.PRIVATE_ROUND +
                            line.split(":")[0] + ChatProtocol.SPLIT_SING +
                            line.split(":")[1] + ChatProtocol.PRIVATE_ROUND
                            );
                } else {
                    ps.println(ChatProtocol.MSG_ROUND + line + ChatProtocol.MSG_ROUND);
                }
            }
        } catch (IOException ex) {
            System.out.println("网络通信异常！请重新登录！");
            closeRs();
            System.exit(1);
        }
    }
    // 关闭 Socket、输入流、输出流
    private void closeRs() {
        try {
            if (keyIn != null) {
                keyIn.close();
            }
            if (brServer != null) {
                brServer.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.init();
        client.readAndSend();
    }
}
