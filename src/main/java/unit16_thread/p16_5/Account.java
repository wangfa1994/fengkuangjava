package unit16_thread.p16_5;

import java.util.concurrent.locks.ReentrantLock;

public class Account 
{
	// 定义锁对象
	private final ReentrantLock lock = new ReentrantLock();
	private String accountNo;
	private double balance;
	public Account() {};
	public Account(String accountNo, double balance)
	{
		this.accountNo = accountNo;
		this.balance = balance;
	}
	// 因为账户余额不允许随便修改，所以只为balance 提供 getter 方法
	public double getBalance() 
	{
		return balance;
	}

	public synchronized void draw(double drawAmount)
	{
		if(balance >= drawAmount)
		{
			System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
			try 
			{
				Thread.sleep(1); // 睡眠，相当于强制让线程切换
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			// 修改余额
			balance -= drawAmount;
			System.out.println("\t余额为：" + balance);
		}
		else 
		{
			System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
		}
	}
	public void draw2(double drawAmount) 
	{
		// 加锁
		lock.lock();
		try 
		{
			if(balance >= drawAmount)
			{
				System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
				try 
				{
					Thread.sleep(1); // 睡眠，相当于强制让线程切换
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				// 修改余额
				balance -= drawAmount;
				System.out.println("\t余额为：" + balance);
			}
			else 
			{
				System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
			}
		} 
		finally 
		{
			// 修改完成，释放锁
			lock.unlock();
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

	public String getAccountNo()
	{
		return accountNo;
	}
	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
}
