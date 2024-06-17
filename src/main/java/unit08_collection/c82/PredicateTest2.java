package unit08_collection.c82;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

public class PredicateTest2 {
    public static void main(String[] args) {
        Collection books = new HashSet();
        books.add("疯狂 Java 讲义");
        books.add("Spring 实战");
        books.add("轻量级 Java EE 企业应用实战");
        books.add("1");
        books.add("22");

        // 包含 Java 的数量
        callAll(books, ele -> ((String)ele).contains("Java"));
        // 长度大于10的数量
        callAll(books, ele -> ((String)ele).length() > 10);
    }

    static int callAll(Collection books, Predicate predicate){
        int count = 0;
        for (Object obj : books){
            if(predicate.test(obj)){
                count++;
            }
        }
        System.out.println(count);
        return count;
    }
}
