package unit07_basicclasss.c4;

import java.time.*;

/**
 * @author alvin
 * @date 2020-05-24 14:57
 */
public class NewDatePackageTest {
    public static void main(String[] args) {
        System.out.println("=========Clock用法============");
        // 当前 Clock
        final Clock clock = Clock.systemDefaultZone();//Clock.systemUTC();
        // 通过 Clock 获取当前时刻
        System.out.println("当前时刻为:" + clock.instant());
        // clock 对应的毫秒数,与 System.currentTimeMillis()相同
        System.out.println(clock.millis());
        System.out.println(System.currentTimeMillis());

        System.out.println("=========Duration用法============");
        final Duration d = Duration.ofSeconds(36000);
        System.out.println("6000秒相当于:" + d.toMinutes() + "分");
        System.out.println("6000秒相当于" + d.toHours() + "小时");
        System.out.println("6000秒相当于" + d.toDays() + "天");
        // 在 clock 基础上增加 6000 秒,返回新的 Clock
        final Clock clock2 = Clock.offset(clock, d);
        System.out.println("当前时刻加 36000 秒为:" + clock2.instant());

        System.out.println("=========Instant用法============");
        // 获取当前时间
        final Instant instant = Instant.now();
        System.out.println(instant);
        // instant 添加 6000 秒,返回新的 Instant
        final Instant instant2 = instant.plusSeconds(6000);
        System.out.println(instant2);
        // 根据字符串解析 Instant 对象
        final Instant instant3 = Instant.parse("2014-02-23T10:12:35.342Z");
        System.out.println(instant3);
        // instant3 添加 5 小时 4 分钟
        final Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
        System.out.println(instant4);
        // instant4 的 5 天以前的时刻
        final Instant instant5 = instant4.minus(Duration.ofDays(5));
        System.out.println(instant5);

        System.out.println("=========LocalDate用法============");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        // 2014 年的第 146 天
        localDate = LocalDate.ofYearDay(2014, 146);
        System.out.println(localDate);
        // 设置为 2014 年 5 月 21 日
        localDate = LocalDate.of(2014, Month.MAY, 21);
        System.out.println(localDate);

        System.out.println("=========LocalTime用法============");
        // 当前时间
        LocalTime localTime = LocalTime.now();
        // 设置为 22 点 33 分
        localTime = LocalTime.of(22, 33);
        System.out.println(localTime);
        // 一天中的第 5503 秒
        localTime = LocalTime.ofSecondOfDay(5503);
        System.out.println(localTime);

        System.out.println("=========LocalDateTime用法============");
        // 当前日期、时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 当前日期、时间加上 5 小时 3 分钟
        LocalDateTime future = localDateTime.plusHours(5).plusMinutes(3);
        System.out.println("当前日期、时间的 5 小时 3 分之后：" + future);

        System.out.println("=========Year YearMonth MonthDay用法============");
        Year year = Year.now();
        System.out.println(year);
        year = year.plusYears(5);
        System.out.println(year);
        // 根据指定月份获取 YearMonth
        YearMonth ym = year.atMonth(10);
        System.out.println("year年10月：" + ym);
        // 加5年减三个月
        ym = ym.plusYears(5).minusMonths(3);
        System.out.println("year年10月加5年减3个月：" + ym);
        MonthDay md = MonthDay.now();
        System.out.println("当前月日：" + md);
        // 设置为 5 月 23 日
        MonthDay md2 = md.with(Month.MAY).withDayOfMonth(23);
        System.out.println("5月23日为：" + md2);
    }
}
