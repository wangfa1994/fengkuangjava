package unit07_basicclasss.c7;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author alvin
 * @date 2020-05-24 19:44
 */
public class I18nTest {
    public static void main(String[] args) {
        // 取得系统默认的国家/语言环境
        Locale myLocale = Locale.getDefault(Locale.Category.FORMAT);
        // 根据指定的国家/语言环境加载资源文件
        ResourceBundle bundle = ResourceBundle.getBundle("unit7_basicclasss.c7.i18n.mess", myLocale);
        System.out.println(bundle.getString("hello"));
    }
}
