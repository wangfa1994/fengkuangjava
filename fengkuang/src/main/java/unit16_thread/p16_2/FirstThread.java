package unit16_thread.p16_2;

public class FirstThread extends Thread
{
	private int i;
	public void run() // 重写 run() 方法。方法体就是线程执行体
	{
		for ( ; i < 100; i++)
		{
			System.out.println("当前线程：" + getName() + " " + i);
		}
	}
	public static void main(String[] args) 
	{
		for (int i = 0; i < 100; i++)
		{
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 20)
			{
				FirstThread thread1 = new FirstThread(); // 创建并启动第一个线程
				thread1.setName("自定义Thread1");
				thread1.start();
				new FirstThread().start(); // 创建并启动第二个线程
			}
		}
	}
}
