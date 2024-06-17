package unit17_socket.c175;

import java.io.IOException;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author alvin
 * @date 2020-05-02 17:12
 */
public class DefaultProxySelectorTest {
    // 定义需要访问的网站地址
    static String urlStr = "http://www.baidu.com";

    public static void main(String[] args) throws URISyntaxException, IOException {
        // 获取系统的默认属性
        final Properties props = System.getProperties();
        // 通过设置系统属性设置 HTTP 访问所有的代理服务器的主机地址、端口
        props.setProperty("http.proxyHost", "192.168.10.96");
        props.setProperty("http.proxyPort", "8080");
        // 设置不使用代理的主机
        props.setProperty("http.nonProxyHosts", "localhost|192.168.10.*");
        // https
        props.setProperty("https.proxyHost", "192.168.10.96");
        props.setProperty("https.proxyPort", "443");
        // ftp
        props.setProperty("ftp.proxyHost", "192.168.10.96");
        props.setProperty("ftp.proxyPort", "2121");
        props.setProperty("ftp.nonProxyHosts", "localhost|192.168.10.*");
        // socket
        props.setProperty("socket.proxyHost", "192.168.10.96");
        props.setProperty("socket.proxyPort", "2121");
        // 获取系统默认的代理选择器
        final ProxySelector selector = ProxySelector.getDefault();
        System.out.println("系统默认的代理选择器：" + selector);
        // 根据 URI 动态决定所使用的代理服务器
        System.out.println("系统为 ftp://www.crazyit.org 选择的代理服务器为：" +
                ProxySelector.getDefault().select(new URI("ftp://www.crazyit.org")));

        URL url = new URL(urlStr);
        final URLConnection conn = url.openConnection();
        conn.setConnectTimeout(3000);
        try(
            Scanner scan = new Scanner(conn.getInputStream(), "utf-8"))
        {
            // 读取远程主机的内容
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        }
    }
}
