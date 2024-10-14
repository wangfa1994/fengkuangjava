package unit18_classload.c1;

/**
 * @author alvin
 * @date 2020-05-04 14:01
 */
class MyTest {
    static {
        System.out.println("静态初始化块...");
    }
    // 使用一个字符串直接量 准备验证解析
    static final String compileConstant = "疯狂Java讲义";
    static String a = "a";
    static String b = "b";
    static String c = a + b;
}
public class CompileConstantTest {
    public static void main(String[] args) {
        // 访问类变量，不会触发初始化。因为是宏变量
        System.out.println(MyTest.compileConstant);
        // 会触发初始化
        System.out.println(MyTest.c);
    }
}
