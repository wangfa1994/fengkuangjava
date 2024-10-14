package unit18_classload.c5.aop;

import java.lang.reflect.Proxy;

/**
 * @author alvin
 * @date 2020-05-04 23:43
 */
public class MyProxyFactotγ {
    // 为指定的 target 生成动态代理对象
    public static Object getProxy(Object target){
        // 创建一个 MyInvocationHandler 对象
        final MyInvocationHandler handler = new MyInvocationHandler();
        // 为 handler 设置 target 对象
        handler.setTarget(target);
        // 创建并返回一个动态代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
    }
}
