package unit16_thread.juc_1a2b3c;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author alvin
 * @date 2020-04-05 15:23
 */
public class T02_BlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue bq1 = new ArrayBlockingQueue(1);
        ArrayBlockingQueue bq2 = new ArrayBlockingQueue(1);

        new Thread(()->{
            for (int i = 1; i <= 26; i++) {
                try {
                    System.out.print(i);
                    bq2.put("0k");
                    bq1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(()->{
            for (char i = 'A'; i <= 'Z'; i++) {
                try {
                    // 这里注意是有顺序的。bq2有值时先打印，再给bq1塞值，否则可能还没打印，另一个线程就跑了
                    bq2.take();
                    System.out.print(i);
                    bq1.put("0k");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
