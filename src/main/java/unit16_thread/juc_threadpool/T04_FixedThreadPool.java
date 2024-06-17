package unit16_thread.juc_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author alvin
 * @date 2020-04-06 13:23
 */
public class T04_FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
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
        System.out.println(executorService);

        executorService.shutdown();
        // executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());

        System.out.println(executorService);
        Thread.sleep(5000);

        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(executorService);
    }
}
