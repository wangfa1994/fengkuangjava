package unit05_oo1.c57;

public class ConversionTest {
    public static void main(String[] args) {
        double d = 13.4;
        long l = (long)d;
        System.out.println(l);
        int in = 5;
        // 编译报错，数值类型变量不可转换为boolean类型
        // boolean b = (boolean)in;
        Object obj = "Hello";
        String objStr = (String)obj;
        System.out.println(objStr);

        // 编译类型为Object，实际类型为Integer
        Object objPri = Integer.valueOf(5);
        // 引发ClassCastException 异常
        String str = (String)objPri;
    }
}
