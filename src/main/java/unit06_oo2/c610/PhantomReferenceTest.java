package unit06_oo2.c610;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {
    public static void main(String[] args) {
        String str = new String("疯狂 Java 讲义");
        // 创建一个引用队列
        ReferenceQueue rq = new ReferenceQueue();
        // 创建一个虚引用
        PhantomReference pr = new PhantomReference(str, rq);
        str = null;
        // 并不能通过虚引用获取被引用的对象，输出 null
        System.out.println(pr.get());
        System.gc();
        System.runFinalization();
        // 垃圾回收后，虚引用将被放入引用队列中
        System.out.println(rq.poll() == pr);
    }
}
