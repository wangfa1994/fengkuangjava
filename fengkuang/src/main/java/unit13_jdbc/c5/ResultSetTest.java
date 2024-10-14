package unit13_jdbc.c5;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-23 16:16
 */
public class ResultSetTest {
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

    public void query(String sql) throws Exception {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = pstmt.executeQuery()) {
            rs.last();
            int rowCount = rs.getRow();
            for (int i = rowCount; i > 0; i--) {
                rs.absolute(i);
                System.out.println(rs.getString(1)+ "\t" +
                        rs.getString(2) + "\t" +
                        rs.getString(3));
                // 修改记录指针所指记录，第 2 列的值
                rs.updateString(2, "学生名" + i);
                // 提交修改
                rs.updateRow();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ResultSetTest rt = new ResultSetTest();
        rt.initParam("./src/unit13_jdbc/mysql.ini");
        rt.query("select * from student_table");
    }
}
