package unit08_collection.c83;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class LinkedHashSetTest {
    public static void main(String[] args) {
        HashSet hs = new LinkedHashSet();
        hs.add("test");
        hs.add("疯狂 Java 讲义");
        System.out.println(hs);
        hs.remove("test");
        hs.add("test");
        // false, set 集合不允许重复
        System.out.println(hs.add("test"));
        System.out.println(hs);
    }
}
