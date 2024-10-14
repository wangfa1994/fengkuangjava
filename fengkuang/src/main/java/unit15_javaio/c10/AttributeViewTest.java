package unit15_javaio.c10;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.List;

/**
 * @author alvin
 * @date 2020-05-17 21:05
 * 访问文件属性
 */
public class AttributeViewTest {
    public static void main(String[] args) throws Exception {
        // 获取将要操作的文件
        final Path testPath = Paths.get("./src/main/java/unit15_javaio/c10/AttributeViewTest.java");

        // 获取访问基本属性的 view
        final BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        // 获取访问基本属性的 attrs
        final BasicFileAttributes basicAttribs = basicView.readAttributes();
        System.out.println("创建时间：" + new Date(basicAttribs.creationTime().toMillis()));
        System.out.println("最后访问时间：" + new Date(basicAttribs.lastAccessTime().toMillis()));
        System.out.println("文件大小：" + basicAttribs.size());

        // 获取访问文件属性主信息的 view
        final FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
        System.out.println("文件所有者：" + ownerView.getOwner());
        // 获取系统中 guest 对应的用户
        final UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
        // 修改用户
        // ownerView.setOwner(user);

        // 获取访问自定义属性的 view
        final UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
        // 添加一个自定义属性
        userView.write("发行者", Charset.defaultCharset().encode("alvin"));
        final List<String> attrNames = userView.list();
        for (String name : attrNames) {
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println(name + "---->" + value);
        }

        // 获取访问 DOS 属性的 view
        final DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
        // 将文件设置隐藏、只读
        dosView.setHidden(true);
        dosView.setReadOnly(true);
    }
}
