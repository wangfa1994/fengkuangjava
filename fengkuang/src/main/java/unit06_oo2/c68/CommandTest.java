package unit06_oo2.c68;

import unit06_oo2.c66.Command;
import unit06_oo2.c66.ProcessArray;

public class CommandTest {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        // 处理数组，具体处理行为取决于匿名内部类
        pa.process(target, new Command() {
            @Override
            public void process(int[] target) {
                int sum = 0;
                for(int tmp : target)
                {
                    sum += tmp;
                }
                System.out.println("数组元素之和："+sum);
            }
        });
    }
}
