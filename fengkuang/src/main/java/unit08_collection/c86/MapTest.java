package unit08_collection.c86;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("test1", 1);
        map.put("test2", 2);
        map.put("test3", 3);
        map.put("test4", 4);

        // 插入新值，返回原来的值
        System.out.println(map.put("test2", 22));
        System.out.println(map);
        System.out.println(map.containsKey("test3"));
        System.out.println(map.containsValue(22));
        // 遍历
        for(Object e : map.entrySet()){
            Map.Entry entry = (Map.Entry)e;
            System.out.println(entry.getKey() + " == " + entry.getValue());
        }
        // 删除 test2
        System.out.println(map.remove("test2"));
        System.out.println(map);
    }
}
