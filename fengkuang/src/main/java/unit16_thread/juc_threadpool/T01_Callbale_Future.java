package unit16_thread.juc_threadpool;

import java.util.concurrent.*;

/**
 * @author alvin
 * @date 2020-04-05 19:15
 * Executor：线程执行接口，将线程定义和执行分开
 * ExecutorService：线程生命周期控制
 * 认识 Callable、Future、FutureTask
 */
public class T01_Callbale_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Callable 和 Runnable 类似。可以有返回值（Future），但是不能直接作为 Thread 的 target
        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        };

        FutureTask<String> futureTask = new FutureTask<String>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get()); // 阻塞

        ExecutorService poll = Executors.newCachedThreadPool();
        Future<String> future = poll.submit(callable);
        System.out.println(future.get()); // 阻塞

        poll.shutdown();
    }
}
