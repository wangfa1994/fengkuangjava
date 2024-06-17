package unit08_collection.c82;

import java.util.Collection;
import java.util.HashSet;

public class CollectionEach {
    public static void main(String[] args) {
        Collection books = new HashSet();
        books.add("疯狂 Java 讲义");
        books.add("Spring 实战");
        books.add("轻量级 Java EE 企业应用实战");

        // TODO: 理解函数式编程
        books.forEach(obj -> System.out.println(obj));
    }
}
