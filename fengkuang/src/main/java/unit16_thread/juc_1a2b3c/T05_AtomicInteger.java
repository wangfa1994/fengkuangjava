package unit16_thread.juc_1a2b3c;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alvin
 * @date 2020-04-05 16:39
 */
public class T05_AtomicInteger {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        new Thread(()->{
            for (int i = 1; i <= 26; i++) {
                // 写个循环，不打印不罢休
                while (atomicInteger.get() != 1){}
                System.out.print(i);
                atomicInteger.set(2);
            }
        }).start();

        new Thread(()->{
            for (char i = 'A'; i <= 'Z'; i++) {
                while (atomicInteger.get() != 2){}
                System.out.print(i);
                atomicInteger.set(1);
            }
        }).start();
    }
}
