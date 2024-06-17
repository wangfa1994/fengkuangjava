package unit16_thread.juc_threadpool;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author alvin
 * @date 2020-04-05 20:27
 * 自定义线程池
 */
public class T03_ThreadPool {

    static class Task implements Runnable{
        int i;
        Task(int i){
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ==> " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return "task["+i+"]";
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),  // 队列大小
//                Executors.defaultThreadFactory(), // 线程工厂
                new UserThreadFactory("test"),
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 拒绝策略

        // 启动八个线程
        for (int i = 0; i < 8; i++) {
            pool.execute(new Task(i));
        }

        // pool.submit() // submit 和 execute 有啥区别？
        System.out.println("队列："+pool.getQueue());
        // 再加任务就会执行拒绝策略，应为定义的线程池最多只能8个任务
        pool.execute(new Task(100));
        System.out.println("队列："+pool.getQueue());

        pool.shutdown();
    }
}
