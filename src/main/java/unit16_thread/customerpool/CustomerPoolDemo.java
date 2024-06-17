package unit16_thread.customerpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerPoolDemo {

    public static void main(String[] args) {
        threadPool();
    }

    public static void threadPool() {
        ExecutorService executor =new ThreadPoolExecutor(2, 2, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),
                new NamedThreadFactory("threadName"),new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + "-----over");
                }
            };

            executor.execute(runnable);
        }
    }

    static class NamedThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            if (name != null) {
                namePrefix = name + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
            } else {
                namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
            }
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
