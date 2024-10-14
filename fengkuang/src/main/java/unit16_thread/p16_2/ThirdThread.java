package unit16_thread.p16_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThirdThread 
{
	public static void main(String[] args) 
	{
		FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>)() -> {
			int i = 0;
			for (; i < 100; i++)
			{
				System.out.println(Thread.currentThread().getName() + " 循环变量i的值：" + i);
			}
			// Thread.sleep(2000);
			return i;
		});
		// task.cancel(true);
		for (int i = 0; i < 100; i++)
		{
			System.out.println(Thread.currentThread().getName() + " 的循环变量 i 的值：" + i);
			if (i == 20)
			{
				System.out.println(task.isCancelled()); // false
				System.out.println(task.isDone());		// false
				new Thread(task, "有返回值的线程1").start();
			}
		}
		try
		{
			System.out.println(task.isCancelled()); // false
			System.out.println(task.isDone());		// false
			System.out.println("子线程的返回值：" + task.get(1000, TimeUnit.MILLISECONDS));
			System.out.println(task.isCancelled()); // false
			System.out.println(task.isDone());		// true
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
