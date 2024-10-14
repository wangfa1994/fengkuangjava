package unit08_collection.c86;

import java.util.EnumMap;

enum Season{
    SPRING,SUMMER,FALL,WINTER
}
public class EnumTest {
    public static void main(String[] args) {
        EnumMap enumMap = new EnumMap(Season.class);
        enumMap.put(Season.SUMMER, "夏日炎炎");
        enumMap.put(Season.SPRING, "春暖花开");
        // 春、夏
        System.out.println(enumMap);
    }
}
