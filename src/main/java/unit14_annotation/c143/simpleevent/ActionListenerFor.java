package unit14_annotation.c143.simpleevent;

import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author alvin
 * @date 2020-05-04 9:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ActionListenerFor {
    // 定义一个成员变量，用于设置元数据
    // 该 listener 成员变量用于保存监听器实现类
    Class<? extends ActionListener> listener();
}
