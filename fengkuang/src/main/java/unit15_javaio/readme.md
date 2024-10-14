# Java I/O 流
Java 的 IO 通过 java.io 包下的类和接口来支持，主要包括输入、输出两种 IO 流，每种输入输出流又分为字节流和字符流两大类。
Java 的 IO 流使用了装饰器设计模式，它将 IO 流分成底层节点流和上层处理流，其中节点流用于和底层的物理存储节点直接关联。不同的物理节点获取节点流的方式可能存在一定差异，但程序可以把不同的物理包装成统一的处理流，从而允许程序使用统一的代码来读取不同的物理存储节点的资源。

## File 类
File 能新建、删除、重命名文件和目录，但是 File 不能访问文件内容本身。

## 理解 Java 的 IO 流
### 流的分类
输入输出是按照程序运行所在内存的角度来划分的
- 输入流（InputStream、Reader）：只能从中读取数据
- 输出流（OutputStream、Writer）：只能从中写入数据

- 字节流：操作的数据单元是 8 位的字节
- 字符流：操作的数据单元是 16 位的字符

- 节点流：可以从/向一个特定的 IO 设备（磁盘、网络）读/写数据的流
- 处理流：用于对一个已经存在的流进行连接过封装。也称包装流。装饰器设计模式

### 流的概念模型
输入流可以想象成一个装满水的水管。使用隐式的记录指针来表示当前正准备从哪个水滴开始读取。取出水滴后，指针向后移动。
输出流可以想象成一个空的水管。装入一个水滴后，指针向后移动。
处理流的模型图

## 字节流和字符流
- InputStream、Reader：三个 read() 方法
- OutputStream、Writer：三个 write() 方法。write(String str)、write(String str, int off, int len)
与 JDBC 编程一样，程序里打开的文件 IO  资源不属于内存里的资源，垃圾回收机制无法回收该资源，所以应该显示关闭文件 IO 资源。

## 输入/输出流体系
### 处理流的用法
将节点流作为参数传入即可
PrintStream 类的输出功能非常强大，通常如果需要输出文本内容，都应该将输出流包装为 PrintStream 后进行输出

### 输入/输出流体系
如果进行输入/输出的内容是文本内容，则应该考虑使用字符流；如果进行输入/输出的内容是二进制内容，则应该考虑使用字节流

### 转换流（字节流转换为字符流）
InputStreamReader\OutputStreamWriter

### 推回输入流（推回缓冲区实现）
PushbackInputStream\PushbackReader

## 重定向标准输入/输出
System.setErr()
System.setIn()
System.setOut()

## Java 虚拟机读写其他进程的数据
Runtime.getRuntime.exec("javac")

## RandomAccessFile
RandomAccessFile 可以访问文件的任意位置，如果只需要访问文件部分内容，而不是把文件从头读到尾，使用 RandomAccessFile 更好
getFilePointer()：返回文件记录指针的当前位置
seek(long pos)：将文件记录指针定位到 pos 位置
RandomAccessFile 既可以读文件也可写文件

## 对象序列化
### 序列化的含义和意义
允许 Java 对象转换成字节序列，这些字节序列可以保存在磁盘上，或者通过网络传输，以备后来重新恢复成原来的对象。系列化机制使得对象可以脱离程序的运行而独立存在。

### 使用对象流实现序列化
ObjectOutputStream、ObjectInputStream
当一个可序列化类有多个父类时，这些父类要么有无参数的构造器，要么也是可序列化的，否则反序列化会抛出 InvalidClassException 异常。

### 对象引用的序列化
先序列化引用对象，再序列化自己
序列化算法：
所有保存到磁盘中的对象都有一个序列化编号。
当程序试图序列化一个对象时，会先检查该对象是否已经被序列化过，只有未被序列化过，才会被序列化
如果某个对象已经被序列化，程序将直接输出一个序列化编号，而不是重新序列化。
问题：当对象可变时。重新序列化好像不起效果。？？？

### 自定义序列化
private transient int age; 表示 age 实例变量在序列化时忽略
writeObject(ObjectOutputStream out)
readObject(ObjectInputStream in)
writeReplace()

### 另一种自定义序列化机制
Externalizable：自定义需要序列化的信息；提供两个空方法，需要自己实现；性能更好
Serializable：系统自动存储必要信息；易于实现，只需实现接口即可；性能略差
readExternal(ObjectInput in)
writeExternal(ObjectOutput out)

对象的类名、实例变量都会被序列化；方法、类变量（static 修饰）、transient 变量（瞬态实例变量）都不会被序列化

### 版本
反序列化是，必须提供该对象的 class 文件，但是，随着项目的升级，系统的 class 文件也会升级。
private static final serialVersionUID 的值保持不变，序列化机制也会把他们当成同一个序列化版本。

## NIO
新 IO 采用内存映射文件的方式来处理输入/输出，新 IO 将文件或文件的一段区域映射到内存中，这样就可以像访问内存一样访问文件了。

### 使用 Buffer
capacity：容量
limit：界限
position：位置
mark：标记
flip()：为从 Buffer 取出数据做准备
clear()：为从 Buffer 装入数据做准备
put()
get()

### 使用 Channel
与传统的流类似，但有两个主要区别：
Channel 可以直接将指定文件的部分或全部直接映射成 Buffer
程序不能直接访问 Channel 中的数据，必须借助于 Buffer
通过流的 getChannel 方法获取 Channel
map()
read()
write()
jujm 

### 文件锁
FileChannel lock()、tryLock()
共享锁：允许多个进程来读取该文件，但阻止其他进程获得对该文件的排他锁
排他锁：锁住对该文件的读写
FileLock isShared()、release()


