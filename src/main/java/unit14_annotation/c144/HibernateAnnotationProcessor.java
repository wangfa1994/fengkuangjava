package unit14_annotation.c144;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

/**
 * @author alvin
 * @date 2020-05-03 23:36
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
// 指定可以处理@Persistent、@Id、@Property 三个注解
@SupportedAnnotationTypes({"Persistent","Id","Property"})
public class HibernateAnnotationProcessor extends AbstractProcessor {
    // 循环处理每个需要处理的程序对象
    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        // 定义一个文件输出流，用于生成额外的文件
        PrintStream ps = null;
        try {
            // 遍历每个被 @Persistent 修饰的 class 文件
            for(Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)){
                // 获取正在处理的类名
                Name clazzName = t.getSimpleName();
                // 获取定义类前的 @Persistent 注解
                Persistent per = t.getAnnotation(Persistent.class);
                // 创建文件输出流
                ps = new PrintStream(new FileOutputStream(clazzName+".hbm.xml"));
                // 执行输出
                ps.println("<?xml version=\"1.0\"?>");
                ps.println("...");
                // 输出 per 的 table 值
                ps.println("\" table=\""+ per.table() + "\">");
                for (Element f : t.getEnclosedElements()){
                    // 只处理成员变量上的注解
                    if(f.getKind() == ElementKind.FIELD) {
                        // 获取成员变量定义前的 @Id 注解
                        Id id = f.getAnnotation(Id.class);
                        // 当 @Id 存在时，输出 <id.../> 元素
                        if(id != null) {
                            ps.println("<id name=\""+
                                    f.getSimpleName() +
                                    "\" clomn=\"" + id.column() +
                                    "\" type=\""+id.type()+
                                    "\">");
                            }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return true;
    }
}
