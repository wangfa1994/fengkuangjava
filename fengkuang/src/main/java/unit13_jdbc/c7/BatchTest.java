package unit13_jdbc.c7;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-23 16:04
 */
public class BatchTest {
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

    public void insertBatch(String[] sqls) throws Exception {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            // 保存当前的模式
            boolean autoCommit = conn.getAutoCommit();
            // 关闭自动提交,开启事务
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                // 循环插入 sql
                for (String sql : sqls) {
                    stmt.addBatch(sql);
                }
                // 同时提交所有的 SQL
                stmt.executeLargeBatch();
                conn.commit();
                // 恢复原来的提交模式
                conn.setAutoCommit(autoCommit);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BatchTest bt = new BatchTest();
        bt.initParam("./src/unit13_jdbc/mysql.ini");
        String[] sqls = new String[]{
                "insert into student_table(student_name,java_teacher) values('aaa',1)",
                "insert into student_table(student_name,java_teacher) values('bbb',1)",
                // 违反外键约束
                "insert into student_table(student_name,java_teacher) values('ccc',1)"
        };
        bt.insertBatch(sqls);
    }
}
