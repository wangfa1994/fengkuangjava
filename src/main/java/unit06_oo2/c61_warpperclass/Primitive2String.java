package unit06_oo2.c61_warpperclass;

/**
 * 基本类型和String互相转换
 */
public class Primitive2String {
    public static void main(String[] args) {
        String intStr = "123";
        // 字符串转int
        int it1 = Integer.parseInt(intStr);
        int it2 = Integer.valueOf(intStr);
        System.out.println(it2);
        String floatStr = "4.56";
        // 字符串转float
        float ft1 = Float.parseFloat(floatStr);
        float ft2 = Float.valueOf(floatStr);
        System.out.println(ft2);

        // float转string
        String ftStr = String.valueOf(2.345f);
        // double转string
        String dbStr = String.valueOf(3.345);
        // boolean转String
        String boolStr = String.valueOf(true);
        System.out.println(boolStr.toUpperCase());

    }
}
