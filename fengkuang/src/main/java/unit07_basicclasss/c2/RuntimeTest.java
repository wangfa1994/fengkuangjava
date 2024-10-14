package unit07_basicclasss.c2;

import java.io.IOException;

/**
 * @author alvin
 * @date 2020-05-24 8:43
 */
public class RuntimeTest {
    public static void main(String[] args) throws IOException {
        // 获取 Java 程序关联的运行时对象
        final Runtime rt = Runtime.getRuntime();
        System.out.println("处理器数量:" + rt.availableProcessors());
        System.out.println("空闲内存数:" + rt.freeMemory());
        System.out.println("总内存数量:" + rt.totalMemory());
        System.out.println("可用最大内存数:" + rt.maxMemory());

        // 运行记事本程序
        rt.exec("notepad.exe");
    }
}
