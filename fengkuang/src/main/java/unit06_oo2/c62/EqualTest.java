package unit06_oo2.c62;

public class EqualTest {
    public static void main(String[] args) {
        int it = 65;
        float fl = 65.0f;
        // true
        System.out.println("65和65.0f是否相等？" + (it == fl));
        char ch = 'A';
        // true
        System.out.println("65和'A是否相等？" + (it == ch));
        String str1 = new String("hello");
        String str2 = new String("hello");
        // false
        System.out.println("str1 和 str2 是否相等？"
            + (str1 == str2));
        // true
        System.out.println("str1 是否 equals str2？"
            + (str1.equals(str2)));
        // String 与 EqualTest 类没有继承关系，编译报错
        // System.out.println("hello" == new EqualTest());
    }
}
