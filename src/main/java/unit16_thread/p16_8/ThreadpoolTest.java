package unit16_thread.p16_8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadpoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(1);
        // 2. 创建 target
        Runnable target = () ->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()
                + "的 i 值为：" + i);
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        // 3. target 放入线程池
        pool.submit(target);
        TimeUnit.SECONDS.sleep(10);
        pool.execute(target);
        // 4. 关闭线程池
       // pool.shutdown();
    }
}
