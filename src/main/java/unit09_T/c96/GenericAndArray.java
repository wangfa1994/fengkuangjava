package unit09_T.c96;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 22:59
 */
public class GenericAndArray {
    public static void main(String[] args) {
        // 编译报错
        // List<String>[] lsa = new ArrayList<String>[10];
        // 这样是允许的，但是会有警告
        List<String>[] lsa = new ArrayList[10];
        // 向上转型
        Object[] oa = lsa;
        List<Integer> li = new ArrayList<>();
        li.add(3);
        oa[1] = li;
        // 没有任何警告，但是将引发异常
        String s = lsa[1].get(0);
    }
}
