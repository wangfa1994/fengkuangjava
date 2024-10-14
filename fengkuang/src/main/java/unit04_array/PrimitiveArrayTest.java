package unit04_array;

public class PrimitiveArrayTest {
    public static void main(String[] args) {
        // 定义一个数组变量
        int[] iArr;
        // 动态初始化数组，数组长度为 5
        iArr = new int[5];
        // 为数组元素赋值
        for(int i=0;i<iArr.length; i++){
            iArr[i] = i + 10;
        }
    }
}
