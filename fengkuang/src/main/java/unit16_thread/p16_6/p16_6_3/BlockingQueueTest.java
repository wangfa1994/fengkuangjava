package unit16_thread.p16_6.p16_6_3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class BlockingQueueTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
		bq.put("Java");
		bq.put("Java");
		bq.put("Java"); // 阻塞线程
	}

}
