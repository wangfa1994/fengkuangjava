package unit07_basicclasss.c7;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author alvin
 * @date 2020-05-24 20:02
 */
public class MessageFormatTest {
    public static void main(String[] args) {
        // 定义一个 Locale 变量
        Locale currentLocale = null;
        // 如果运行程序指定了两个参数
        if (args.length == 2) {
            // 使用运行程序的两个参数构造 Locale 实例
            currentLocale = new Locale(args[0], args[1]);
        } else {
            // 否则直接使用系统默认的 Locale
            currentLocale = Locale.getDefault(Locale.Category.FORMAT);
        }
        ResourceBundle bundle = ResourceBundle.getBundle("unit7_basicclasss.c7.i18n.mess", currentLocale);
        String msg = bundle.getString("msg");
        System.out.println(MessageFormat.format(msg, "alvin", new Date()));
    }
}
