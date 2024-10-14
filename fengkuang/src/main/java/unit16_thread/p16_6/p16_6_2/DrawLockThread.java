package unit16_thread.p16_6.p16_6_2;

public class DrawLockThread extends Thread
{
	private AccountLock account;
	private double drawAmount;
	public DrawLockThread(String name, AccountLock account, double drawAmount) 
	{
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	// 重复100次取钱操作
	@Override
	public void run() 
	{
		for (int i = 0; i < 100; i++) 
		{
			System.out.println("第 " + i + "次取钱。");
			account.draw(drawAmount,i);
		}
	}
}
