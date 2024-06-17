package unit07_basicclasss.c2;

/**
 * @author alvin
 * @date 2020-05-24 8:30
 */
public class IdentityHashCodeTest {
    public static void main(String[] args) {
        // s1 s2 是两个不同的对象
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        // 因为 String 重写了 hashCode() 方法--改为根据字符序列计算 hashCode
        // 所以 s1 s2 hashCode() 相同
        System.out.println(s1.hashCode() + "----" + s2.hashCode());
        // s1 s2 是不同的对象,所以 identityHashCode 不同
        System.out.println(System.identityHashCode(s1) + "----" +
                System.identityHashCode(s2));
        String s3 = "Java";
        String s4 = "Java";
        System.out.println(System.identityHashCode(s3) + "----" +
                System.identityHashCode(s4));
    }
}
