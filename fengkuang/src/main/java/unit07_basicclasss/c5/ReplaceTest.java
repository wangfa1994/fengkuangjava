package unit07_basicclasss.c5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alvin
 * @date 2020-05-24 18:22
 */
public class ReplaceTest {
    public static void main(String[] args) {
        String[] msgs = {
            "Java has regular expressions in 1.4",
            "regular expression now expressing in Java",
            "Java represses oracular expression"
        };
        final Pattern p = Pattern.compile("re\\w*");
        Matcher matcher = null;
        for (int i=0; i<msgs.length; i++) {
            if (matcher == null) {
                matcher = p.matcher(msgs[i]);
            } else {
                matcher.reset(msgs[i]);
            }
            System.out.println(matcher.replaceAll("哈哈:)"));
        }
    }
}
