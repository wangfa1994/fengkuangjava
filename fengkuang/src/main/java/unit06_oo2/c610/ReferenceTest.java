package unit06_oo2.c610;

import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args) throws Exception{
        String str = new String("疯狂 Java 讲义");
        WeakReference wr = new WeakReference(str);
        str = null; // 消除强引用
        // 取出弱引用所引用的对象
        System.out.println(wr.get());
        // 强制垃圾回收
        System.gc();
        System.runFinalization();
        System.out.println(wr.get());
    }
}
