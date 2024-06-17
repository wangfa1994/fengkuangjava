package unit16_thread.p16_6.p16_6_1;

import unit16_thread.p16_6.p16_6_1.Account;

public class DepositThread extends Thread
{
	private Account account;
	private double depositAmount;
	public DepositThread(String name, Account account, double depositAmount) 
	{
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	// 重复100次取钱操作
	@Override
	public void run() 
	{
		for (int i = 0; i < 101; i++)
		{
			// System.out.println("第 " + i + "次存钱。");
			account.deposit(depositAmount, i);
		}
	}
	
}
