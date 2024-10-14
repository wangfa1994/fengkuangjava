package unit07_basicclasss.c7;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author alvin
 * @date 2020-05-24 20:23
 */
public class LoggerTest {
    public static void main(String[] args) throws IOException {
        // 获取 Logger 对象
        final System.Logger logger = System.getLogger("fkjava");
        // 设置日志级别(FINE 对应 DEBUG)
        Logger.getLogger("fkjava").setLevel(Level.FINE);
        // 设置使用 a.xml 保存日志记录
        Logger.getLogger("fkjava").addHandler(new FileHandler("a.xml"));
        logger.log(System.Logger.Level.DEBUG, "debug 信息");
        logger.log(System.Logger.Level.INFO, "info 信息");
        logger.log(System.Logger.Level.ERROR, "error 信息");
    }
}
