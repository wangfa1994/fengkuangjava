package unit08_collection.c82;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class IteratorTest {
    public static void main(String[] args) {
        Collection books = new HashSet();
        books.add("疯狂 Java 讲义");
        books.add("Spring 实战");
        books.add("轻量级 Java EE 企业应用实战");

        Iterator it = books.iterator();
        while (it.hasNext()){
            String book = (String)it.next();
            System.out.println(book);
            if(book.equalsIgnoreCase("疯狂 Java 讲义")){
                it.remove();
                // ConcurrentModificationException 异常
                // SDK12 不报错了
                books.remove(book);
            }
            // 不会改变集合元素本身
            // 可以看出：遍历时，不是把集合元素本身传递给迭代变量
            book = "测试。。。";
        }
        System.out.println(books);
        Iterator it2 = books.iterator();
        it2.forEachRemaining(obj -> System.out.println(obj));

        System.out.println("==========foreach==========");
        for (Object obj : books){
            // 此处 book 也不是集合元素本身
            String book = (String)obj;
            System.out.println(book);
            if(book.equals("Spring 实战")){
                // ConcurrentModificationException 异常
                books.remove(book);
            }
        }
        System.out.println(books);
    }
}
