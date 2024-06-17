package unit07_basicclasss.c7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alvin
 * @date 2020-05-24 20:51
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) throws ParseException {
        Date d = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中第D天");
        // 公元2020年中第145天
        System.out.println(sdf1.format(d));

        // 一个非常特殊的日期字符串
        String str = "14###3月##21";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y###MMM##d");
        // Fri Mar 21 00:00:00 CST 2014
        System.out.println(sdf2.parse(str));
    }
}
