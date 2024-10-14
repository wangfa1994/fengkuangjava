package unit07_basicclasss.c4;

import java.util.Date;

/**
 * @author alvin
 * @date 2020-05-24 12:41
 */
public class DateTest {
    public static void main(String[] args) {
        final Date d1 = new Date();
        // 当前时间之后的 100 ms 时间
        final Date d2 = new Date(System.currentTimeMillis() + 100);
        System.out.println(d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));
    }
}
