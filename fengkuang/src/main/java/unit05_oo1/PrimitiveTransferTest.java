package unit05_oo1;

public class PrimitiveTransferTest {
    public static void swap(int a, int b)
    {
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println("swap方法里，a="+a+";b="+b);
    }
    public static void main(String[] args) {
        int a = 6;
        int b = 9;
        swap(a, b);
        System.out.println("交换后，a="+a+";b="+b);
    }
}
