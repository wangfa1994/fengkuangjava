package unit16_thread.juc_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  @author alvin
 *  @date 2020-04-06 13:38
 * SingleThreadExecutor：只有一个线程的线程池，可以保证任务顺序执行
 * 为什么要有单线程的线程池？自己 new 一个线程不就行了？
 * 因为线程池有任务队列，有线程生命周期管理，提供了封装
 */
public class T05_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 6; i++) {
            final int j = i;
            executorService.execute(()->{
                System.out.println(j + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }
}
