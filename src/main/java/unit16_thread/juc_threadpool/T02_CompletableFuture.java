package unit16_thread.juc_threadpool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author alvin
 * @date 2020-04-05 19:39
 * 认识 CompletableFuture 管理多个返回结果（Future）
 */
public class T02_CompletableFuture {
    public static void main(String[] args) throws IOException {
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());
        CompletableFuture.allOf(futureTB, futureTM, futureJD).join();

        CompletableFuture.supplyAsync(()->priceOfJD())
                .thenApply(String::valueOf)
                .thenApply(str -> "price "+str)
                .thenAccept(System.out::println);

        System.in.read();
    }
    private static double priceOfTM() {
        delay();
        return 1.00;
    }
    private static double priceOfTB() {
        delay();
        return 2.00;
    }
    private static double priceOfJD() {
        delay();
        return 3.00;
    }
    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
