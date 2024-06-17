package unit09_T.c91;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-23 22:43
 */
public class ListErr {
    public static void main(String[] args) {
        // 创建一个只想报讯字符串的 List
        List strList = new ArrayList();
        strList.add("111");
        strList.add("222");
        // 不小心把一个 Integer 对象丢进了集合
        strList.add(333);
        // 引发 ClassCastException
        strList.forEach(str -> System.out.println(((String)str).length()));
    }
}
