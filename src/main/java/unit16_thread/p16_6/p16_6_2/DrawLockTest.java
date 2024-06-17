package unit16_thread.p16_6.p16_6_2;

public class DrawLockTest 
{
	public static void main(String[] args) 
	{
		AccountLock account = new AccountLock("1234567", 0);
		new DrawLockThread("取钱者1", account, 800).start();
		// new DrawThread("取钱者2", account, 800).start();
		new DepositLockThread("存钱者甲", account, 800).start();
		//new DepositThread("存钱者2", account, 800).start();
		//new DepositThread("存钱者3", account, 800).start();
	}
}
