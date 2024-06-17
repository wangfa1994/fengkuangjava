package unit16_thread.p16_6.p16_6_1;

import unit16_thread.p16_6.p16_6_1.Account;

public class DrawThread extends Thread
{
	private Account account;
	private double drawAmount;
	public DrawThread(String name, Account account, double drawAmount) 
	{
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	// 重复100次取钱操作
	@Override
	public void run() 
	{
		for (int i = 0; i < 50; i++)
		{
			//System.out.println("第 " + i + "次取钱。");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			account.draw(drawAmount,i);
		}
	}
}
