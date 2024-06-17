package unit14_annotation.c142;

import java.lang.annotation.*;

/**
 * @author alvin
 * @date 2020-05-03 22:38
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Inheritable {
}
