package unit07_basicclasss.c3;

/**
 * @author alvin
 * @date 2020-05-24 9:27
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder();
        sb.append("java");
        sb.insert(0, "hello ");
        sb.replace(5, 6, ",");
        sb.delete(5, 6);
        System.out.println(sb); // hellojava
        // 反转
        sb.reverse(); // avajolleh
        System.out.println(sb);
        System.out.println(sb.length());
        System.out.println(sb.capacity()); // 16
        sb.setLength(5);
        System.out.println(sb);
    }
}
