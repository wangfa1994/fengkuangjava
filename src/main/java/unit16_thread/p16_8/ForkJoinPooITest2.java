package unit16_thread.p16_8;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// 继承 RecursiveTask 来实现可分解的任务
class CalTask extends RecursiveTask<Integer> {
    // 每个小任务最多累加 20 个数
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;
    public CalTask(int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        // 差小于 THRESHOLD 时，开始累加
        if(end - start < THRESHOLD){
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            // 并行执行两个 小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }
}
public class ForkJoinPooITest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[100];
        Random rand = new Random();
        int total = 0;
        for (int i = 0, len = arr.length; i < len; i++) {
            int tmp = rand.nextInt(20);
            total += (arr[i] = tmp);
        }
        System.out.println(total);
        // 1. 创建一个通用池
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // 2. 提交任务
        ForkJoinTask<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
        System.out.println(future.get());
        // 3. 关闭线程池
        pool.shutdown();
    }
}
