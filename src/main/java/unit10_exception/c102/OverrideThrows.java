package unit10_exception.c102;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author alvin
 * @date 2020-05-03 14:05
 */
public class OverrideThrows {
    public void test() throws IOException {
        final FileInputStream fis = new FileInputStream("a.txt");
    }
}
class Sub extends OverrideThrows {
    // 子类方法声明抛出了比父类方法更大的异常
    // 所以下面方法出错
//    public void test() throws Exception {
//        super.test();
//    }
}
