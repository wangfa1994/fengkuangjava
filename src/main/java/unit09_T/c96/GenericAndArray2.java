package unit09_T.c96;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 23:07
 */
public class GenericAndArray2 {
    public static void main(String[] args) {
        List<?>[] lsa = new ArrayList<?>[10];
        Object[] oa = lsa;
        List<Integer> li = new ArrayList<>();
        li.add(3);
        oa[1] = li;
        Object target = lsa[1].get(0);
        if((target instanceof String)){
            // 下面代码安全了
            String s = (String) target;
        }
    }

//    <T> T[] makeArray(Collection<T> coll){
//        // 编译报错
//        return new T[coll.size()];
//    }
}
