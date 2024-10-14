package unit07_basicclasss.c3;

import java.math.BigDecimal;

/**
 * @author alvin
 * @date 2020-05-24 10:12
 */
public class BigDecima1Test {
    public static void main(String[] args) {
        final BigDecimal f1 = new BigDecimal("0.05");
        final BigDecimal f2 = BigDecimal.valueOf(0.01);
        final BigDecimal f3 = new BigDecimal(0.05);
        System.out.println("使用 String 作为构造器参数:");
        System.out.println(f1.add(f2)); // 0.06
        System.out.println(f1.subtract(f2)); // 0.04
        System.out.println(f1.multiply(f2)); // 0.0005
        System.out.println(f1.divide(f2)); // 5

        System.out.println("使用 double 作为构造器参数:");
        // 0.06000000000000000277555756156289135105907917022705078125
        System.out.println(f3.add(f2));
        System.out.println(f3.subtract(f2));
        System.out.println(f3.multiply(f2));
        System.out.println(f3.divide(f2));
    }
}
