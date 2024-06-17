package unit13_jdbc.c4;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class PreparedStatementTest {

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
	public void insertUseStatement() throws Exception
	{
		long start = System.currentTimeMillis();
		try(
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement())
		{
			for (int i=0; i < 100; i++)
			{
				stmt.executeUpdate("insert into student_table(student_name,java_teacher) values('姓名" + i + "',20)");
			}
			System.out.println("使用 Statement 费时：" + (System.currentTimeMillis() - start));
		}
	}
	
	public void insertUserPreparedStatement() throws Exception
	{
		long start = System.currentTimeMillis();
		try(
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = conn.prepareStatement("insert into student_table(student_name,java_teacher) values(?,1)"))
		{
			for (int i=0; i < 100; i++)
			{
				pstmt.setString(1, "姓名" + i);
				pstmt.executeUpdate();
			}
			System.out.println("使用 PreparedStatement 费时：" + (System.currentTimeMillis() - start));
		}
	}
	
	public static void main(String[] args) throws Exception {
		PreparedStatementTest pt = new PreparedStatementTest();
		pt.initParam("./src/unit13_jdbc/mysql.ini");
		pt.insertUseStatement();
		pt.insertUserPreparedStatement();
	}

}
