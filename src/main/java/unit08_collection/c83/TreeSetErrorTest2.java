package unit08_collection.c83;

import java.util.Date;
import java.util.TreeSet;

public class TreeSetErrorTest2 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add(new String("test"));
        // 报错，TreeSet 元素要求为同一个类的实例
        ts.add(new Date());
    }
}
