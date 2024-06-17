package unit07_basicclasss.c5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alvin
 * @date 2020-05-24 18:06
 */
public class FindGroup {
    public static void main(String[] args) {
        // 使用字符串模拟从网上得到的网页源码
        String str = "adadfadfadsf18594286805发打发打发" +
                "adfad18594286801放大算法的";
        final Matcher m = Pattern.compile("((13\\d)|(18\\d))\\d{8}")
                .matcher(str);
        // 将所有符合正则表达式的子串全部输出
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
