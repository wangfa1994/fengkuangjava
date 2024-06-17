## 与用户互动
### 运行 Java 程序的参数
main 方法签名说明：
- public 修饰符：Java 类由 JVM 调用，为了让 JVM 可以自由调用这个 main 方法，所以使用 public 修饰符把这个方法暴露出来
- static 修饰符：JVM 调用 main 方法时，不会先创建该类对象，而是直接通过该类来调用 main 方法
- void 返回值：main 方法由 JVM 调用，返回值给 JVM 没有任何意义

### 使用 Scanner 获取键盘输入
Scanner 是一个基于正则表达式的文本扫描器，它可以从文件、输入流、字符串中解析出基本类型值和字符串值
Scanner：hasNext()、hasNextXxx()、nextXxx()、useDelimiter()

## 系统相关
Java 程序在不同操作系统上运行时，可能需要取得平台相关属性，或者调用平台命令来完成特定功能。Java 提供了 System 类和 Runtime 类来与程序的运行平台 进行交互
### System 类
System 类代表当前平台，程序不能创建 System 类的对象。
System.getenv()：获取系统的环境变量
System.getProperties()
System.gc()：通知系统进行垃圾回收
System.runFinalization()：通知系统进行资源清理
System.in：标准输入
System.out：标准输出
System.err：错误输出流
System.setIn()、System.setOut()、System.setErr()：改变系统的标准输入输出
System.currentTimeMillis()：以 Long 型整数形式返回当前时间（毫秒）
System.nanoTime()：以 Long 型整数形式返回当前时间（纳秒）
System.identityHashCode(Object x)：返回指定对象的精确 hashcode 值，也就是根据该对象地址计算得到的 hashcode 值。当某个类的 hashCode 方法被重写后，该类实例的 hashCode 方法就不能唯一标识该对象。但 identityHashcode 得到的 hashcode 值依然可以标识该对象

### Runtime 类
Runtime 类代表 Java 程序的运行时环境，每个 Java 程序都有一个与之对应的 Runtime 实例，可以通过 Runtime.getRuntime() 获取该实例
Runtime.gc()
Runtime.runFinalization()
runtime.availableProcessers()
runtime.freeMemory()
runtime.totalMemort()
runtime.maxMemory()
runtime.exec("notepad.exe")

## 常用类
### Object 类
所有的 Java 类都是 Object 类的子类，所以任何 Java 对象都可以调用 Object 类的方法
ob.equals(Object obj)：判断指定的对象与该对象是否相等。此处相等的标准是，两个对象是同一个对象，因此该方法通常没有太大的实用价值
ob.finalize()：当系统中没有引用变量引用到该对象时，垃圾回收器调用此方法来清理该对象
ob.getClass()：返回该对象的运行时类
ob.hashCode()：返回该对象的 hashCode 值。默认情况下，Object 类的 hashCode() 方法根据该对象的地址来计算，即与 System.identityHashCode(Object x) 计算结果一样。但很多类都重写了 hashCode() 方法，不再根据地址来计算 hashCode
ob.toString()：返回该对象的字符串表示。格式：运行时类名@十六进制 hashCode 值。但很多类都重写了 toString() 方法，用于返回可以表述该对象信息的字符串
ob.wait()、ob.notify()、ob.notifyAll()：控制线程的暂停和运行
ob.clone()：实现对象的克隆。实现 Cloneable 接口，实现 clone() 方法，调用 clone() 方法。浅克隆，不会对引用类型的成员变量值所引用的对象进行克隆。如果需要深克隆，需要开发者自己进行递归克隆

### Java 7 新增的 Objects 类
空指针安全。就是说 null.toString() 不会报错。
Objects.requireNonNull(Object obj)：要求 obj 不为空

### String、StringBuffer、StringBuilder
String 类是一个不可变类，即一旦一个 String 对象被创建后，包含在这个对象中的字符序列是不可改变的，直至这个对象被销毁
StringBuffer 对象则代表一个字符序列可变的字符串，通过 append()、insert()、reverse()、setCharAt()、setLength() 等方法可以改变这个字符串对象的字符序列。最后通过 toString() 方法将其转换为一个 String 对象
StringBuilder 和 StringBuffer 类似，只是 StringBuilder 没有线程安全功能，性能略高

### Test

