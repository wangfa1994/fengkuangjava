package unit07_basicclasss.c8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author alvin
 * @date 2020-05-24 19:11
 */
public class NewFormatterParse {
    public static void main(String[] args) {
        // 定义一个任意格式的日期、时间字符串
        String str1 = "2014==04==12 01时06分09秒";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter formatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");
        // 执行解析
        LocalDateTime dt1 = LocalDateTime.parse(str1, formatter1);
        System.out.println(dt1);

        // 解析另外一个字符串
        String str2 = "2014$$$04月$$$13 01时06分09秒";
        DateTimeFormatter formatter2 = DateTimeFormatter
                .ofPattern("yyyy$$$MM月$$$dd HH时mm分ss秒");
        LocalDateTime dt2 = LocalDateTime.parse(str2, formatter2);
        System.out.println(dt2);
    }
}
