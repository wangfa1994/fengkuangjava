package unit08_collection.c82;

import java.util.Collection;
import java.util.HashSet;

public class Co11ectionStream {
    public static void main(String[] args) {
        Collection books = new HashSet();
        books.add("疯狂 Java 讲义");
        books.add("Spring 实战");
        books.add("轻量级 Java EE 企业应用实战");
        books.add("1");
        books.add("22");

        // 包含 Java 的个数
        System.out.println(books.stream()
                .filter(ele -> ((String)ele).contains("Java"))
                .count());
        // 长度大于 10 的个数
        System.out.println(books.stream()
                .filter(ele -> ((String)ele).length() > 10)
                .count());
        books.stream().mapToInt(ele -> ((String)ele).length())
                .forEach(System.out::println);
    }
}
