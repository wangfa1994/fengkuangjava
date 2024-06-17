package unit16_thread.p16_5;

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
	// 当多个线程修改同一个共享数据时，将涉及数据安全问题
	public void run()
	{
		account.draw(drawAmount);
		// account.draw2(drawAmount);
	}
}
