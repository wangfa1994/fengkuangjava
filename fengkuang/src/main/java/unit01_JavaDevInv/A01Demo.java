package unit01_JavaDevInv;

public class A01Demo {

    /**
     *
     * ## 1.1 Java 语言发展简史
     * - 1995 年，Sun 公司发布 Java 语言
     * - 1996 年年初，Sun 发布 JDK 1.0。
     *   这个版本包含两部分：运行环境（即 JRE）和开发环境（即 JDK）。
     *  运行环境包含核心 API、集成 API、用户界面 API、发布技术、Java 虚拟机（JVM）5 个部分；
     *  开发环境包括编译 Java 程序的编译器（即 javac 命令）。
     * - 1997 年 2 月 18 日发布了 JDK 1.1。JDK 1.1 增加了 JIT （即时编译）编译器。
     * - 1998 年 12 月，Sum 发布了 Java 历史上最重要的一个版本；JDK 1.2 将 Java 分成了 J2EE、J2SE、J2ME 三个版本。
     * 	- J2ME：主要用于控制移动设备和信息家电等有限存储的设备
     * 	- J2SE：整个 Java 技术的核心和基础，他是 J2ME 和 J2EE 编程的基础
     * 	- J2EE：Java 技术应用最为广泛的部分，J2EE 提供了企业应用开发相关的完整解决方案
     * - 2002 年 2 月，Sun 发布了 JDK 历史上最为成熟的版本：JDK 1.4。JDK 1.4 已经可以使用 Java 实现大多数的应用了
     *     在此期间，Java 语言在企业应用领域大放异彩，涌现出大量基于 Java 语言的开源框架：Strtus、WebWork、Hibernate、Spring等；
     *   大量企业应用服务器也开始涌现：WebLogic、WebSphere、JBoss 等
     * - 2004 年 10 月，Sun 发布了 JDK 1.5，同时 Sun 将 JDK 1.5 改名为 Java SE 5.0、Java EE 5.0、Java ME 5.0
     *     JDK 1.5 增加了泛型、增强的 for 语句、可变数量的形参、注释（Annotations）、自动拆箱和装箱等功能
     *           推出了 EJB 3.0 规范；推出了自己的 MVC 框架规范：JSF
     * - 2006 年 12 月，Sun 公司发布了 JDK 1.6（也被称为 Java SE 6）
     * - 2009 年 4 月 20 日，Oracle 宣布以每股 9.5 美元的价格收购 Sun
     * - 2011 年 7 月 28 日，Oracle 发布 Java SE 7
     * 	引入二进制整数、支持字符串的 switch 语句、菱形语法、多异常捕捉、自动关闭资源的 try 语句
     * - 2014 年 3 月 18 日，Oracle 发布 Java SE 8
     * 	引入 Lambda 表达式
     *
     * ## 1.3 Java 程序运行机制
     * ### 高级语言运行机制
     * 计算机高级语言按程序的执行方式可以分为编译型和解释型两种
     * - 编译型语言：使用专门的编译器，针对特定平台将源代码一次性翻译为可被该平台硬件执行的机器码，并包装成该平台所能识别的可执行性程序。效率高，但不跨平台。C、C++
     * - 解释型语言： 使用专门的解释器对程序源代码逐行解释成特定平台的机器码，并立即执行。相当于每执行一次，就要编译一次。因此效率低，但是跨平台。Ruby、Python
     * - Java 语言是一种特殊的高级语言，它即具有解释性语言的特征，也具有编译型语言的特征，因为 Java 程序要经过先编译，后解释两个步骤
     * 	Java 的编译不是生成特定平台的机器码，而是生成与平台无关的字节码（也就是 *.class 文件）。当然，这种字节码不是可执行的，必须使用 Java 的解释器来解释执行。Java 源文件，javac 编译得到 *.class 字节码文件，java 解释得到特定平台的字节码。Java 的解释器就是 JVM（Java Virtual Machine）。JVM 是 Java 程序跨平台的关键部分。
     *
     * ## 1.4 开发 Java 的准备
     * ### JDK
     * JDK 全称是 Java SE Development Kit，即 Java 标准开发包。包括 Java 编译器、Java 运行时环境（JRE，包含 JVM），以及常用的类库。
     *
     * ## 1.6 Java 程序的基本规则
     * 如果 Java 源文件里定义了一个 public 类，则该文件的主文件名必须与该 public 类的类名相同。因此，一个 Java 源文件最多只能定义一个 public 类
     *
     */
}
