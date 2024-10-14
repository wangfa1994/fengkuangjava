package unit08_collection.c85;

import java.util.ArrayDeque;

public class ArrayDequeStack {
    public static void main(String[] args) {
        ArrayDeque stack = new ArrayDeque(10);
        // 入栈
        stack.push("test1");
        stack.push("test2");
        stack.push("test3");
        System.out.println(stack);
        // 访问第一个元素 test3
        System.out.println(stack.peek());
        // 出栈 test3
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
