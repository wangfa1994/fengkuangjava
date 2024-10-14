package unit16_thread.p16_8;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

// 继承 RecursiveAction 来实现可分解的任务
class PrintTask extends RecursiveAction{
    // 每个小任务最多打印50个数
    private static final int THRESHOLD = 50;
    private int start;
    private int end;
    public PrintTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if(end - start < THRESHOLD){
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName()
                + "的 i 值：" + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            // 并行执行两个 小任务
            left.fork();
            right.fork();
        }
    }
}
public class ForkJoinPooITest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(0, 300));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
