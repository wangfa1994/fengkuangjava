package unit08_collection.c85;

import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.offer(6);
        pq.offer(-3);
        pq.offer(20);
        pq.offer(18);
        // PriorityQueue 不可有 null
        // pq.offer(null);
        // 输出不是按照加入顺序，而是按照大小
        // 显示没有准确按照大小是因为 toString 的影响
        System.out.println(pq);
        // 获取并删除第一个元素
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.remove());
        System.out.println(pq.element());
        System.out.println(pq);
    }
}
