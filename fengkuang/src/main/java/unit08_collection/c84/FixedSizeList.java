package unit08_collection.c84;

import java.util.Arrays;
import java.util.List;

public class FixedSizeList {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("test1", "test2");
        // 实现类：Arrays$ArrayList
        System.out.println(list.getClass());
        list.forEach(System.out::println);
        // 试图增加、删除都会抛出 UnsupportedOperationException 异常
        list.add("test3");
        list.remove("test1");
    }
}
