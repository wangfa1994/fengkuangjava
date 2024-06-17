package unit09_T.c93;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 20:54
 */
public class CommonSymbol {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        // 编译报错
//        test(strList);

        // 正常
        test1(strList);

        List<?> c = new ArrayList<>();
        // 编译报错
//        c.add(new Object());
        // 正常，因为 null 是所有引用类型的实例
        c.add(null);
    }

    public static void test(List<Object> c){
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i));
        }
    }

    public static void test1(List<?> c){
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i));
        }
    }
}
