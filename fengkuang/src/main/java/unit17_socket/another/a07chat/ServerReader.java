package unit17_socket.another.a07chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;

public class ServerReader extends Thread {

    private Socket socket;

    public ServerReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(socket.getInputStream());
            while (true) {
                int msgType = dis.readInt(); // 消息类型
                if (msgType == 1) {
                    //登录
                    String name = dis.readUTF();
                    System.out.println(name + "--->" + socket.getRemoteSocketAddress());
                    ServerChat.onLineSockets.put(socket, name);
                }
                writeMsg(msgType, dis);
            }
        } catch (Exception e) {
            System.out.println("有人下线了");
            ServerChat.onLineSockets.remove(socket);

            try {
                writeMsg(1, dis); //给所有的人发消息进行下线通知
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    private void writeMsg(int msgType, DataInputStream dis) throws Exception {
        String msg;
        if (msgType == 1) {
            // 读取所有在线人数发给客户端去更新自己的在线人数列表
            StringBuilder rs = new StringBuilder();
            Collection<String> onlineNames = ServerChat.onLineSockets.values();
            if (!onlineNames.isEmpty()) {
                for (String onlineName : onlineNames) {
                    rs.append(onlineName + Constants.SPILIT);
                }
                msg = rs.substring(0, rs.lastIndexOf(Constants.SPILIT));
                sendMsgToAll(msgType, msg);
            }
        } else if (msgType == 2) {
            //读取消息
            String newMsg = dis.readUTF();
            String sendName = ServerChat.onLineSockets.get(socket);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");

            String msgFinal = sendName + "  " + sdf.format(System.currentTimeMillis()) + "\r\n" +
                    "    " + newMsg + "\r\n";
            sendMsgToAll(msgType, msgFinal);

        } else if (msgType == 3) {
            //读取消息
            String newMsg = dis.readUTF();
            String sendName = ServerChat.onLineSockets.get(socket);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");

            String msgFinal = sendName + "  " + sdf.format(System.currentTimeMillis()) + "对您私发\r\n" +
                    "    " + newMsg + "\r\n";
            // 私发
            // 得到给谁私发
            String destName = dis.readUTF();
            sendMsgToOne(destName, msgFinal);
        }

    }

    private void sendMsgToOne(String destName, String msgFinal) {

        try {
            Set<Socket> allOnLineSockets = ServerChat.onLineSockets.keySet();
            for(Socket sk :  allOnLineSockets){
                // 得到当前需要私发的socket
                // 只对这个名字对应的socket私发消息
                if(ServerChat.onLineSockets.get(sk).trim().equals(destName)){
                    DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
                    dos.writeInt(2); // 消息类型
                    dos.writeUTF(msgFinal);
                    dos.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("发送异常");
        }
    }

    private void sendMsgToAll(int msgType, String msg) throws Exception {
        Set<Socket> allOnlineSockets = ServerChat.onLineSockets.keySet();
        for (Socket allOnlineSocket : allOnlineSockets) {
            DataOutputStream outputStream = new DataOutputStream(allOnlineSocket.getOutputStream());
            outputStream.writeInt(msgType);
            outputStream.writeUTF(msg);
            outputStream.flush();
        }


    }
}
