package unit08_collection.c86;

import java.util.IdentityHashMap;

public class IdentityHashMapTest {
    public static void main(String[] args) {
        IdentityHashMap ihm = new IdentityHashMap();
        ihm.put(new String("语文"), 89);
        ihm.put(new String("语文"), 89);
        ihm.put("语文", 89);
        ihm.put("java", 93);
        ihm.put("java", 93);
        // 4个
        System.out.println(ihm);
    }
}
