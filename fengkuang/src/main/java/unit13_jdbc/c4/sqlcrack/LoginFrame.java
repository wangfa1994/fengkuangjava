package unit13_jdbc.c4.sqlcrack;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-22 23:14
 */
public class LoginFrame {
    private final String PROP_FILE = "./src/main/java/unit13_jdbc/mysql.ini";
    private String driver;
    private String url;
    private String user;
    private String pass;
    // 登录界面的 GUI 组件
    private JFrame jf = new JFrame("登录");
    private JTextField userField = new JTextField(20);
    private JTextField passField = new JTextField(20);
    private JButton loginButton = new JButton("登录");
    public void init() throws Exception {
        final Properties connProp = new Properties();
        connProp.load(new FileInputStream(PROP_FILE));
        driver = connProp.getProperty("driver");
        url = connProp.getProperty("url");
        user = connProp.getProperty("user");
        pass = connProp.getProperty("pass");
        // 加载驱动
        Class.forName(driver);
        // 为登录按钮添加事件监听器
        loginButton.addActionListener(e -> {
            // 登录成功则显示"登录成功"
            if(safeValidate(userField.getText(), passField.getText())) {
                JOptionPane.showMessageDialog(jf, "登录成功");
            }
            else {
                JOptionPane.showMessageDialog(jf, "登录失败");
            }
        });
        jf.add(userField, BorderLayout.NORTH);
        jf.add(passField);
        jf.add(loginButton, BorderLayout.SOUTH);
        jf.pack();
        jf.setVisible(true);
    }
    private boolean validate(String userName, String userPass) {
        String sql = "select * from jdbc_test "
                + "where jdbc_name='" + userName
                + "' and jdbc_desc='" + userPass + "'";
        System.out.println(sql);
        try (Connection conn = DriverManager.getConnection(url,user,pass);
            final Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean safeValidate(String userName, String userPass) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(
                    "select * from jdbc_test where jdbc_name=? and jdbc_desc=?")) {
             pstmt.setString(1, userName);
             pstmt.setString(2, userPass);
             try (ResultSet rs = pstmt.executeQuery()) {
                 if (rs.next()) {
                     return true;
                 }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        new LoginFrame().init();
    }

}
