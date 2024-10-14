package unit06_oo2.c66;

public class PrintCommand implements Command{
    public void process(int[] target)
    {
        for(int tmp : target)
        {
            System.out.println("迭代输出：" + tmp);
        }
    }
}
