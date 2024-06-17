package unit14_annotation.c143;

import java.lang.annotation.*;

/**
 * @author alvin
 * @date 2020-05-04 9:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Testable {
}
