package unit06_oo2.c64_final;

public class PrivateFinalMethodTest
{
    private final void test1(){}

    public final void test2(){}
}
class Sub extends  PrivateFinalMethodTest
{
    // 合法，只是定义了一个新方法，不是重写父类方法
    public void test1(){};
    // 非法
    // public void test2(){};
}
class FinalOverload
{
    public final void test() {}
    // final 修饰的方法，只是不能被重写，而不是不能被重载。合法
    public final void test(String org) {}
}
