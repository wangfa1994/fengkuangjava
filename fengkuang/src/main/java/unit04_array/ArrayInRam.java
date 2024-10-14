package unit04_array;

public class ArrayInRam {
    public static void main(String[] args) {
        // 定义并初始化数组，使用静态初始化
        int[] a = {5,7,20};
        // 定义并初始化数组，使用动态初始化
        int[] b = new int[4];
        System.out.println("b 数组长度：" + b.length);
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
        for(int i=0; i<b.length; i++){
            System.out.println(b[i]);
        }
        // 因为 a 是 int[] 类型，b 也是 int[] 类型，所以可以将 a 赋值给 b
        // 也就是让 b 引用指向 a 引用指向的数组
        b = a;
        System.out.println("b 数组长度：" + b.length);
    }
}
