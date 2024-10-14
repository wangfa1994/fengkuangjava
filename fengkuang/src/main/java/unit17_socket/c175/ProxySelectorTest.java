package unit17_socket.c175;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-05-02 16:59
 */
public class ProxySelectorTest {
    // 代理服务器的地址
    final String PROXY_ADDR = "129.82.12.188";
    final int PROXY_PORT = 3124;
    // 定义需要访问的网站地址
    String urlStr = "http://www.baidu.com";
    public void init() throws Exception{
        // 注册默认的代理选择器
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                System.out.println("select");
                // 本程序总是返回某个固定的代理服务器
                List<Proxy> result = new ArrayList<>();
                result.add(new Proxy(Proxy.Type.HTTP,
                        new InetSocketAddress(PROXY_ADDR, PROXY_PORT)));
                return result;
            }
            // 根据业务需要返回特定的对应的代理服务器
            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("无法连接道指定的代理服务器.");
            }
        });
         URL url = new URL(urlStr);
        // 没有指定代理服务器,直接打开连接
        final URLConnection conn = url.openConnection();
        System.out.println(conn);
    }

    public static void main(String[] args) throws Exception {
        new ProxySelectorTest().init();
    }
}
