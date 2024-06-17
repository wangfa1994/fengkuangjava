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

public class CachedRowSetPage {

	private String driver;
	private String url;
	private String user;
	private String pass;
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
	
	public CachedRowSet query(String sql, int pageSize, int page) throws Exception
	{
		try(
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql))
		{
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet cachedRs = factory.createCachedRowSet();
			// 设置煤业显示 pagesize 条记录
			cachedRs.setPageSize(pageSize);
			// 从指定位置开始装
			cachedRs.populate(rs, (page - 1) * pageSize + 1);
//			cachedRs.populate(rs);

			cachedRs.nextPage();
			// 也不行
			// cachedRs.previousPage();
			return cachedRs;
		}
	}
	
	public static void main(String[] args) throws Exception {
		CachedRowSetPage cp = new CachedRowSetPage();
		cp.initParam("./src/unit13_jdbc/mysql.ini");
		CachedRowSet rs = cp.query("select * from student_table", 3, 2);
		while (rs.next())
		{
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
		}
	}

}
