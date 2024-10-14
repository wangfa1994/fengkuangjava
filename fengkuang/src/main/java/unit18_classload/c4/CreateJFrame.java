package unit18_classload.c4;

import java.lang.reflect.Constructor;

/**
 * @author alvin
 * @date 2020-05-04 21:52
 */
public class CreateJFrame {
    public static void main(String[] args) throws Exception {
        // 获取 JFrame 对象的 Class 对象
        final Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
        // 获取 JFrame 中带一个字符串参数的构造器
        Constructor ctor = jframeClazz.getConstructor(String.class);
        final Object obj = ctor.newInstance("测试窗口");
        System.out.println(obj);
    }
}
