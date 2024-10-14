package unit16_thread.p16_6.p16_6_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLock 
{
	// 显示定义 Lock 对象
	private final Lock lock = new ReentrantLock();
	// 获得指定 Lock 对象对应的 Condition
	private final Condition cond = lock.newCondition();
	private String accountNo;
	private double balance;
	// 标识账户是否已经有存款
	private boolean flag = false;
	public AccountLock() {};
	public AccountLock(String accountNo, double balance)
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
	public void draw(double drawAmount, int i) 
	{
		lock.lock(); // 加锁
		try 
		{
			if(!flag) // 没有存款，阻塞取钱方法
			{
				cond.await();
			}
			else 
			{
				// 执行取钱操作
				System.out.println(Thread.currentThread().getName() + " 取钱("+i+")：" + drawAmount);
				balance -= drawAmount;
				System.out.println("账户余额为：" + balance);
				// 标识为没有存款
				flag = false;
				// 唤醒其他线程
				cond.signalAll();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			lock.unlock(); // 释放锁
		}
	}
	
	public void deposit(double depositAmount, int i) 
	{
		lock.lock();
		try 
		{
			if(flag) // 已有存款，阻塞存钱方法
			{
				cond.await();
			}
			else 
			{
				// 执行取钱操作
				System.out.println(Thread.currentThread().getName() + " 存钱("+i+")：" + depositAmount);
				balance += depositAmount;
				System.out.println("账户余额为：" + balance);
				flag = true;
				cond.signalAll();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
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
		if (obj !=null && obj.getClass() == AccountLock.class)
		{
			AccountLock target = (AccountLock)obj ;
			return target.getAccountNo( ).equals (accountNo);
		}
		return false;
	}
}
