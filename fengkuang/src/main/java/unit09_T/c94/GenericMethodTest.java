package unit09_T.c94;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 21:39
 */
public class GenericMethodTest {
    static <T> void fromArrayToCollection(T[] a, Collection<T> c){
        for(T o : a){
            c.add(o);
        }
    }

    static <T> void test(Collection<T> from, Collection<T> to){
        for (T ele : from){
            to.add(ele);
        }
    }

    static <T> void test1(Collection<? extends T> from, Collection<T> to){
        for (T ele : from){
            to.add(ele);
        }
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        // T:Object
        fromArrayToCollection(oa, co);

        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        // T:String
        fromArrayToCollection(sa, cs);
        // T:Object？？
        fromArrayToCollection(sa, co);

        List<Object> ao = new ArrayList<>();
        List<String> as = new ArrayList<>();
        // 编译报错？
        // test(as, ao);
        // 正常
        test1(as, ao);
    }
}
