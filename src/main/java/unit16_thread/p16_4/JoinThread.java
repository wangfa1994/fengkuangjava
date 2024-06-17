package unit16_thread.p16_4;

public class JoinThread extends Thread
{
	public JoinThread(String name) 
	{
		super(name);
	}
	// 重写 run 方法，定义线程执行
	public void run() 
	{
		for (int i = 0; i < 100; i++) 
		{
			System.out.println(getName() + " " + i);
		}
	}
	public static void main(String[] args) throws InterruptedException 
	{
		// 启动子线程
		new JoinThread("新线程").start();;
		for (int i = 0 ; i < 100 ; i++ )
		{
			if (i == 20)
			{
				JoinThread jt = new JoinThread( "被Join 的线程" ) ;
				jt.start();
				// main 线程调用了jt 线程的 join()方法
				// 所以 main 线程必须等 jt 执行结束才会向下执行
				jt.join();
			}
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
}
