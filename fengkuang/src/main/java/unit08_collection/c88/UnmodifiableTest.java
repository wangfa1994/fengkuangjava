package unit08_collection.c88;

import java.util.*;

public class UnmodifiableTest {
    public static void main(String[] args) {
        List<Object> l = Collections.emptyList();
        Set<String> s = Collections.singleton("test1");
        HashMap scores = new HashMap();
        scores.put("语文", 80);
        scores.put("java", 82);
        Map map = Collections.unmodifiableMap(scores);
        // 下面都会抛出异常 UnsupportedOperationException
        l.add("test");
        s.add("test");
        map.put("test", 10);
    }
}
