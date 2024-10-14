package unit07_basicclasss.c5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alvin
 * @date 2020-05-24 18:16
 */
public class MatchesTest {
    public static void main(String[] args) {
        String[] mails = {
                "1111@163.com",
                "2222@gmail.com",
                "3333@outlook.com",
                "4444@abc.xx"
        };
        String mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        final Pattern mailPattern = Pattern.compile(mailRegEx);
        Matcher matcher = null;
        for (String mail : mails) {
            if (matcher == null) {
                matcher = mailPattern.matcher(mail);
            } else {
                matcher.reset(mail);
            }
            String result = mail + (matcher.matches() ? "是" : "不是") +
                "一个有效的邮件地址.";
            System.out.println(result);
        }
    }
}
