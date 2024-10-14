package unit07_basicclasss.c3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alvin
 * @date 2020-05-24 10:02
 */
public class RandomSeedTest {
    public static void main(String[] args) {
        final Random r1 = new Random(50);
        System.out.println("第一个种子为 50 的 Random 对象");
        System.out.println(r1.nextBoolean());
        System.out.println(r1.nextInt());
        System.out.println(r1.nextDouble());
        System.out.println(r1.nextGaussian());

        final Random r2 = new Random(50);
        System.out.println("第二个种子为 50 的 Random 对象");
        System.out.println(r2.nextBoolean());
        System.out.println(r2.nextInt());
        System.out.println(r2.nextDouble());
        System.out.println(r2.nextGaussian());

        // ThreadLocalRandom 对象
        final ThreadLocalRandom rand = ThreadLocalRandom.current();

        final Random r = new Random(System.currentTimeMillis());
    }
}
