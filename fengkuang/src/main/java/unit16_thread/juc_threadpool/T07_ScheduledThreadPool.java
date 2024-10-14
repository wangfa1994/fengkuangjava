package unit16_thread.juc_threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author alvin
 * @date 2020-04-06 14:02
 */
public class T07_ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        es.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName());
        }, 500, 2000, TimeUnit.MILLISECONDS);
    }
}
