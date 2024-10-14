package unit08_collection.c84;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 8 新增的排序和替换
 */
public class ListTest3 {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add("1");
        books.add("111");
        books.add("11");
        books.add("11111");

        // Comparator
        books.sort((o1,o2) ->((String)o1).length() - ((String)o2).length());
        System.out.println(books);
        // UnaryOperator
        books.replaceAll(ele -> ((String)ele).length());
        System.out.println(books);

    }
}
