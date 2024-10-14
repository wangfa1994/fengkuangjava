package unit14_annotation.c144;

import java.lang.annotation.*;

/**
 * @author alvin
 * @date 2020-05-03 23:26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Id {
    String column();
    String type();
    String generator();
}
