package unit14_annotation.c141;

import java.util.Arrays;

/**
 * @author alvin
 * @date 2020-05-03 22:13
 */
public class ErrorUtilsTest {
    public static void main(String[] args) {
        ErrorUtils.faultyMethod(Arrays.asList("hello!"),
                Arrays.asList("world!"));
    }
}
