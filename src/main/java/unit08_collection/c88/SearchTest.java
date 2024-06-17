package unit08_collection.c88;

import java.util.ArrayList;
import java.util.Collections;

public class SearchTest {
    public static void main(String[] args) {
        ArrayList nums = new ArrayList();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        // 2 -5 3 0
        System.out.println(nums);
        // 3
        System.out.println(Collections.max(nums));
        Collections.replaceAll(nums, 0, 1);
        // 2 -5 3 1
        System.out.println(nums);
        // 1
        System.out.println(Collections.frequency(nums, -5));
        Collections.sort(nums);
        // -5 1 2 3
        System.out.println(nums);
        // 3
        System.out.println(Collections.binarySearch(nums, 3));
    }
}
