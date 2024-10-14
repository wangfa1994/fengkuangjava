package unit14_annotation.c143.simpleevent;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * @author alvin
 * @date 2020-05-04 9:51
 */
public class ActionListenerInstaller {
    // 处理注解的方法，其中 obj 是包含注解的对象
    public static void processAnnotations(Object obj) {
        try {
            // 获取 obj 对象
            Class cl = obj.getClass();
            // 获取指定 obj 对象的所有成员变量，并遍历每个成员变量
            for(Field f : cl.getDeclaredFields()) {
                // 将成员变量设置成可以自由访问
                f.setAccessible(true);
                // 获取该成员变量上 ActionListenerFor 类型注解
                ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
                // 获取成员变量 f 的值
                Object fObj = f.get(obj);
                // 如果 f 是 AbstractButton 的实例，且 a 不为 null
                if(a != null && fObj != null && fObj instanceof AbstractButton) {
                    // 获取 a 注解里的 listener 元数据（它是一个监听器类）
                    Class<? extends ActionListener> listenerClazz = a.listener();
                    // 使用反射来创建 listener 类对象
                    ActionListener al = listenerClazz.newInstance();
                    AbstractButton ab = (AbstractButton) fObj;
                    // 为 ab 按钮添加事件监听器
                    ab.addActionListener(al);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
