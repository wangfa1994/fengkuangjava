package unit18_classload.c5.aop;

/**
 * @author alvin
 * @date 2020-05-04 23:36
 */
public class GunDog implements Dog {
    @Override
    public void info() {
        System.out.println("我是一只猎狗");
    }
    @Override
    public void run() {
        System.out.println("我奔跑迅速");
    }
}
