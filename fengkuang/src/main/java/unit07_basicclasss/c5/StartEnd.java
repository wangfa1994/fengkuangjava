package unit07_basicclasss.c5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alvin
 * @date 2020-05-24 18:12
 */
public class StartEnd {
    public static void main(String[] args) {
        String regStr = "Java is very easy!";
        System.out.println("目标字符串是：" + regStr);
        final Matcher m = Pattern.compile("\\w+").matcher(regStr);
        while (m.find()) {
            System.out.println(m.group() + "子串的起始位置：" +
                    m.start() + ",其结束位置：" + m.end());
        }
    }

//    目标字符串是：Java is very easy!
//    Java子串的起始位置：0,其结束位置：4
//    is子串的起始位置：5,其结束位置：7
//    very子串的起始位置：8,其结束位置：12
//    easy子串的起始位置：13,其结束位置：17
}
