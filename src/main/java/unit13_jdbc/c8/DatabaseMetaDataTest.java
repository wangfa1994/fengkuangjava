package unit13_jdbc.c8;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseMetaDataTest {

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
	
	public void info() throws Exception
	{
		try(
			Connection conn = DriverManager.getConnection(url, user, pass))
		{
			// 获取 DatabaseMetaData 对象
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getTableTypes();
			System.out.println("---MySql支持的表类型信息---");
			printResultSet(rs);
			rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
			System.out.println("---当前数据库里的数据表信息---");
			printResultSet(rs);
			rs = dbmd.getPrimaryKeys(null, null, "student_table");
			System.out.println("---student_table 表的主键信息---");
			printResultSet(rs);
			rs = dbmd.getProcedures(null, null, "%add_pro%");
			System.out.println("---当前数据库里的存储过程信息---");
			printResultSet(rs);
			rs = dbmd.getCrossReference(null,null,"teacher_table",
					null, null, "student_table");
			System.out.println("---teacher_table 和 student_table之间的外键约束---");
			printResultSet(rs);
			rs = dbmd.getColumns(null, null, "student_table", "%");
			System.out.println("---student_table 表的全部数据列---");
			printResultSet(rs);
		}
	}
	
	public void printResultSet(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++)
		{
			System.out.print(rsmd.getColumnName(i + 1) + "\t");
		}
		System.out.print("\n");
		// 打印 ResultSet 里的全部数据
		while (rs.next())
		{
			for (int i=0; i < rsmd.getColumnCount(); i++)
			{
				System.out.print(rs.getString(i + 1) + "\t");
			}
			System.out.print("\n");
		}
		rs.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		DatabaseMetaDataTest dt = new DatabaseMetaDataTest();
		dt.initParam("./src/main/java/unit13_jdbc/mysql.ini");
		dt.info();
	}

}
