package unit13_jdbc.c6;

import java.io.FileInputStream;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetFactoryTest {

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
	public void update(String sql) throws Exception
	{
		RowSetFactory factory = RowSetProvider.newFactory();
		try(
			// 获取 JdbcRowSet
			JdbcRowSet jdbcRs = factory.createJdbcRowSet()
		)
		{
			// 设置必要的连接信息
			jdbcRs.setUrl(url);
			jdbcRs.setUsername(user);
			jdbcRs.setPassword(pass);
			// 设置 SQL 查询语句
			jdbcRs.setCommand(sql);
			// 执行查询
			jdbcRs.execute();
			jdbcRs.afterLast();

			// 向前滚动结果集
			while (jdbcRs.previous())
			{
				if (jdbcRs.getInt("student_id") == 3)
				{
					// 修改指定记录行
					jdbcRs.updateString("student_name", "孙悟空");
					jdbcRs.updateRow();
				}
				System.out.println(jdbcRs.getString(1) + "\t" + jdbcRs.getString(2) + "\t" + jdbcRs.getString(3));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		RowSetFactoryTest jt = new RowSetFactoryTest();
		jt.initParam("./src/unit13_jdbc/mysql.ini");
		jt.update("select * from student_table");
	}

}
