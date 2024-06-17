package unit17_socket.c175;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author alvin
 * @date 2020-05-02 16:39
 */
public class ProxyTest {
    // 代理服务器的地址
    final String PROXY_ADDR = "129.82.12.188";
    final int PROXY_PORT = 3124;
    // 定义需要访问的网站地址
    String urlStr = "http://www.baidu.com";
    public void init() throws Exception{
        URL url = new URL(urlStr);
        // 创建一个代理服务器对象
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress(PROXY_ADDR, PROXY_PORT));
        // 使用指定的代理服务器打开连接
        URLConnection conn = url.openConnection(proxy);
        // 设置超时时长
        conn.setConnectTimeout(3000);
        try(
            // 通过代理服务器读取数据的 Scanner
            Scanner scan = new Scanner(conn.getInputStream());
            PrintStream ps = new PrintStream("index.html"))
        {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line);
                // 将网页资源内容输出到指定输出流
                ps.println(line);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new ProxyTest().init();
    }
}
