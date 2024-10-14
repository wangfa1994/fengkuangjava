package unit06_oo2.c62;

public class StringCompareTest {
    public static void main(String[] args) {
        // s1 直接引用常量池中的"疯狂Java"
        String s1 = "疯狂Java";
        String s2 = "疯狂";
        String s3 = "Java";
        // s4 可以在编译时就确定下来，直接引用常量池
        String s4 = "疯狂" + "Java";
        // 同上
        String s5 = "疯" + "狂" + "Java";
        // 编译时无法确定，不能引用常量池
        String s6 = s2 + s3;
        // 使用new调用构造器会创建一个新的String对象
        // s7引用堆内存中新创建的String对象
        String s7 = new String("疯狂Java");
        System.out.println(s1 == s4);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s7);
    }
}
