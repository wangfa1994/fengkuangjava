package unit16_thread.p16_4;

public class DaemonThread extends Thread
{
	public void run() 
	{
		for (int i = 0; i < 1000; i++) 
		{
			System.out.println(getName() + " " + i);
		}
	}
	public static void main(String[] args) 
	{
		DaemonThread t = new DaemonThread();
		System.out.println(t.isDaemon());
		// 设置位后台线程，必须在 start 之前设置，否则会报错
		t.setDaemon(true);
		System.out.println(t.isDaemon());
		t.start();
		for (int i = 0; i < 10; i++) 
		{
			System.out.println(Thread.currentThread().getName() +" "+ i) ;
		}
		// 一一程序执行到此处，前台线程(main 线程)结束一一
		// 后台线程也应该随之结束
	}
}
