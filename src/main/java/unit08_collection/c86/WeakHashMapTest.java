package unit08_collection.c86;

import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap whm = new WeakHashMap();
        // key 为 匿名字符串对象（没有其他引用）
        whm.put(new String("语文"), new String("良好"));
        whm.put(new String("数学"), new String("及格"));
        whm.put(new String("英文"), new String("中等"));
        // key 为系统缓存的字符串对象，系统会自动保持强引用
        whm.put("java", new String("中等"));
        System.out.println(whm);
        System.gc();
        System.runFinalization();
        System.out.println(whm);
    }
}
