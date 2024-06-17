package unit07_basicclasss.c3;

import java.util.Arrays;
import java.util.Random;

/**
 * @author alvin
 * @date 2020-05-24 9:56
 */
public class RandomTest {
    public static void main(String[] args) {
        final Random rand = new Random();
        System.out.println(rand.nextBoolean());
        final byte[] buffer = new byte[16];
        rand.nextBytes(buffer);
        System.out.println(Arrays.toString(buffer));
        // 0.0~0.1之间的伪随机数
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        // 平均值是 0.0,标准差是 1.0 的伪高斯数
        System.out.println(rand.nextGaussian());
        System.out.println(rand.nextInt());
        // 0~26
        System.out.println(rand.nextInt(26));
        System.out.println(rand.nextLong());
    }
}
