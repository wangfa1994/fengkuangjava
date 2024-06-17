package unit16_thread.p16_6.p16_6_1;

public class DrawTest
{
	public static void main(String[] args) 
	{
		Account account = new Account("1234567", 0);
		new DrawThread("取钱者1", account, 800).start();
		new DrawThread("取钱者2", account, 800).start();
		new DepositThread("存钱者甲", account, 800).start();
		//new DepositThread("存钱者2", account, 800).start();
		//new DepositThread("存钱者3", account, 800).start();
	}
}
