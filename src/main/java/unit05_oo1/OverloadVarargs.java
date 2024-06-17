package unit05_oo1;

public class OverloadVarargs {
    public void test(String msg)
    {
        System.out.println("只有一个字符串参数的test方法");
    }
    public void test(String... books)
    {
        System.out.println("形参个数可变的test方法");
    }

    public static void main(String[] args) {
        OverloadVarargs olv = new OverloadVarargs();
        // 下面两次调用将执行第二个test方法
        olv.test();
        olv.test("aa","bb");
        // 调用第一个test方法
        olv.test("aa");
        // 调用第二个test方法
        olv.test(new String[]{"aa"});
    }
}
