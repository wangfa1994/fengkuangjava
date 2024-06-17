package unit16_thread.juc_1a2b3c;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author alvin
 * @date 2020-04-05 16:17
 */
public class T04_lock_condition {
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        CountDownLatch ct = new CountDownLatch(1);

        new Thread(()->{
            try {
                lock.lock();
                ct.countDown();
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    condition.signal();
                    condition.await();
                }
                condition.signal(); // 结束程序
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            try {
                ct.await(); // 保证顺序
                lock.lock();
                for (char i = 'A'; i <= 'Z'; i++) {
                    System.out.print(i);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }).start();

    }
}
