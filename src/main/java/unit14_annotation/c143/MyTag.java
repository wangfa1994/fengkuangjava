package unit14_annotation.c143;

/**
 * @author alvin
 * @date 2020-05-03 23:12
 */
public @interface MyTag {
    // 定义带两个成员变量的注解
    String name() default "alvin";
    int age() default 26;
}
