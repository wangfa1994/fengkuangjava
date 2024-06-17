try：放置可能发生异常的代码
catch：异常类型和代码块，表明该 catch 快用于处理这种类型的代码
finally：用于回收再 try 块里打开的物理资源
throws：主要用在方法的签名，用于声明该方法可能抛出的异常
throw：可以单独作为语句使用，抛出一个具体的异常

Java 7 增加带资源的 try 块、捕捉多异常的 catch 块
Java 将异常分为两种：Checked 异常和 Runtime 异常
Java 认为 Checked 异常都是可以在编译阶段被处理的异常，所以他强制程序处理这类异常。

## 异常概述
问题：无法穷举所有异常情况；错误处理代码和业务实现代码混杂
if (用户输入不合法){
  alert 输入不合法
  goto retry
} else {
  // 业务实现代码
  ...
}

## 异常处理机制
当程序运行出现意外情况时，系统会自动生成一个 Exception 对象，从而实现将业务功能实现代码和错误处理代码分离，提供更好的可读性。
if (一切正常){
  // 业务实现代码
  ...
} else {
  alert 输入不合法
  goto retry
}

try {
  // 业务实现代码
  ...
} catch (Exception e) {
  alert 输入不合法
  goto retry
}

### 异常继承体系
在 try 中声明的变量，只能在 try 中使用
Throwable
  Error
  Exception
    RuntimeException：indexOutOfBoundsException,NullPointerException,ClassCastException
    SQLException
    IOException
    
### 多异常捕捉
用 | 分开
多异常捕捉，异常变量有 final 限制，不可重新赋值，例如：ie = new ArithmeticException("test");

### 访问异常信息
getMessage()
printStackTrace()
getStackTrace()

### 使用 finally 回收资源
垃圾回收机制不会回收物理资源，只会回收堆内存中对象所占用的资源
有 try 之后，catch 和 finally 至少有一个

catch 有 return 会先执行 finally；有 System.exit(1)，不会执行 finally   
如果 finally 中有 return 或 throw 等导致方法终止的语句，将使得 try，catch 块中的 return 和 throw 失效

### 自动关闭资源的 try 语句
try 中资源必须实现 AutoCloseable 或 Closeable 接口
自动关闭资源的 try 相当于包含了隐士的 finally，所以可以既没有 catch 又没有 finally

## Checked 异常和 Runtime 异常体系
不处理 Checked 异常编译无法通过
Runtime 不强制捕捉
### 使用 throws 声明抛出异常
方法重写时：
子类方法声明抛出的异常类型应该是父类异常类型的子类或相同
子类方法抛出的异常不允许比父类方法声明抛出的异常多
### 使用 throw 抛出异常
throw Checked 异常：该 throw 要么处于 try 块里，显示捕捉该异常，要么放在一个带 throws 声明抛出的方法中
### 自定义异常
extends Exception
三个构造器：无参、String、Throwable
### catch 和 throw 同时使用
catch 到后 抛出一个用户自定义的业务异常
### Java 7 增强的 throw 语句
throws 更加智能
### 异常链
把原始异常信息隐藏起来，仅向上提供必要的异常提示信息的处理方式，可以保证底层异常不会扩散到表现层，可以避免向上暴露太多实现细节。
这种捕获一个异常，然后抛出另一个异常，并把原始异常信息保存下来是一种简单的链式处理。责任链模式
### Java 的异常跟踪栈
printStackTrace()

## 异常处理规则
是程序混乱最小化
捕捉并保留诊断信息
通知合适的人员
采用合适的方式结束异常活动



