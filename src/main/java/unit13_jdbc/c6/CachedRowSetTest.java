package unit13_jdbc.c6;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetTest {

	private static String driver;
	private static String url;
	private static String user;
	private static String pass;
	public void initParam(String paramFile) throws Exception
	{
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
		Class.forName(driver);
	}
	
	public CachedRowSet query(String sql) throws Exception
	{
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRs = factory.createCachedRowSet();
		// 使用 ResultSet 填装 RowSet
		cachedRs.populate(rs);
		// 关闭资源
		rs.close();
		stmt.close();
		conn.close();
		return cachedRs;
	}
	
	public static void main(String[] args) throws Exception {
		CachedRowSetTest ct = new CachedRowSetTest();
		ct.initParam("./src/main/java/unit13_jdbc/mysql.ini");
		CachedRowSet rs = ct.query("select * from student_table");
		rs.afterLast();
		while (rs.previous())
		{
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
			if (rs.getInt("student_id") == 2)
			{
				rs.updateString("student_name", "孙悟空");
				rs.updateRow();
			}
		}
		// 重新获取数据库连接
		Connection conn = DriverManager.getConnection(url, user, pass);
		conn.setAutoCommit(false);
		// 把对 RowSet 所做的修改同步到底层数据库
		rs.acceptChanges(conn);
	}

}
