package unit08_collection.c83;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

enum Season {
    SPRING,SUMMER,FALL,WINNER
}
/**
 * EnumSet 基础用法
 */
public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet es = EnumSet.allOf(Season.class);
        // 四个
        System.out.println(es);
        EnumSet<Season> es2 = EnumSet.noneOf(Season.class);
        // 空
        System.out.println(es2);
        es2.add(Season.WINNER);
        es2.add(Season.SPRING);
        // 输出有序结果
        System.out.println(es2);
        // 刨除 es2 后剩下的值
        EnumSet<Season> es3 = EnumSet.complementOf(es2);
        System.out.println(es3);

        Collection c = new HashSet();
        c.add(Season.FALL);
        c.add(Season.SPRING);
        // 赋值，要求集合中都是同一个枚举中的值
        EnumSet enumSet = EnumSet.copyOf(c);
        System.out.println(enumSet);
        c.add("test");
        // 报错
        // EnumSet enumSet1 = EnumSet.copyOf(c);
    }
}
