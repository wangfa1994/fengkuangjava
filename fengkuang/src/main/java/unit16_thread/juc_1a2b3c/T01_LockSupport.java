package unit16_thread.juc_1a2b3c;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author alvin
 * @date 2020-04-05 15:11
 */
public class T01_LockSupport {
    static Thread t1, t2;
    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char i = 'A'; i <= 'Z'; i++) {
                LockSupport.park();
                System.out.print(i);
                LockSupport.unpark(t1);
            }
        });

        // 注意一个先打印一个先park；另外注意防止两个线程都处于park
        t1.start();
        t2.start();
    }
}
