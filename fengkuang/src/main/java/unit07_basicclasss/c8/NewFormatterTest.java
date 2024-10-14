package unit07_basicclasss.c8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author alvin
 * @date 2020-05-24 19:04
 */
public class NewFormatterTest {
    public static void main(String[] args) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                // 直接使用常量创建
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                // 使用本地化的不同风格来创建
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,
                        FormatStyle.MEDIUM),
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),
                // 根据模式字符串创建
                DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss")
        };
        LocalDateTime date = LocalDateTime.now();
        // 依次使用不同的格式器进行格式化
        for (int i = 0; i<formatters.length; i++) {
            System.out.println(date.format(formatters[i]));
            System.out.println(formatters[i].format(date));
        }
    }
}
