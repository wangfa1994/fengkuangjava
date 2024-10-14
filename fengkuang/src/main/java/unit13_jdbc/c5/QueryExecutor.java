package unit13_jdbc.c5;

import javax.management.ValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;

/**
 * @author alvin
 * @date 2020-05-23 18:27
 */
public class QueryExecutor {
    JFrame jf = new JFrame("查询执行器");
    private JScrollPane scrollPane;
    private JButton execBn = new JButton("查询");
    // 用于输入查询语句的文本框
    private JTextField sqlField = new JTextField(45);
    private static Connection conn;
    private static Statement stmt;
    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("./src/unit13_jdbc/mysql.ini"));
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String pass = props.getProperty("pass");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init() {
        JPanel top = new JPanel();
        top.add(new JLabel("输入查询语句："));
        top.add(sqlField);
        top.add(execBn);
        // 为执行按钮、单行文本框添加事件监听器
        execBn.addActionListener(new ExceListener());
        sqlField.addActionListener(new ExceListener());
        jf.add(top, BorderLayout.NORTH);
        jf.setSize(680, 480);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    // 定义监听器
    class ExceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // 删除原来的 JTable（JTable 适用 scrollPane 来包装）
            if (scrollPane != null) {
                jf.remove(scrollPane);
            }
            try (ResultSet rs = stmt.executeQuery(sqlField.getText())) {
                // 取出 ResultSet 的 MetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                Vector<String> columnNames = new Vector<>();
                Vector<Vector<String>> data = new Vector<>();
                // 把 ResultSet 的所有列名添加到 Vector 里
                for (int i=0; i < rsmd.getColumnCount(); i++) {
                    columnNames.add(rsmd.getColumnName(i + 1));
                }
                // 把 ResultSet 的所有记录添加到 Vector 里
                while (rs.next()) {
                    Vector<String> v = new Vector<>();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        v.add(rs.getString(i + 1));
                    }
                    data.add(v);
                }
                // 创建新的 JTable
                JTable table = new JTable(data, columnNames);
                scrollPane = new JScrollPane(table);
                // 添加新的 Table
                jf.add(scrollPane);
                // 更新主窗口
                jf.validate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new QueryExecutor().init();
    }
}
