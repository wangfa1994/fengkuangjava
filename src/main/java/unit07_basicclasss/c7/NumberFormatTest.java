package unit07_basicclasss.c7;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author alvin
 * @date 2020-05-24 20:41
 */
public class NumberFormatTest {
    public static void main(String[] args) {
        // 需要给格式化的数字
        double db = 1234000.567;
        // 创建四个 Locale，分别代表中国、日本、德国、美国
        Locale[] locales = {Locale.CHINA, Locale.JAPAN,
            Locale.GERMAN, Locale.US};
        NumberFormat[] nf = new NumberFormat[12];
        // 为上面四个 Local 创建 12 个 NumberFormat 对象
        for (int i=0; i<locales.length; i++) {
            nf[i*3] = NumberFormat.getNumberInstance(locales[i]);
            nf[i*3 + 1] = NumberFormat.getPercentInstance(locales[i]);
            nf[i*3 + 2] = NumberFormat.getCurrencyInstance(locales[i]);
        }
        for (int i = 0; i < locales.length; i++) {
            String tip = i == 0 ? "-----中国的格式-----":
                i == 1 ? "------日本的格式------" :
                i == 2 ? "------德国的格式------" :
                "------美国的格式-----";
            System.out.println(tip);
            System.out.println("通用数值格式：" + nf[i*3].format(db));
            System.out.println("百分比数值格式：" + nf[i*3+1].format(db));
            System.out.println("货币数值格式：" + nf[i*3+2].format(db));
        }
    }
}
