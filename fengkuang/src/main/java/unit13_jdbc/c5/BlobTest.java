package unit13_jdbc.c5;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-23 16:31
 */
public class BlobTest {
    JFrame jf = new JFrame("图片管理程序");
    private static Connection conn;
    private static PreparedStatement insert;
    private static PreparedStatement query;
    private static PreparedStatement queryAll;
    private DefaultListModel<ImageHolder> imageModel
            = new DefaultListModel<>();
    private JList<ImageHolder> imageList = new JList<>(imageModel);
    private JTextField filePath = new JTextField(26);
    private JButton browserBn = new JButton("...");
    private JButton uploadBn = new JButton("上传");
    private JLabel imageLabel = new JLabel();
    // 以当前路径创建文件选择器
    JFileChooser chooser = new JFileChooser(".");

    // 创建文件过滤器
    FileNameExtensionFilter filter = null;
    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("./src/main/java/unit13_jdbc/mysql.ini"));
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String pass = props.getProperty("pass");
            Class.forName(driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
            // 创建执行插入的 statement 对象
            insert = conn.prepareStatement("insert into img_table" +
                    " values(null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            // 创建执行查询的 statement
            query = conn.prepareStatement("select img_data from img_table" +
                    " where img_id=?");
            queryAll = conn.prepareStatement("select img_id, " +
                    " img_name from img_table");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws SQLException {
        // 初始化文件选择器
        filter = new FileNameExtensionFilter("图片文件（*.jpg,*.jpeg,*.gif,*.png）",
                "jpg", "jpeg", "gif", "png");
        chooser.addChoosableFileFilter(filter);
        // 禁止 文件类型 下拉列表中显示 所有文件 选项
        chooser.setAcceptAllFileFilterUsed(false);
        // 初始化程序界面
        fillListModel();
        filePath.setEditable(false);
        // 只能单选
        imageList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JPanel jp = new JPanel();
        jp.add(filePath);
        jp.add(browserBn);
        browserBn.addActionListener(event -> {
            // 显示文件对话框
            int result = chooser.showDialog(jf, "浏览图片文件上传");
            // 如果用户选择了 APPROVE(赞同)按钮，即打开，保存等效按钮
            if(result == JFileChooser.APPROVE_OPTION) {
                filePath.setText(chooser.getSelectedFile().getPath());
            }
        });
        jp.add(uploadBn);
        uploadBn.addActionListener(avt -> {
            // 如果上传文件的文本框有内容
            if (filePath.getText().trim().length() > 0) {
                // 将指定文件保存到数据库
                upload(filePath.getText());
                // 清空文本框内容
                filePath.setText("");
            }
        });
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
        left.add(jp, BorderLayout.SOUTH);
        jf.add(left);
        imageList.setFixedCellHeight(20);
        jf.add(new JScrollPane(imageList), BorderLayout.EAST);
        imageList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 如果鼠标双击
                if (e.getClickCount() >= 2) {
                    // 取出选中的 List 项
                    ImageHolder cur = (ImageHolder)imageList.getSelectedValue();
                    try {
                        // 显示选中项对应的 Image
                        showImage(cur.getId());
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                    }
                }
            }
        });
        jf.setSize(720, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    // 查找 img_table 填充 ListModel
    public void fillListModel() throws SQLException {
        try (ResultSet rs = queryAll.executeQuery()) {
            // 先清除所有元素
            imageModel.clear();
            // 把查询的全部记录添加到 ListMoel 中
            while (rs.next()) {
                imageModel.addElement(new ImageHolder(rs.getInt(1),
                        rs.getString(2)));
            }
        }
    }
    // 将指定图片放入数据库
    public void upload(String fileName) {
        // 截取文件名
        String imageName = fileName.substring(fileName.lastIndexOf("\\") + 1,
                fileName.lastIndexOf('.'));
        File f = new File(fileName);
        try (InputStream is = new FileInputStream(f)) {
            // 设置图片名参数
            insert.setString(1, imageName);
            // 设置二进制流参数
            insert.setBinaryStream(2, is, (int)f.length());
            int affect = insert.executeUpdate();
            if (affect == 1) {
                // 重新更新 ListModel， 将会让 JList 显示最新的图片列表
                fillListModel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 根据图片 ID 来显示图片
    public void showImage(int id) throws SQLException {
        // 设置参数
        query.setInt(1, id);
        try (ResultSet rs = query.executeQuery()) {
            if (rs.next()) {
                // 取出 Blob 列
                Blob imgBlob = rs.getBlob(1);
                // 取出 Blob 列里的数据
                ImageIcon icon = new ImageIcon(imgBlob.getBytes(1L,
                        (int)imgBlob.length()));
                imageLabel.setIcon(icon);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        new BlobTest().init();
    }
}
// 创建 FileFilter 的子类，用以实现文件过滤功能
class ExtensionFileFilter extends FileFilter {
    private String description = "";
    private ArrayList<String> extensions = new ArrayList<>();
    // 自定义方法，用于添加文件扩展名
    public void addExtension(String extension) {
        if (!extension.startsWith(".")) {
            extension = "." + extension;
            extensions.add(extension.toLowerCase());
        }
    }
    // 用于设置该文件过滤器的描述文本
    public void setDescription(String aDescription) {
        description = aDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) return true;
        // 将文件名转为小写
        String name = f.getName().toLowerCase();
        // 遍历所有可接收的扩展名，相同则接收
        for (String extension : extensions) {
            if (name.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
// 创建一个 ImageHolder，用于封装图片名、图片 ID
class ImageHolder {
    private int id;
    private String name;
    public ImageHolder() {}
    public ImageHolder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
