package unit07_basicclasss.c7;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author alvin
 * @date 2020-05-24 20:31
 */
public class LoggerI18N {
    public static void main(String[] args) throws IOException {
        // 加载国际化资源包
        ResourceBundle rb = ResourceBundle.getBundle("unit7_basicclasss.c7.i18n.mess",
                Locale.getDefault(Locale.Category.FORMAT));
        // 获取 System.Logger 对象
        System.Logger logger = System.getLogger("fkjava", rb);
        Logger.getLogger("fkjava").setLevel(Level.INFO);
        // 设置使用 a.xml 保存日志记录
        Logger.getLogger("fkjava").addHandler(new FileHandler("a.xml"));
        logger.log(System.Logger.Level.DEBUG, "debug");
        logger.log(System.Logger.Level.INFO, "info");
        logger.log(System.Logger.Level.ERROR, "error");
    }
}
