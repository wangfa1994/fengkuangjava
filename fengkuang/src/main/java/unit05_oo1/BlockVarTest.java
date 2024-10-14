package unit05_oo1;

public class BlockVarTest {
    public static void main(String[] args) {
        {
            int a;
            // 下面代码将出现错误，a还未初始化
            // System.out.println("代码块局部变量a的值：" + a);
            a = 5;
            System.out.println("代码块局部变量a的值：" + a);
        }
        // a已经不存在
        // System.out.println("代码块局部变量a的值：" + a);
    }
}
