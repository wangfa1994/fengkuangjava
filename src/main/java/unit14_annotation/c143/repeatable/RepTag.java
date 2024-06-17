package unit14_annotation.c143.repeatable;

import java.lang.annotation.*;

/**
 * @author alvin
 * @date 2020-05-04 10:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(RepTags.class)
public @interface RepTag {
    // 为该注解定义两个成员变量
    String name() default "alvin";
    int age() default 26;
}
