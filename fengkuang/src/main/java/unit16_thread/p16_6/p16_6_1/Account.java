package unit16_thread.p16_6.p16_6_1;

public class Account 
{
	private String accountNo;
	private double balance;
	// 标识账户是否已经有存款
	private volatile boolean flag = false;
	
	public Account() {};
	public Account(String accountNo, double balance)
	{
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() 
	{
		return accountNo;
	}
	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}
	// 因为账户余额不允许随便修改，所以只为balance 提供getter 方法
	public double getBalance() 
	{
		return balance;
	}
	public synchronized void draw(double drawAmount, int i) 
	{
		try 
		{
			if(!flag) // 没有存款，阻塞取钱方法
			{
				wait();
			}
//			else
//			{
				// 执行取钱操作
				System.out.println(Thread.currentThread().getName() + " 取钱("+i+")：" + drawAmount);
				balance -= drawAmount;
				System.out.println("账户余额为：" + balance);
				// 标识为没有存款
				flag = false;
				// 唤醒其他线程
				notifyAll();
//			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void deposit(double depositAmount, int i) 
	{
		try 
		{
			if(flag) // 已有存款，阻塞存钱方法
			{
				wait();
			}
//			else
//			{
				// 执行取钱操作
				System.out.println(Thread.currentThread().getName() + " 存钱("+i+")：" + depositAmount);
				balance += depositAmount;
				System.out.println("账户余额为：" + balance);
				flag = true;
				notifyAll();
//			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int hashCode() 
	{
		return accountNo.hashCode();
	}
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true ;
		}
		if (obj !=null && obj.getClass() == Account.class)
		{
			Account target = (Account)obj ;
			return target.getAccountNo( ).equals (accountNo);
		}
		return false;
	}
}
