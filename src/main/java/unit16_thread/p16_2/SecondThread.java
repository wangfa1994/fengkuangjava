package unit16_thread.p16_2;

public class SecondThread implements Runnable
{
	private int i;
	// 重写 run() 方法。方法体就是线程执行体
	public void run()
	{
		for ( ; i < 100; i++)
		{
			System.out.println(Thread.currentThread().getName() + " ---- " + i);
		}
	}
	public static void main(String[] args) 
	{
		for (int i = 0; i < 100; i++)
		{
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 20)
			{
				// 创建并启动第一个线程
				SecondThread thread = new SecondThread();
				new Thread(thread, "新线程 1").start();
				new Thread(thread, "新线程 2").start();
			}
		}
	}
}
