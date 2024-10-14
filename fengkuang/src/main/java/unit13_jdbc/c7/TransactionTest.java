package unit13_jdbc.c7;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class TransactionTest {

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
	
	public void insertInTransaction(String[] sqls) throws Exception
	{
		try(
			Connection conn = DriverManager.getConnection(url, user, pass))
		{
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery("select * from student_table");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("student_name"));
			}

			System.out.println("=====================================");
			// 关闭自动提交，开启事务
			conn.setAutoCommit(false);
			try(
				Statement stmt = conn.createStatement())
			{
				for (String sql : sqls)
				{
					stmt.executeUpdate(sql);
				}
			}
			conn.commit();
		}
	}
	
	public static void main(String[] args) throws Exception {
		TransactionTest tt = new TransactionTest();
		tt.initParam("./src/main/java/unit13_jdbc/mysql.ini");
		String[] sqls = new String[]{
				"insert into student_table(student_name,java_teacher) values('aaa',1)",
				"insert into student_table(student_name,java_teacher) values('bbb',1)",
				// 违反外键约束
				"insert into student_table(student_name,java_teacher) values('ccc',5)"
		};
		tt.insertInTransaction(sqls);
	}

}
