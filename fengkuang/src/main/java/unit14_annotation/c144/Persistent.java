package unit14_annotation.c144;

import java.lang.annotation.*;

/**
 * @author alvin
 * @date 2020-05-03 23:24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Persistent {
    String table();
}
