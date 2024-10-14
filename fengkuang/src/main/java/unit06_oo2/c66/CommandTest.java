package unit06_oo2.c66;

public class CommandTest {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        // 第一次处理数组，具体行为取决于PrintCommand
        pa.process(target, new PrintCommand());
        System.out.println("=================");
        pa.process(target, new AddCommand());
    }
}
