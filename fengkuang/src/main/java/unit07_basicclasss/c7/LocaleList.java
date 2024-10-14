package unit07_basicclasss.c7;

import java.util.Locale;

/**
 * @author alvin
 * @date 2020-05-24 19:36
 */
public class LocaleList {
    public static void main(String[] args) {
        // 返回 Java 所支持的全部国家和语言的数组
        final Locale[] localeList = Locale.getAvailableLocales();
        for (int i = 0; i<localeList.length; i++) {
            System.out.println(localeList[i].getDisplayCountry() +
                    "=" + localeList[i].getCountry() + " " +
                    localeList[i].getDisplayLanguage() +
                    "=" + localeList[i].getLanguage());
        }
    }
}
