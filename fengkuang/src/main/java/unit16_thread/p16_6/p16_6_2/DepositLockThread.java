package unit16_thread.p16_6.p16_6_2;

public class DepositLockThread extends Thread
{
	private AccountLock account;
	private double depositAmount;
	public DepositLockThread(String name, AccountLock account, double depositAmount) 
	{
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	// 重复100次取钱操作
	@Override
	public void run() 
	{
		for (int i = 0; i < 100; i++) 
		{
			System.out.println("第 " + i + "次存钱。");
			account.deposit(depositAmount, i);
		}
	}
}
