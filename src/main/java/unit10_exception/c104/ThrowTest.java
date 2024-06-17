package unit10_exception.c104;

/**
 * @author alvin
 * @date 2020-05-03 14:15
 */
public class ThrowTest {
    public static void main(String[] args) {
        try {
            // 调用声明抛出 Checked 异常的方法，要么显示捕获
            // 要么在 main 方法中再次声明抛出
            throwChecked(-3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 调用声明抛出 Runtime 异常的方法既可以显示捕获
        // 也可以不理会
        throwRuntime(3);
    }
    public static void throwChecked(int a) throws Exception {
        if(a>0) {
            // 自行抛出 Exception 异常
            // 该代码必须处于 try 块里，或处于带 throws 声明的方法中
            throw new Exception("throwChecked a 的值大于 0，不符合要求");
        }
    }
    public static void throwRuntime(int a) {
        if(a>0) {
            // 自行抛出 RuntimeException 异常，既可以显示捕获
            // 也可以不理会该异常，把异常交给方法调用者处理
            throw new RuntimeException("throwRuntime a 的值大于 0，不符合要求");
        }
    }
}
