package unit08_collection.c88;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Java9Collection {
    public static void main(String[] args) {
        // 创建包含 4 个元素的 set 集合
        Set<String> set = Set.of("Java", "Kotlin", "C", "C++");
        System.out.println(set);
        // 报错
        // set.add("test");

        List<Integer> l = List.of(1, 2, 3, 4);
        System.out.println(l);
        // l.remove(1);

        Map<String, Integer> m = Map.of("test1", 1, "test2", 2);
        System.out.println(m);

        Map<String, Integer> m2 = Map.ofEntries(Map.entry("t1", 1),
                Map.entry("t2", 2));
        System.out.println(m2);
    }
}
