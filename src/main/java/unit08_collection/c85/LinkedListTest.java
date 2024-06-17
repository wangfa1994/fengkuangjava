package unit08_collection.c85;

import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList books = new LinkedList();
        // 加入队列尾部
        books.offer("test1");
        // 加入栈顶部
        books.push("test2");
        // 加入队列头部（相当于栈顶部）
        books.offerFirst("test3");
        // 以 List 方式遍历集合 test3 test2 test1
        for (int i = 0; i < books.size(); i++) {
            System.out.println("遍历中：" + books.get(i));
        }
        // 访问栈顶元素 test3
        System.out.println(books.peekFirst());
        // 访问队列最后一个元素 test1
        System.out.println(books.peekLast());
        // 出栈 test3
        System.out.println(books.pop());
        // test2 test1
        System.out.println(books);
        // 删除最后一个 test1
        System.out.println(books.pollLast());
        // test2
        System.out.println(books);
    }
}
