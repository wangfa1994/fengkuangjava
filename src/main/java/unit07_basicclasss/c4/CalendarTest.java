package unit07_basicclasss.c4;

import java.util.Calendar;

/**
 * @author alvin
 * @date 2020-05-24 14:36
 */
public class CalendarTest {
    public static void main(String[] args) {
        final Calendar c = Calendar.getInstance();
        // 取出年
        System.out.println(c.get(Calendar.YEAR));
        // 取出月
        System.out.println(c.get(Calendar.MONTH));
        // 取出日
        System.out.println(c.get(Calendar.DATE));
        // 分别设置年月日时分秒
        c.set(2003, 10, 23, 12, 32, 23);
        System.out.println(c.getTime());
        // 前推一年
        c.add(Calendar.YEAR, -1);
        System.out.println(c.getTime());
        // 前推 8 个月
        c.add(Calendar.MONTH, -12);
        System.out.println(c.getTime());
    }
}
