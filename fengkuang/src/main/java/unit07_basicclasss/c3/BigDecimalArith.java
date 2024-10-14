package unit07_basicclasss.c3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author alvin
 * @date 2020-05-24 10:17
 */
public class BigDecimalArith {
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    // 私有构造器
    private BigDecimalArith() {}
    // 提供精确的加法运算
    public static double add(double v1, double v2) {
        final BigDecimal b1 = BigDecimal.valueOf(v1);
        final BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    // 提供精确的减法运算
    public static double sub(double v1, double v2) {
        final BigDecimal b1 = BigDecimal.valueOf(v1);
        final BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    // 提供精确的乘法运算
    public static double mul(double v1, double v2) {
        final BigDecimal b1 = BigDecimal.valueOf(v1);
        final BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    // 提供(相对)精确的除法运算
    public static double div(double v1, double v2) {
        final BigDecimal b1 = BigDecimal.valueOf(v1);
        final BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, DEF_DIV_SCALE, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(BigDecimalArith.add(0.05, 0.01));
        System.out.println(BigDecimalArith.sub(1.0, 0.42));
        System.out.println(BigDecimalArith.mul(4.015, 100));
        System.out.println(BigDecimalArith.div(123.3, 100));
    }
}
