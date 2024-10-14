package unit14_annotation.c143.repeatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author alvin
 * @date 2020-05-04 10:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepTags {
    // 定义 value 成员变量，该成员变量可接收多个 @RepTag 注解
    RepTag[] value();
}
