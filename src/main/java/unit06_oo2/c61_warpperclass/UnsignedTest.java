package unit06_oo2.c61_warpperclass;

public class UnsignedTest {
    public static void main(String[] args) {
        byte b = -3;
        System.out.println("byte 类型的-3对应的无符号整数：" + Byte.toUnsignedInt(b));
        System.out.println("指定使用16进制解析无符号整数：" + Integer.parseUnsignedInt("ab", 16));
        // 将-12 转换为无符号int 型，然后转换为十六进制的字符串
        System.out.println(Integer.toUnsignedString(-12, 16));
        System.out.println(Integer.divideUnsigned(-2, 3));
        System.out.println(Integer.remainderUnsigned(-2, 7));
    }
}
