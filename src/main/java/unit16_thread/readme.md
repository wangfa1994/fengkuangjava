## 线程概述
进程：当一个程序进入内存运行时，即变成一个进程。是系统进行资源分配和调度的一个独立单位
线程：是进程的执行单元；是进程的组成部分
操作系统可以同时执行多个任务，每个任务就是进程；进程可以同时执行多个任务，每个任务就是线程

## 线程的创建和启动
### 继承 Thread 类创建线程
### 实现 Runnable 接口创建线程
### Callable 和 Future 创建线程

## 线程的生命周期
new：新建
start()：就绪
就绪状态的线程获得 CPU 后，开始执行 run() 方法，则该线程处于运行状态
阻塞：sleep()、该线程调用阻塞式 IO 方法、试图获得一个同步监视器，但正被其他线程持有、等待 notify、suspend()
死亡：run()或call()方法执行完成、抛出未捕获的 Exception 或 Error、stop()
isAlive()

不可对一个已经死亡的线程调用 start() 方法

## 控制线程
### join 线程
一个线程调用另外一个线程的 join() 方法后，必须等待另外那个线程执行完成，才会再执行该线程
### 后台线程
Java 垃圾回收机制线程
所有前台线程均死亡，后台线程随之死亡
Thread.setDaemon(true) 必须在 start() 之前设置
Thread.isDaemon()
### 睡眠线程 sleep
Thread.sleep(1000)
线程转为阻塞状态
抛出 InterruptedException
### 让步线程 yield
Thread.yield()
线程转为就绪状态
如果此时系统没有与之优先级相同或者优先级更高的线程，该线程将继续执行
### 改变线程优先级
Thread.setPriority(1-10)
Thread.getPriority()
MAX_PRIORITY 10
MIN_PRIORITY 1
NORM_PRIORITY 5

## 线程同步
### 线程安全问题
两个线程同时取钱问题。可能造成余额为负数
### 同步代码块（同步监视器）
任何时候，只能有一个线程可以获得同步监视器的锁定，当同步代码块执行完成后，该线程会释放对该同步监视器的锁定
synchronized(obj)
{
}
### 同步方法
synchronized 修饰非 static 方法
不要对线程安全类的所有方法都进行同步，只需对那些改变竞争资源的方法进行同步
如果可变类有两种运行环境：单线程和多线程环境，则应该为该可变类提供两种版本，即线程安全和不安全
例如：StringBuilder(单线程)、StringBuffer(多线程)
### 释放同步监视器
同步方法或代码块执行结束、遇到 break\return 终止代码块、出现未处理的 Error\Exception、执行同步监视器的 wait() 方法
注意：调用 Thread.sleep()、Thread.yield()、Thread.suspend() 不会释放同步监视器
### 同步锁
Lock  ReentrantLock
ReadWriteLock ReentrantReadWriteLock
private final ReentrantLock lock = new ReentrantLock();
lock.lock()
lock.unlock()
### 死锁
两个或者两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象。
死锁条件：
互斥条件：一个资源每次只能被一个进程使用
请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放
循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系
避免死锁：
阻止循环等待条件。将系统中所有资源设置标志位、排序，规定所有的进程申请资源必须以一定的顺序做操作避免死锁。

## 线程通信
### 传统线程通信
Object wait()、notify()、notifyAll()
wait 后，直到其他线程调用该同步监视器的 notify 或 notifyAll 方法来唤醒该线程
### 使用 Condition 控制线程通信
private final Lock lock = new ReentrantLock()
private final Condition cond = lock.newCondition()
lock.lock()
cond.await()
cond.signalAll()
lock.unlock()
### 使用阻塞队列（BlockingQueue）控制线程通讯
BlockingQueue 
程序的两个线程通过交替向 BlockingQueue 中放入元素、取出元素，即可以很好的控制线程的通信。
抛出异常 返回 false 阻塞
add(e) offer(e) put(e)
remove() poll() take()
element() peek() 

## 线程组和未处理异常
一旦某个线程加入某个线程组后，就一直属于该线程组，直到线程死亡。
默认情况下，子线程的现在组和父线程是一个
ThreadGroup
Thread(ThreadGroup, Runnable)
Thread(ThreadGroup, Runnable, name)
Thread(ThreadGroup, name)
Thread.getThreadGroup()

ThreadGroup(String name)
ThreadGroup(ThreadGroup, name)
ThreadGroup.getName()、activeCount()、interrupt()、isDaemon()、setDaemon(true)、setMaxPriority(int)、uncaughtException(Thread t, Throwable e),与 catch 不同，catch 不会将异常传播给上级调用者

## 线程池
系统启动一个新线程的成本是比较高的，因为它涉及与操作系统的交互
与数据库连接池类似，线程池是在系统启动时即创建大量空闲线程，程序将一个 Runnable 对象或 Callable 对象传给线程池，线程池就会启动一个线程来执行他们的 run() 或 call() 方法，当方法执行结束后，线程并不会死亡，而是再次返回线程池中成为空闲状态。
线程池的最大线程数参数可以控制系统中并发线程数不超过此数

### Java 8 改进的线程池
ExecutorService pool = Executors.newFixedThreadPool(6);
pool.submit(Runnable target);
pool.shutdown;
### Java 8 增强的 ForkJoinPool
ForkJoinPool pool = new ForkJoinPool();
pool.submit();
pool.shutdown();
Thread.fork(); // 将大任务分解为小任务
Thread.join();

## 线程相关类
### ThreadLocal 类
代表一个线程局部变量，通过把数据放在 ThreadLocal 中就可以让线程创建一个该变量的副本，从而避免并发访问线程安全问题。
get()
remove()
set()
ThreadLocal 和其他所有同步机制一样，都是为了解决多线程中对同一变量的访问冲突。
通常如果多个线程之间需要共享资源，以达到线程之间的通信功能，就使用同步机制；如果仅仅只要隔离多个线程之间的共享冲突，则可使用 ThreadLocal
### 包装线程不安全的集合
HashMap m = Collections.synchronizedMap(new HashMap())
### 线程安全的集合类

一个加了主键的表，并不能被称之为「表」。一个没加主键的表，它的数据无序的放置在磁盘存储器上，一行一行的排列的很整齐， 跟我认知中的「表」很接近。如果给表上了主键，那么表在磁盘上的存储结构就由整齐排列的结构转变成了树状结构，也就是上面说的「平衡树」结构，换句话说，就是整个表就变成了一个索引
主键的作用就是把「表」的数据格式转换成「索引（平衡树）」的格式放置。

索引能让数据库查询数据的速度上升， 而使写入数据的速度下降，原因很简单的， 因为平衡树这个结构必须一直维持在一个正确的状态， 增删改数据都会改变平衡树各节点中的索引数据内容，破坏树结构， 因此，在每次数据改变时， DBMS必须去重新梳理树（索引）的结构以确保它的正确，这会带来不小的性能开销

