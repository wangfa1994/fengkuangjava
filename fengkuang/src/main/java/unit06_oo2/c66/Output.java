package unit06_oo2.c66;

public interface Output {
    // 接口里定义的成员变量只能是常量
    int MAX_CACHE_LINE = 50;
    // 接口里定义的普通方法只能是 public 的抽象方法
    void out();
    void getData(String msg);
    // 接口中定义默认方法，需要使用 default 修饰
    default void print(String... msgs)
    {
        for(String msg : msgs)
        {
            System.out.println(msg);
        }
    }
    default void test()
    {
        System.out.println("默认的test方法");
    }
    // 接口中定义类方法，需要使用 static 修饰
    static String staticTest()
    {
        return "接口里的类方法";
    }
    // 接口中定义私有方法
    private void foo()
    {
        System.out.println("foo 私有方法");
    }
    // 接口中定义私有静态方法
    private static void bar()
    {
        System.out.println("bar 私有静态方法");
    }
}
