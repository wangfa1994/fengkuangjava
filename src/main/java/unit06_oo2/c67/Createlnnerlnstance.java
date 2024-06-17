package unit06_oo2.c67;
class Out
{
    class In
    {
        public In(String msg)
        {
            System.out.println(msg);
        }
    }
}
public class Createlnnerlnstance {
    public static void main(String[] args) {
        Out.In in = new Out().new In("测试信息");
    }
}
