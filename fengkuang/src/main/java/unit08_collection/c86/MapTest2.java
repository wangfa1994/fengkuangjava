package unit08_collection.c86;

import java.util.HashMap;
import java.util.Map;

public class MapTest2 {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("test1", 1);
        map.put("test2", 2);
        map.put("test3", 3);
        map.put("test4", 4);
        // test5 不存在，不做任何改变
        map.replace("test5", 5);
        System.out.println(map);
        // merge
        map.merge("test2", 22,
                (oldVal, param) -> (Integer)oldVal + (Integer)param);
        // 24
        System.out.println(map.get("test2"));
        // computeIfAbsent 5
        map.computeIfAbsent("test5",
                (key) -> ((String)key).length());
        System.out.println(map.get("test5"));
        map.computeIfPresent("test5",
                (key, value) -> (Integer)value * (Integer)value);
        System.out.println(map);
    }
}
