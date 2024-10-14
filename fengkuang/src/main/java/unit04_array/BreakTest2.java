package unit04_array;

public class BreakTest2 {
    public static void main(String[] args) {
        // 外层循环， outer 作为标识符
        outer:
        for(int i=0; i<5; i++){
            // 内层循环
            for(int j=0; j<3; j++){
                System.out.println("i 的值为：" + i + " j 的值为：" + j);
                if(j == 1){
                    // 跳出 outer 标签所表示的循环
                    break outer;
                }
            }
        }
    }
}
