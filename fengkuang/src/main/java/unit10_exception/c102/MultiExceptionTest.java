package unit10_exception.c102;

/**
 * @author alvin
 * @date 2020-05-02 21:44
 */
public class MultiExceptionTest {
    public static void main(String[] args) {
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a / b;
            System.out.println("您输入的两个数相除的结果是：" + c);
        } catch (IndexOutOfBoundsException|NumberFormatException
                |ArithmeticException ie){
            System.out.println("程序发生了数组越界、数字格式异常、算术异常之一");
            // 多异常有 final 修饰，编译报错
            // ie = new ArithmeticException("test");
        } catch (Exception e) {
            System.out.println("未知异常");
            // 正常
            e = new RuntimeException("test");
        }
    }
}
