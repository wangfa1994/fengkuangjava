package unit17_socket.c172;

import java.net.InetAddress;

/**
 * @author alvin
 * @date 2020-05-01 9:45
 */
public class InetAddressTest {
    public static void main(String[] args) throws Exception {
        // 根据主机名来获取实例
        InetAddress ip = InetAddress.getByName("wiki.monkey-kong.cn");
        // 判断是否可达
        System.out.println("我的博客是否可达：" + ip.isReachable(2000));
        System.out.println(ip.getCanonicalHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getHostName());

        // 根据原始 IP 地址来获取对应的实例
        InetAddress ip2 = InetAddress.getByAddress(new byte[]{104, 31, 66, 111});
        System.out.println(ip2.getHostName());
    }
}
