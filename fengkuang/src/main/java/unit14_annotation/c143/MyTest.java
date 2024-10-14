package unit14_annotation.c143;

/**
 * @author alvin
 * @date 2020-05-04 9:06
 */
public class MyTest {
    // 使用 @Testable 注解指定该方法是可测试的
    @Testable
    public static void m1(){}
    public static void m2(){}
    @Testable
    public static void m3(){
        throw new IllegalArgumentException("参数出错了！");
    }
    private static void m4(){}
    @Testable
    public static void m5(){}
    public static void m6(){}
    @Testable
    public static void m7(){
        throw new RuntimeException("程序业务出现异常！");
    }
    public static void m8(){}
}
