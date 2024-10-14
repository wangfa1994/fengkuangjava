package unit16_thread.p16_9;

/**
 * 线程局部变量
 */
class Account{
    // 定义 ThreadLocal
    private ThreadLocal<String> name = new ThreadLocal<>();
    public Account(String str){
        this.name.set(str);
        // 访问当前线程的 name 副本的值
        System.out.println("---" + this.name.get());
    }
    public String getName(){
        return name.get();
    }
    public void setName(String str){
        this.name.set(str);
    }
}
class MyTest extends Thread {
    private Account account;
    public MyTest(Account account, String name){
        super(name);
        this.account = account;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            //if(i == 6){
                account.setName(getName());
            //}
            System.out.println(account.getName()
                    + " 账户的 i 值：" + i);
        }
    }
}
public class ThreadLocaITest {
    public static void main(String[] args) {
        // 启动两个线程，共享同一个 Account
        // 初始名 是主线程的副本
        Account at = new Account("初始名");
        // 6 之后，两个子线程也有 name 的副本了
        new MyTest(at, "线程甲").start();
        new MyTest(at, "线程乙").start();
    }
}
