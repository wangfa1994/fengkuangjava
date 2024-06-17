package unit17_socket.c173.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 17:01
 */
public class ServerThread extends Thread {
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            // 获取该 Socket 对应的输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 获取该 Socket 对应的输出流
            ps = new PrintStream(socket.getOutputStream());
            String line = null;
            while ((line = br.readLine()) != null) {
                // 以ServerThread开始和结束，则确定读取到的为登录的用户名
                if(line.startsWith(ChatProtocol.USER_ROUND)
                  && line.endsWith(ChatProtocol.USER_ROUND)) {
                    // 得到真实消息
                    String userName = getRealMsg(line);
                    // 如果用户名重复
                    if(Server.clients.map.containsKey(userName)){
                        System.out.println("登录失败，用户名重复。");
                        ps.println(ChatProtocol.NAME_REP);
                    } else {
                        System.out.println("登录成功。");
                        ps.println(ChatProtocol.LOGIN_SUCCESS);
                        Server.clients.put(userName, ps);
                    }
                } else if(line.startsWith(ChatProtocol.PRIVATE_ROUND)
                  && line.startsWith(ChatProtocol.PRIVATE_ROUND)){
                    // 以 PRIVATE_ROUND 开始和结尾，则确定为私聊信息
                    // 得到真实消息
                    String userAndMsg = getRealMsg(line);
                    // 以 SPLIT_SIGN 分割，前半是私聊用户，后半是聊天信息
                    String user = userAndMsg.split(ChatProtocol.SPLIT_SING)[0];
                    String msg = userAndMsg.split(ChatProtocol.SPLIT_SING)[1];
                    // 获取私聊用户对应的输出流，并发送私聊信息
                    Server.clients.map.get(user).println(Server.clients.
                            getKeyByValue(ps) + "悄悄对你说：" + msg);
                } else {
                    // 公聊
                    // 得到真实信息
                    String msg = getRealMsg(line);
                    for(PrintStream clientPs : Server.clients.valueSet()) {
                        clientPs.println(Server.clients.getKeyByValue(ps)
                            + "说" + msg);
                    }
                }
            }
        } catch (IOException e) {
            Server.clients.removeByValue(ps);
            System.out.println(Server.clients.map.size());
            // 关闭网络、IO资源
            try {
                if(br != null) {
                    br.close();
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
            e.printStackTrace();
        }
    }
    // 将读到的内容去掉前后的协议字符
    private String getRealMsg(String line) {
        return line.substring(ChatProtocol.PROTOCOL_LEN,
                line.length() - ChatProtocol.PROTOCOL_LEN);
    }
}
