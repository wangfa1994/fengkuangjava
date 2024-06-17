package unit08_collection.c84;

import java.util.ArrayList;
import java.util.List;

class A {
    public boolean equals(Object o){
        return true;
    }
}

/**
 * List 根据 equals 判断相等
 */
public class ListTest2 {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add("test1");
        books.add("test2");
        books.add("test3");
        System.out.println(books);
        // 删除集合中的 A 对象，导致第一个元素被删除
        books.remove(new A());
        // 再次删除集合中的第一个元素
        books.remove(new A());
        System.out.println(books);
    }
}
