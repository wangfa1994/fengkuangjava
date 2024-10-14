package unit16_thread.juc_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author alvin
 * @date 2020-04-06 13:51
 */
public class T06_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(executorService);
        for (int i = 0; i < 6; i++) {
            executorService.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        Thread.sleep(2000);
        System.out.println(executorService);
        executorService.shutdown();
        System.out.println(executorService);
    }
}
