package unit10_exception.c102;

/**
 * @author alvin
 * @date 2020-05-02 22:05
 */
public class FinallyFlowTest {
    public static void main(String[] args) {
        boolean a = test();
        System.out.println(a);
    }
    public static boolean test() {
        try {
            // 因为 finally 中包含return，下面语句失效
            throw new RuntimeException("aaa");
            // return true;
        } finally {
            return false;
        }
    }
}
