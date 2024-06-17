package unit18_classload.c2;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-04 16:10
 */
public class URLClassLoaderTest {
    private static Connection conn;
    // 定义一个获取数据库连接的方法
    public static Connection getConn(String url, String user,
        String pass) throws Exception{
        if(conn == null) {
            // 创建一个 URL 数组
            URL[] urls = {new URL("file:mysql-connection-java-5.1.30-bin.jar")};
            // 以默认的 ClassLoader 作为父 ClassLoader，创建 URLClassLoader
            URLClassLoader myClassLoader = new URLClassLoader(urls);
            // 加载 MySQL 的 JDBC 驱动，并创建默认实例
            Driver driver = (Driver)myClassLoader
                    .loadClass("com.mysql.cj.jdbc.Driver")
                    .getConstructor().newInstance();
            // 创建一个设置 JDBC 连接属性的 Properties 对象
            Properties props = new Properties();
            // 至少需要为该对象传入 user 和 password 两个属性
            props.setProperty("user", user);
            props.setProperty("password", pass);
            // 调用 Driver 对象的 connect 方法来取得数据库连接
            conn = driver.connect(url, props);
        }
        return conn;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getConn("jdbc:mysql://localhost:3306/mysql",
                "root", "root"));
    }
}
