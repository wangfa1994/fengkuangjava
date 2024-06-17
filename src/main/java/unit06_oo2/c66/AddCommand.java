package unit06_oo2.c66;

public class AddCommand implements Command {
    public void process(int[] target)
    {
        int sum = 0;
        for(int tmp : target)
        {
            sum += tmp;
        }
        System.out.println("数组元素总和是：" + sum);
    }
}
