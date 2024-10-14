package unit07_basicclasss.c4;

import java.util.Calendar;

/**
 * @author alvin
 * @date 2020-05-24 14:48
 */
public class CalendarLazyTest {
    public static void main(String[] args) {
        final Calendar cal = Calendar.getInstance();
        cal.set(2003, 7, 31);
        cal.set(Calendar.MONTH, 8);
        // 如果不注释,则显示10月份
        // System.out.println(cal.getTime());
        cal.set(Calendar.DATE, 5);
        System.out.println(cal.getTime());
    }
}
