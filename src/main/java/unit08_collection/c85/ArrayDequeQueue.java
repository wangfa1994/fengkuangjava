package unit08_collection.c85;

import java.util.ArrayDeque;

public class ArrayDequeQueue {
    public static void main(String[] args) {
        ArrayDeque queue = new ArrayDeque();
        // 入队
        queue.offer("test1");
        queue.offer("test2");
        queue.offer("test3");
        // test1 test2 test3
        System.out.println(queue);
        // 访问第一个元素 test1
        System.out.println(queue.peek());
        // 出队 test1
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
