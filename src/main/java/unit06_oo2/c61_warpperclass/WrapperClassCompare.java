package unit06_oo2.c61_warpperclass;

public class WrapperClassCompare {
    public static void main(String[] args) {
        // 虽然包装类型变量是引用数据类型，但包装类的实例可以与数值类型的值进行比较
        Integer a = Integer.valueOf(6);
        System.out.println("6的包装类实例是否大于5.0：" + (a > 5.0));

        // 两个包装类的实例进行比较的情况比较复杂，因为包装类的实例实际上是引用类型，只有两个包装类的引用
        // 指向同一个对象时才会返回true
        System.out.println("比较两个包装类的实例是否想等：" + (Integer.valueOf(2) == Integer.valueOf(2)));

        System.out.println("比较两个包装类的实例是否想等(127)：" + (Integer.valueOf(127) == Integer.valueOf(127)));
        System.out.println("比较两个包装类的实例是否想等(128)：" + (Integer.valueOf(128) == Integer.valueOf(128)));
        // 结果不同和Integer的设计有关，Integer把一128~127之间的整数自动装箱成 Integer 实例， 并放入了一个名为cache的数组中缓存起来
        // 不再-128~127之间的整数自动装箱为Integer时，系统会新建一个Integer实例

        // Java7为所有的包装类都提供了一个静态的 compare 方法，用于比较两个基本类型的大小
        System.out.println("========================包装类静态compare方法========================");
        System.out.println(Boolean.compare(true, false));
        System.out.println(Boolean.compare(false, false));

        System.out.println(Double.compare(3.14, 3.15));
        System.out.println(Double.compare(3.14, 3.14));

        // Character 提供了很多字符相关的工具方法，详见 API

    }
}
