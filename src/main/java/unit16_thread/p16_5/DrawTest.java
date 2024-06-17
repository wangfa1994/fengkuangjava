package unit16_thread.p16_5;

public class DrawTest
{
	public static void main(String[] args) 
	{
		Account account = new Account("1234567", 1000);
		// 模拟两个线程对同一个账户取钱
		new DrawThread("甲", account, 800).start();;
		new DrawThread("乙", account, 800).start();;
	}
}