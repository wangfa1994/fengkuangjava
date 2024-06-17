package unit08_collection.c82;

import java.util.Collection;
import java.util.HashSet;

public class PredicateTest {
    public static void main(String[] args) {
        Collection books = new HashSet();
        books.add("疯狂 Java 讲义");
        books.add("Spring 实战");
        books.add("轻量级 Java EE 企业应用实战");
        books.add("1");
        books.add("22");

        // 删除长度小于 10 的元素
        books.removeIf(ele -> ((String)ele).length() < 10);
        System.out.println(books);
    }
}
