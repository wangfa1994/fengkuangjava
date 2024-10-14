# JDBC 基础
## JDBC 简介
JDBC 为数据库开发提供了标准的 API，他们只是接口，没有实现类，这些实现类由各数据库厂商提供实现，这些实现就是驱动程序。
而程序员使用 JDBC 时只要面对标准的 JDBC API 编程即可。这就是面向接口编程的典型应用。

## 安装数据库
MySql：root/root
show databases;
create database if not exits databasename;
drop database databasename;
use databasename;
show tables;
desc tablename;
MyISAM：事务支持不好
InnoDB：通过建立行级锁来保证事务完整性

查询语句：select
DML：insert、update、delete
DDL：create、alter、drop、truncate
DCL：grant、revoke
事务控制语句：commit、rollback、savepoint

## JDBC 典型用法
DriverManager：用于管理 JDBC 驱动的服务类。getConnection()

Connection：数据库连接对象。createStatement()、prepareStatement()、prepareCall()
setSavepoint()
setTransactionIsolation(level)
rollback()
rollback(savepoint)
setAutoCommit()
commit()
setSchema()
getSchema()
setNetWorkTimeout()
getNetWorkTimeout()
只有获得 Statement 后，才可以执行 SQL 语句

Statement：用于执行 SQL 语句的工具接口
executeQuery()
executeUpdate()
execute()
getResultSet()
getUpdateCount()
closeOnCompletion()
isCloseOnCompletion()
executeLargeUpdate()

PreparedStatement：预编译的 Statement 对象
比 Statement 多了 setXxx()方法
预编译 SQL 语句，性能更好
无需拼接 SQL 语句，编程更简单
防止 SQL 注入，安全性更好

ResuleSet：结果集对象
close()
absolute(int row)
beforeFirst()
first()
previous()
next()
last()
afterLast()
getXxx(int index)
getXxx(String columnLabel)

CallableStatement 调用存储过程

处理 Blob 类型数据

## Java 7 的 RowSet
JdbcRowSet
setUrl()、setUsername()、setPassword()、setCommand()、execute()、populate(ResultSet rs)
CachedRowSet
FileteredRowSet
JoinRowSet
WebRowSet

JdbcRowSetImpl
JdbcRowSetImpl()、JdbcRowSetImpl(Connection conn)、JdbcRowSetImpl(ResultSet rs)
CachedRowSetImpl
FilteredRowSetImpl
JoinRowSetImpl
WebRowSetImpl

### 离线 RowSet
在使用 ResultSet 时代，在访问 ResultSet 时，必须保持 Connection 为打开状态

### 离线 RowSet 的查询分页
populate(ResultSet rs, int startRow)
setPageSize(int pageSize)
previousPage()
nextPage()

## 事务处理
事务四个特征：原子性、一致性、隔离性、持续性
begin、start transaction
roolback
commit
savepoint a
rollback to a
DDL、DCL语句会导致事务立即提交

批量更新
stmt.addBatch(sql1)
stmt.addBatch(sql2)
stmt.addBatch(sql3)
stmt.executeBatch()、stmt.executeLargeBatch()
DatabaseMetaData supportsBatchUpdatas()

## 使用连接池管理连接
通过 DriverManager 频繁的获取连接，并频繁的关闭连接，将造成系统性能低下。
数据库连接池解决方案：当应用程序启动时，系统主动建立足够的数据库连接，并将这些连接组成一个连接池。每次应用程序请求数据库连接时，无须重新打开连接，而是从连接池中取出已有的连接使用，使用完成后不再关闭数据库连接，而是直接将连接归还给连接池。通过是使用连接池，将大大提高应用程序的运行效率。
DBCP
C3PO

