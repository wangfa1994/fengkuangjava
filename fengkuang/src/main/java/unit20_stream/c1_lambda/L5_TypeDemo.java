package unit20_stream.c1_lambda;

/**
 * lambda 类型推断。
 */
interface IMath{
    int add(int x, int y);
}

public class L5_TypeDemo {
    public static void main(String[] args) {
        // 变量类型定义
        IMath lambda = (x, y) -> x + y;

        // 数组里
        IMath[] lambdas = {(x, y) -> x + y};

        // 强转
        Object lambda2 = (IMath)(x, y) -> x + y;

        // 通过返回类型
        IMath createLambda = createLambda();

        L5_TypeDemo demo = new L5_TypeDemo();
        // 当有二义性时，使用强转对应的接口解决
        demo.test((x,y) -> x + y);

    }

    public void test(IMath math){

    }

    public static IMath createLambda(){
        return (x,y) -> x+y;
    }
}
