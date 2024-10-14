package unit04_array.practice;

public class Circle{

    public static void main(String[] args) {
        circle(15); // 输出半径为5的圆
    }

    public static void circle(int r) {
        for (int y = 0; y <= 2 * r; y += 2) {
            int x = (int) Math.round(r - Math.sqrt(2 * r * y - y * y));
            int len = 2 * (r - x);
            // 圆左的空白
            for (int i = 0; i <= x; i++) {
                System.out.print(' ');
            }
            // 左半圆
            System.out.print('*');
            // 中间空白
            for (int j = 0; j <= len; j++) {
                System.out.print(' ');
            }
            // 右半圆
            System.out.println('*');
        }
    }
}
