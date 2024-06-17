package unit06_oo2.c68;

import unit06_oo2.c66.ProcessArray;

public class CommandTest2 {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] array = {3, -4, 6, 4};
        pa.process(array, (int[] target)->{
            int sum = 0;
            for(int tmp : target)
            {
                sum += tmp;
            }
            System.out.println("数组元素之和：" + sum);
        });
    }
}
