package unit14_annotation.c141;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author alvin
 * @date 2020-05-03 22:07
 */
public class ErrorUtils {
    @SafeVarargs
    public static void faultyMethod(List<String>... listStrArray){
        // Java 语言不允许创建泛型数组，因此 listStrArray 只能被当成 List[] 处理
        // 此时发生堆污染
        List[] listArray = listStrArray;
        final ArrayList<Integer> myList = new ArrayList<>();
        myList.add(new Random().nextInt(100));
        listArray[0] = myList;
//        final String s = listStrArray[0].get(0);
    }
}
