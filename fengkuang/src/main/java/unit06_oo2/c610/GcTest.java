package unit06_oo2.c610;

public class GcTest {
    public static void main(String[] args)
    {
        for (int i = 0; i<4; i++)
        {
            new GcTest();
            //System.gc();
            // Runtime.getRuntime().gc();
        }
        System.gc();
    }
    @Override
    public void finalize()
    {
        System.out.println("系统正在清理 GcTest 对象的资源...");
    }
}
