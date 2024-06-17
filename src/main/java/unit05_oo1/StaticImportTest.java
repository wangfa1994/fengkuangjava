package unit05_oo1;

import static java.lang.System.*;
import static java.lang.Math.*;

public class StaticImportTest {
    public static void main(String[] args) {
        // out 是java.lang.System类的静态成员变量，代表标准输出
        // PI是 java.lang.Math 类的静态成员变量
        out.println(PI);
        // 直接调用Math类的sqrt静态方法
        out.println(sqrt(256));
    }
}
