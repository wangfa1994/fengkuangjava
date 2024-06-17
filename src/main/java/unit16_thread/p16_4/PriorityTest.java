package unit16_thread.p16_4;

public class PriorityTest extends Thread
{
	public PriorityTest(String name) 
	{
		super(name);
	}
	public void run() 
	{
		for (int i = 0; i < 50; i++) 
		{
			System.out.println(getName() + ",其优先级是：" + getPriority() 
					+ ",循环变量：" + i);
		}
	}
	public static void main(String[] args) 
	{
		// 改变主线程优先级
		Thread.currentThread().setPriority(6);
		for (int i = 0; i < 30; i++) 
		{
			if(i == 10)
			{
				PriorityTest low = new PriorityTest("低级");
				System.out.println("创建之初的优先级：" + low.getPriority());
				low.setPriority(Thread.MIN_PRIORITY);
				low.start();
			}
			if(i == 20)
			{
				PriorityTest high = new PriorityTest("高级");
				System.out.println("创建之初的优先级：" + high.getPriority());
				high.setPriority(Thread.MAX_PRIORITY);
				high.start();
			}
		}
	}
}
