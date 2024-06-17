package unit13_jdbc.c3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnMySql {
	public static void main(String[] args) throws Exception{
		// 1.加载驱动，使用反射知识
		// Class.forName("com.mysql.cj.jdbc.Driver");
		try(
			// 2.使用 DriverManager 获取数据库连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?" +
					"useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true", "root", "root");
			// 3.使用 Connection 来创建一个 Statement 对象
			Statement stmt = conn.createStatement();
			// 4.执行 SQL 语句
			//ResultSet rs = stmt.executeQuery("SELECT * from channel_code_info")
			ResultSet rs = stmt.executeQuery("select s.*, teacher_name from student_table s, teacher_table t where t.teacher_id = s.java_teacher")
		  )
		{
			while (rs.next())
			{
				System.out.println(rs.getInt(1) + "\t"
						+ rs.getString(2) + "\t"
						+ rs.getString(3) + "\t"
						+ rs.getString(4));
			}
		}
	}
}
