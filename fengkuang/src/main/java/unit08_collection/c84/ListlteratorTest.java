package unit08_collection.c84;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListlteratorTest {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add("test1");
        books.add("test2");
        books.add("test3");
        books.add("test4");

        ListIterator lit = books.listIterator();
        while (lit.hasNext()){
            System.out.println(lit.next());
            // iterator 只有remove
            lit.add("---------aaa----------");
        }
        System.out.println("==============");
        while (lit.hasPrevious()){
            System.out.println(lit.previous());
        }
    }
}
