package unit04_array;

public class TwoDimensionTest {
    public static void main(String[] args) {
        // 定义一个二位数组
        int[][] a;
        // 把 a 当成以位数组进行初始化，初始化 a 是一个长度为 4 的数组
        a = new int[4][];
        // 把 a 当成一维数组，遍历 a 数组
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
        // 初始化 a 数组第一个元素
        a[0] = new int[2];
        // 访问 a 数组第一个元素所指数组的第二个元素
        a[0][1] = 6;
        // a 数组的第一个元素是一个一维数组，遍历这个数组
        for(int i=0; i<a[0].length; i++){
            System.out.println(a[0][i]);
        }
    }
}
