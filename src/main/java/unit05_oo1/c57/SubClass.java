package unit05_oo1.c57;

class BaseClass
{
    public int book = 6;
    public void base()
    {
        System.out.println("父类的普通方法");
    }
    public void test()
    {
        System.out.println("父类的被覆盖的方法");
    }
}
public class SubClass extends BaseClass{
    // 重新定义一个book实例变量隐藏父类的book实例变量
    public String book = "轻量级 Java EE企业应用实战";
    public void sub()
    {
        System.out.println("子类的普通方法");
    }
    @Override
    public void test()
    {
        System.out.println("子类的覆盖父类的方法");
    }
    public static void main(String[] args) {
        // 编译时类型和运行时类型完全一样，不存在多态
        BaseClass bc = new BaseClass();
        // 6
        System.out.println(bc.book);
        bc.base();
        bc.test();
        // 编译时类型和运行时类型完全一样，不存在多态
        SubClass sc = new SubClass();
        System.out.println(sc.book);
        sc.base(); // 执行从父类继承的方法
        sc.test(); // 执行当前类方法
        // 编译时类型和运行时类型不一样，存在多态
        BaseClass ploymophicBc = new SubClass();
        // 6，表明对象的实例变量则不具备多态性
        System.out.println(ploymophicBc.book);
        ploymophicBc.base(); // 执行从父类继承的方法
        ploymophicBc.test(); // 执行当前类方法
        // 因为 ploymophicBC 的编译时类型是BaseClass
        // ploymophicBc.sub();

    }
}
