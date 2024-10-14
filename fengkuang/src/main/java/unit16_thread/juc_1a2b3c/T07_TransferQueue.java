package unit16_thread.juc_1a2b3c;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author alvin
 * @date 2020-04-05 17:09
 */
public class T07_TransferQueue {
    public static void main(String[] args) {
        LinkedTransferQueue<Object> q = new LinkedTransferQueue<>();
        new Thread(()->{
            for (int i = 1; i <= 26; i++) {
                try {
                    q.transfer(i);
                    System.out.print(q.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (char i = 'A'; i <= 'Z'; i++) {
                try {
                    System.out.print(q.take());
                    q.transfer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
