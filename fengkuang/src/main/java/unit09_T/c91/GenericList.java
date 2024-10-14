package unit09_T.c91;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-23 22:49
 */
public class GenericList {
    public static void main(String[] args) {
        // 创建一个只想报讯字符串的 List
        List<String> strList = new ArrayList<String>();
        strList.add("111");
        strList.add("222");
        // 编译报错
        // strList.add(333);
        // 不需要强转了
        strList.forEach(str -> System.out.println(str.length()));
    }
}
