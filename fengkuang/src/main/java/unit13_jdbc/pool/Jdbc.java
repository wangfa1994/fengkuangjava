package unit13_jdbc.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc {


    public static void main(String[] args) throws Exception{
        System.out.println("old.....................");
       // old();
        System.out.println("dbcp.....................");
        dbcp();
        System.out.println("c3p0.....................");
        //c3p0();
        System.out.println("druid.....................");
        //druid();
    }

    public static void old() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fengkuang?useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true",
                "root", "root");
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select s.*, teacher_name from student_table s, teacher_table t where t.teacher_id = s.java_teacher");
        while (rs.next())
        {
            System.out.println(rs.getInt(1) + "\t"
                    + rs.getString(2) + "\t"
                    + rs.getString(3) + "\t"
                    + rs.getString(4));
        }
        connection.close();
    }


    public static void dbcp() throws Exception{
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/fengkuang?useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(2);

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select s.*, teacher_name from student_table s, teacher_table t where t.teacher_id = s.java_teacher");
        while (rs.next())
        {
            System.out.println(rs.getInt(1) + "\t"
                    + rs.getString(2) + "\t"
                    + rs.getString(3) + "\t"
                    + rs.getString(4));
        }
        connection.close();
    }

    public static void c3p0() throws Exception {
        System.out.println("======================");

        ComboPooledDataSource dataSource  = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/fengkuang?useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setMaxPoolSize(40);
        dataSource.setMinPoolSize(2);
        dataSource.setInitialPoolSize(2);
        dataSource.setMaxStatements(120);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select s.*, teacher_name from student_table s, teacher_table t where t.teacher_id = s.java_teacher");
        while (rs.next())
        {
            System.out.println(rs.getInt(1) + "\t"
                    + rs.getString(2) + "\t"
                    + rs.getString(3) + "\t"
                    + rs.getString(4));
        }
        connection.close();
    }

    public static void druid() throws Exception {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/fengkuang?useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMaxActive(40);
        dataSource.setMinIdle(2);
        dataSource.setInitialSize(2);
        //dataSource.setmaxst(120);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select s.*, teacher_name from student_table s, teacher_table t where t.teacher_id = s.java_teacher");
        while (rs.next())
        {
            System.out.println(rs.getInt(1) + "\t"
                    + rs.getString(2) + "\t"
                    + rs.getString(3) + "\t"
                    + rs.getString(4));
        }
        connection.close();

    }
}
