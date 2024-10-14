package unit16_thread.juc_1a2b3c;

/**
 * @author alvin
 * @date 2020-04-05 15:50
 */
public class T03_sync_wait_notify {
    // 保证打印顺序，也可以用 CountDownLatch 实现
    private static volatile boolean t1Start = false;
    public static void main(String[] args) {
        Object o = new Object();
//        CountDownLatch ct = new CountDownLatch(1);
        new Thread(()->{
            // 注意锁要加载for循环外面，否则程序结束不了了
            synchronized (o){
//                ct.countDown();
                t1Start = true;
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    try {
                        // 先唤醒别人，然后阻塞自己
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();

        new Thread(()->{
//            try {
//                ct.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (o){
                if(!t1Start) {
                    try {
                        o.wait(); // 保证 t1 先打印
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char i = 'A'; i <= 'Z'; i++) {
                    System.out.print(i);
                    try {
                        // 先唤醒别人，然后阻塞自己
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
    }
}
