package unit08_collection.c86;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap scores = new LinkedHashMap();
        // HashMap scores = new HashMap();
        scores.put("语文", 80);
        scores.put("英文", 82);
        scores.put("数学", 76);

        scores.forEach((key, value) -> System.out.println(key+"-->"+value));
    }
}
