package unit17_socket.c174;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author alvin
 * @date 2020-05-02 15:47
 */
public class UdpServer {
    public static final int PORT = 30000;
    // 定义每个数据包的大小最大为 4kb
    private static final int DATA_LEN = 4096;
    // 定义接收网络数据的子接数组
    byte[] inBuff = new byte[DATA_LEN];
    // 以指定子接数组创建准备接收数据的 DatagramPacket 对象
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    // 定义一个用于发送的 DatagramPacket 对象
    private DatagramPacket outPacket;
    // 定义一个字符串数组,服务端发送该数组的元素
    String[] books = new String[] {
            "aaa","bbb","ccc"
    };
    public void init() throws IOException {
        try(
            // 创建 DatagramSocket 对象
            DatagramSocket socket = new DatagramSocket(PORT))
        {
            // 采用循环接收数据
            for (int i = 0; i < 1000; i++) {
                // 读取 Socket 中的数据,读到的数据放入 inPacket 封装的数组里
                socket.receive(inPacket);
                // 判断 inPacket.getData() 和 inBuff 是否是同一个数组
                System.out.println(inBuff == inPacket.getData());
                // 将接收到的内容转换成字符串后输出
                System.out.println(new String(inBuff, 0, inPacket.getLength()));
                // 从字符串数组中取出一个元素作为发送的数据
                byte[] sendData = books[i % 3].getBytes();
                outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());
                // 发送数据
                socket.send(outPacket);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new UdpServer().init();
    }
}
