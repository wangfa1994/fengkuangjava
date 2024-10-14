package unit17_socket.c172;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLPermission;
import java.util.List;
import java.util.Map;

/**
 * @author alvin
 * @date 2020-05-01 13:10
 */
public class GetPostTest {

    /**
     * 向指定 URL 发送 GET 方式的请求
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param) {
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realUrl = new URL(urlName);
            // 打开和 URL 之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
            // 获取所有的响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "---->" + map.get(key));
            }
            try(
                    // 定义 BufferedReader 输入流来读取 URL 的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")))
            {
                String line;
                while ((line = in.readLine()) != null) {
                    result += "\n" + line;
                }
            }
        } catch (Exception e) {
            System.out.println("发送 GET 请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向指定 URL 发送 POST 方式请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param) {
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和 URL 之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送 POST 请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);
            try(
                // 获取 URLConnection 对象对应的输出流
                PrintWriter out = new PrintWriter(conn.getOutputStream()))
            {
                // 发送请求参数
                out.print(param);
                // flush 输出流的缓存
                out.flush();
            }
            try(
                // 读取 URL 的响应
                final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")))
            {
                String line;
                while ((line = in.readLine()) != null) {
                    result += "\n" + line;
                }
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        // 发送 GET 请求
        final String s = GetPostTest.sendGet("http://wiki.monkey-kong.cn/", null);
        System.out.println("====>GET响应结果：");
        System.out.println(s);
        // 发送 POST 请求
//        final String s1 = GetPostTest.sendPost("http://www.baidu.com/", null);
//        System.out.println("====>POST响应结果：");
//        System.out.println(s1);
    }
}
