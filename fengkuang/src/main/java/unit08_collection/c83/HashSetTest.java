package unit08_collection.c83;

import java.util.HashSet;

class A {
    // 没有重写 hashCode，导致可以加进去相同对象
    public boolean equals(Object obj) {
        return true;
    }
}
class B {
    // 没有重写 equals，导致需要在同一个位置存储多个对象，会采用链式结构，导致性能下降
    public int hashCode() {
        return 1;
    }
}
class C {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
    @Override
    public int hashCode() {
        return 2;
    }
}
public class HashSetTest {
    public static void main(String[] args) {
        HashSet books = new HashSet();
        // 加进去两
        books.add(new A());
        books.add(new A());
        // 加进去两
        books.add(new B());
        books.add(new B());
        // 只加进去一个
        books.add(new C());
        books.add(new C());
        // 可以存 null
        books.add(null);
        System.out.println(books);
    }
}
