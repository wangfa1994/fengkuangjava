package unit18_classload.c5.aop;

/**
 * @author alvin
 * @date 2020-05-04 23:37
 */
public class DogUtil {
    // 第一个拦截器方法
    public void method1(){
        System.out.println("====模拟第一个通用方法====");
    }
    // 第二个拦截器方法
    public void method2(){
        System.out.println("====模拟第二个通用方法====");
    }
}
