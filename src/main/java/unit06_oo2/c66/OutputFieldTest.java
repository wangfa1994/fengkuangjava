package unit06_oo2.c66;

public class OutputFieldTest {
    public static void main(String[] args) {
        System.out.println(Output.MAX_CACHE_LINE);
        // final 变量不可重新赋值
        // Output.MAX_CACHE_LINE = 20;
        // 使用接口调用类方法
        System.out.println(Output.staticTest());
    }
}
