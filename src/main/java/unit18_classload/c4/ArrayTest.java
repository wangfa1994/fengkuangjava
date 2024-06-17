package unit18_classload.c4;

import java.lang.reflect.Array;

/**
 * @author alvin
 * @date 2020-05-04 22:32
 */
public class ArrayTest {
    public static void main(String[] args) {
        // 创建一个三位数组
        final Object arr = Array.newInstance(String.class, 3, 4, 10);
        // 获取 arr 种 index 为 2 的元素，该元素应该是一个二维数组
        final Object arrObj = Array.get(arr, 2);
        // 使用 Array 为二维数组的元素赋值。
        Array.set(arrObj, 2, new String[]{"aaa","bbb"});

        // 获取 arrObj 数组中 index 为 3 的元素，该元素是一维数组
        final Object anArr = Array.get(arrObj, 3);
        Array.set(anArr, 8, "888");
        // 将 arr 强制转换为三维数组
        String[][][] cast = (String[][][])arr;
        System.out.println(cast[2][2][0]);
        System.out.println(cast[2][2][1]);

        System.out.println(cast[2][3][8]);
    }
}
