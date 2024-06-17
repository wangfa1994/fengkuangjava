package interview;

/**
 * 实现两个数组字符串进行十进制加法操作
 * Integer.parseInt(numberA.substring(aIndex, aIndex))
 */
public class Test02 {

    public static String add(String numberA, String numberB) {
        try {
            StringBuffer resultStr = new StringBuffer();
            // 从末尾开始扫描
            int aIndex = numberA.length() - 1;
            int bIndex = numberB.length() - 1;
            // 进位数
            int add = 0;
            while (aIndex >= 0 || bIndex >= 0 || add != 0) {
                // 长度不足补零
                int aVal = aIndex >= 0 ? Integer.parseInt(numberA.substring(aIndex, aIndex + 1)) : 0;
                int bVal = bIndex >= 0 ? Integer.parseInt(numberB.substring(bIndex, bIndex + 1)) : 0;
                int sumVal = aVal + bVal + add;
                resultStr.append(sumVal % 10);
                add = sumVal / 10;

                aIndex--;
                bIndex--;
            }
            return resultStr.reverse().toString();
        } catch (NumberFormatException e) {
            return "Error";
        }
    }

    public static void main(String[] args) {
        System.out.println(add("12345", "9876"));
        System.out.println(add("3", "4"));
        System.out.println(add("A", "1234454"));
    }
}
