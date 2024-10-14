package unit18_classload.c3;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author alvin
 * @date 2020-05-04 17:33
 */
// 定义可重复注解
@Repeatable(Annos.class)
@interface Anno{}

@Retention(RetentionPolicy.RUNTIME)
@interface Annos {
    Anno[] value();
}

// 使用 4 个注解修饰该类
@SuppressWarnings(value = "unchacked")
@Deprecated
@Anno
@Anno
public class ClassTest {
    // 私有构造器
    private ClassTest(){}
    // 有参构造器
    public ClassTest(String name){
        System.out.println("执行有参构造器");
    }
    // 无参方法
    public void info(){
        System.out.println("无参 info...");
    }
    // 有参方法
    public void info(String str){
        System.out.println("有参 info...:" + str);
    }
    // 内部类
    class Inner{}

    public static void main(String[] args) throws Exception{
        // 获取 Class 对象
        final Class<ClassTest> clazz = ClassTest.class;
        // 获取构造器
        final Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        System.out.println("ClassTest 的全部构造器如下：");
        for(Constructor c : ctors){
            System.out.println(c);
        }
        // 获取该 Class 对象所对应类的全部 public 方法
        final Method[] mtds = clazz.getMethods();
        System.out.println("ClassTest 的全部 public 方法如下：");
        for(Method md : mtds){
            System.out.println(md);
        }
        // 获取该 class 对象所对应类的指定方法
        System.out.println("带一个参数的 info 方法为：" +
                clazz.getMethod("info", String.class));
        // 获取全部注解
        final Annotation[] anns = clazz.getAnnotations();
        System.out.println("全部注解如下：");
        for(Annotation an : anns) {
            System.out.println(an);
        }
        System.out.println("@SuppressWarnings 注解为：" +
                Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
        System.out.println("@Anno 注解为：" +
                Arrays.toString(clazz.getAnnotationsByType(Anno.class)));

        // 获取内部类
        final Class<?>[] inners = clazz.getDeclaredClasses();
        System.out.println("全部内部类如下：");
        for(Class c : inners){
            System.out.println(c);
        }

        // 加载内部类
        Class inClazz = Class.forName("unit18_classload.c3.ClassTest$Inner");

        // 获取外部类
        System.out.println("inClazz 对应类的外部类为：" +
                inClazz.getDeclaredClasses());
        System.out.println("包：" + clazz.getPackage());
        System.out.println("父类：" + clazz.getSuperclass());
    }
}
