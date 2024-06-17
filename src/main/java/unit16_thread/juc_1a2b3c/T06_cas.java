package unit16_thread.juc_1a2b3c;

/**
 * @author alvin
 * @date 2020-04-05 16:54
 */
public class T06_cas {
    enum T{T1,T2}
    // 注意必须要 volatile
    static volatile T t = T.T1;
    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 1; i <= 26; i++) {
                // 写个循环，不打印不罢休
                while (t != T.T1){}
                System.out.print(i);
                t = T.T2;
            }
        }).start();

        new Thread(()->{
            for (char i = 'A'; i <= 'Z'; i++) {
                while (t != T.T2){}
                System.out.print(i);
                t = T.T1;
            }
        }).start();
    }
}
