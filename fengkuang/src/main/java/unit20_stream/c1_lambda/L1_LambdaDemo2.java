package unit20_stream.c1_lambda;

/**
 * 函数接口，只有一个方法，就是一个接口只做一件事情的意思。告诉用户不要再加其他接口方法了
 */
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);
    /**
     * 默认方法。函数接口是指只有一个需要实现的方法，而不是接口中只有一个方法。
     * 很重要的特性。1.8以前，接口中不敢随意增加方法，因为加了方法后，对该接口的所有实现都有影响。
     */
    default int add(int x, int y){
        return x + y;
    }
}

@FunctionalInterface
interface Interface2{
    int doubleNum(int i);
    default int add(int x, int y){
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface2, Interface1 {
    /**
     * 函数接口使用默认方法需要注意的地方，当多个接口有相同的默认方法时，需要指定使用哪个
     */
    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x,y);
    }
}

/**
 * lambda常用写法
 */
public class L1_LambdaDemo2 {
    public static void main(String[] args) {
        // 其实就是 doubleNum 方法的实现
        Interface1 i1 = (i) -> i*2;

        System.out.println(i1.add(3,7));
        System.out.println(i1.doubleNum(20));

        // 这种是最常见写法
        Interface1 i2 = i -> i*2;

        Interface1 i3 = (int i) -> i*2;

        Interface1 i4 = (int i) -> {
            System.out.println("-------");
            return i*2;
        };
        System.out.println(i4.doubleNum(11));
    }
}
