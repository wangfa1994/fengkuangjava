package unit07_basicclasss.c3;

/**
 * @author alvin
 * @date 2020-05-24 9:32
 */
public class MathTest {
    public static void main(String[] args) {
        System.out.println("===============三角运算===============");
        // 将弧度转换成角度
        System.out.println(Math.toDegrees(1.57));
        // 将角度转换为弧度
        System.out.println(Math.toRadians(90));
        // 反余弦
        System.out.println(Math.acos(1.2));
        // 反正弦
        System.out.println(Math.asin(0.8));
        // 反正切
        System.out.println(Math.atan(2.3));
        // 三角余弦
        System.out.println(Math.cos(1.57));
        // 双曲余弦
        System.out.println(Math.cosh(1.2));
        // 正弦
        System.out.println(Math.sin(1.57));
        // 双曲正弦
        System.out.println(Math.sinh(1.2));
        // 三角正切
        System.out.println(Math.tan(0.8));
        // 双曲正切
        System.out.println(Math.tanh(2.1));
        // 矩形坐标(x,y)转换成极坐标(r, thet)
        System.out.println(Math.atan2(0.1, 0.2));

        System.out.println("===============取整运算===============");
        // 小于目标的最大整数
        System.out.println(Math.floor(-1.2));
        // 大于目标的最小整数
        System.out.println(Math.ceil(1.2));
        // 四舍五入取整
        System.out.println(Math.round(2.3));

        System.out.println("===============乘方开方指数运算===============");
        // 平方根
        System.out.println(Math.sqrt(2.3));
        // 立方根
        System.out.println(Math.cbrt(9));
        // e 的 n 次幂
        System.out.println(Math.exp(2));
        // sqrt(x2 + y2) 没有中间溢出或下溢
        System.out.println(Math.hypot(4, 4));
        // 按照 IEEE 754 标准,对两个参数进行余数运算
        System.out.println(Math.IEEEremainder(5, 2));
        // 计算乘方
        System.out.println(Math.pow(3, 2));
        // 自然对数
        System.out.println(Math.log(12));
        // 底数为 10 的对数
        System.out.println(Math.log10(9));
        // 参数与1之和的自然对数
        System.out.println(Math.log1p(9));

        System.out.println("===============符号相关运算===============");
        // 绝对值
        System.out.println(Math.abs(-4.5));
        // 符号赋值
        System.out.println(Math.copySign(1.2, -1.0));
        // 符号函数
        System.out.println(Math.signum(2.3));

        System.out.println("===============大小相关运算===============");
        // 最大值
        System.out.println(Math.max(2.3, 4.5));
        // 最小值
        System.out.println(Math.min(1.2, 3.4));
        // 相邻浮点数
        System.out.println(Math.nextAfter(1.2, 1.0));
        System.out.println(Math.nextUp(1.2));
        // 随机数
        System.out.println(Math.random());
    }
}
