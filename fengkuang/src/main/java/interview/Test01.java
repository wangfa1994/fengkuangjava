package interview;

/**
 * 判断第二个数组是否为第一个数组的子数组（子数组连续对比）
 * 如果不是返回-1；如果是返回在第一个数组中的最大下标
 */
public class Test01 {

    public static int subArr(int[] A, int[] B) {
        // 子数组最大下标
        int resultIndex = -1;

        // (A.length - B.length) 后，剩余长度小于B数组长度，肯定不会匹配
        for (int i = 0; i < A.length - B.length; i++) {
            for (int j = 0; j < B.length; j++) {
                // i为有效偏移量起始位置
                if (A[i + j] != B[j]) {
                    break;
                }
                // B数组全部匹配，找到目标
                if (j == B.length -1) {
                    resultIndex = i;
                }
            }
        }

        return resultIndex;
    }

    public static void main(String[] args) {
        System.out.println(subArr(new int[]{4,5,6,7,5,6,8}, new int[]{5,6}));
        System.out.println(subArr(new int[]{4,5,7,5,8}, new int[]{5,6}));
        System.out.println(subArr(new int[]{4,5,6,7,5,6,8}, new int[]{6}));
        System.out.println(subArr(new int[]{4,5,6,7,5,6,8}, new int[]{4,5,6}));
    }
}
