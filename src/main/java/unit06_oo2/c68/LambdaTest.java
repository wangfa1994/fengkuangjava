package unit06_oo2.c68;

public class LambdaTest {
    // Runnable 接口中只包含一个无参数的方法
    // 下面的Lambda表达式创建了一个Runnable对象
    Runnable r = () ->{
        for(int i=0;i<100;i++)
        {
            System.out.println();
        }
    };

    // Object 不是函数接口
//    Object obj = () ->{
//        for(int i=0;i<100;i++)
//        {
//            System.out.println();
//        }
//    };

    // 强转
        Object obj = (Runnable)() ->{
        for(int i=0;i<100;i++)
        {
            System.out.println();
        }
    };
}
