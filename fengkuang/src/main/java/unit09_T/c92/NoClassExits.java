package unit09_T.c92;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-23 23:48
 */
public class NoClassExits {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        // 并不存在泛型类，都是List 类型。true
        System.out.println(l1.getClass() == l2.getClass());
    }
}
