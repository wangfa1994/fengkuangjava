package unit16_thread.p16_7;

class MyExHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + " 线程出现了异常：" + e);
    }
}

public class ExHandler {
    public static void main(String[] args) {
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
        int a = 5/0;
        // 与 catch 不同，程序不会正常结束
        System.out.println("程序正常结束！");
    }
}
