## 网络编程的基础知识
IP 地址用于唯一地标识网络中的一个通讯实体。是一个 32 位的整数。
端口是一个 16 位的整数，用于表示数据交给哪个通讯程序处理。0 ~ 65535
公认端口：0 ~ 1023
注册端口：1024 ~ 49151
动态或私有端口：49152 ~ 65535

## Java 基本网络支持
### InetAddress
Inet4Address、Inet6Address
getByName(String host)
getByAddress(byte[] addr)
getCanonicalHostName()
getHostAddress()
getHostName()
isReachable()

### URLDecoder、URLEncoder
用于完成普通字符串和 MIME 字符串之间的互相转换
URLDecoder.decode("", "utf-8")
URLEncoder.encode("", "gbk");

### URL、URLConnection、URLPermission
URL 可以由协议名、主机、端口和资源组成
protocol://host:port/resourceName
http://www.crazyit.org/index.php

URL：getFile()、getHost()、getPath()、getPort()、getProtocol()、getQuery()、openConnection()、openStream()

URLConnection：

## 基于 TCP 协议的网络编程
TCP/IP 通信协议是一种可靠的网络协议，它在通信的两端各建立一个 Socket，从而在通信的两端之间形成网络虚拟链路。
### 使用 ServerSocket 创建 TCP 服务端
accept()
ServerSocket(int port)
ServerSocket(int port, int backlog)
close()

### 使用 Socket 进行通信
Socket(InetAddress/String remoteAddress, int port)
getInputStream()
getOutputStream()
setSoTimeout(int timeout)
connect(InetSocketAddress(IP, PORT), 1000)

### 加入多线程

### 半关闭的 Socket
直接关闭输出流将导致 Socket 关闭
Socket 提供了如下两个半关闭的方法，只关闭 Socket 的输入流或者输出流，用以表示输出数据已经发送完成
shutdownInput()
shutdownOutput()





