package unit08_collection.c83;

import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet nums = new TreeSet();
        // 向 TreeSet 添加四个 Integer 对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);
        // 输出是排序的
        System.out.println(nums);
        System.out.println(nums.first());
        // 输出小于5的子集
        System.out.println(nums.headSet(5));
        // 输出大于等于5的子集
        System.out.println(nums.tailSet(5));
        // 2=< x < 5
        System.out.println(nums.subSet(2, 5));
    }
}
