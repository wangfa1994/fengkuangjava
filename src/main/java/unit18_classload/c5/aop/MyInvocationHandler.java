package unit18_classload.c5.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author alvin
 * @date 2020-05-04 23:39
 */
public class MyInvocationHandler implements InvocationHandler {
    // 需要被代理的对象
    private Object target;
    public void setTarget(Object target){
        this.target = target;
    }
    // 执行动态代理对象的所有方法时，都会被替换成执行该方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final DogUtil du = new DogUtil();
        // 执行 DogUtil 对象中的 method1 方法
        du.method1();
        // 以 target 作为主调来执行 method 方法
        final Object result = method.invoke(target, args);
        // 执行 DogUtil 对象中的 method2 方法
        du.method2();
        return result;
    }
}
