package unit09_T.c93;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 21:17
 */
public class MyUtils {
    public static <T> T copy(Collection<? super T> dest, Collection<T> src){
        T last = null;
        for(T ele : src){
            last = ele;
            // 逆变的泛型集合添加元素是安全的
            dest.add(ele);
        }
        return last;
    }

    // 泛型方法重载可能导致问题
//    public static <T> T copy(Collection<T> dest, Collection<? extends T> src){
//        T last = null;
//        for(T ele : src){
//            last = ele;
//            // 逆变的泛型集合添加元素是安全的
//            dest.add(ele);
//        }
//        return last;
//    }

    public static void main(String[] args) {
        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        li.add(5);
        Integer last = copy(ln, li);
        System.out.println(ln);
    }
}
