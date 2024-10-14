package unit20_stream.c1_lambda;

public class L1_ThreadDemo {
    public static void main(String[] args) {

        Runnable target = new Runnable() {
            @Override
            public void run() {
                System.out.println("OK");
            }
        };
        new Thread(target).start();

        // jdk8 lambda
        /**
         * 返回的就是 Runnable 接口的一个实例。
         * 该实例的实现为 System.out.println("OK")
         */
        Runnable target1 = () -> System.out.println("OK");
        // lambda必须返回指定接口实例；并且接口中只有一个需要实现的方法
        // Object target2 = () -> System.out.println("OK");
        new Thread(target1).start();
    }
}
