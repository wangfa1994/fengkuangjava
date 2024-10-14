package unit09_T.c95;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 22:53
 */
public class ErasureTest2 {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>();
        li.add(6);
        li.add(9);
        List list = li;
        // 只是警告，运行时完全正常
        List<String> ls = list;
        // 但是访问元素时会引发运行时异常。
        System.out.println(ls.get(0));
    }
}
