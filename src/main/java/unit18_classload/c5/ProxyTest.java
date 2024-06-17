package unit18_classload.c5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author alvin
 * @date 2020-05-04 23:21
 */
interface Person{
    void walk();
    void sayHello(String name);
}
class MyInvocationHandler implements InvocationHandler {

    /**
     * 执行动态代理对象的所有方法时，都会被替换成执行如下的 invoke 方法
     * proxy：动态代理对象
     * method：正在执行的方法
     * args：方法实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("======>正在执行的方法：" + method);
        if(args != null) {
            System.out.println("传入的实参：");
            for (Object val : args) {
                System.out.println(val);
            }
        } else {
            System.out.println("没有实参");
        }
        return null;
    }
}
public class ProxyTest {
    public static void main(String[] args) {
        // 创建一个 InvocationHandler 对象
        final MyInvocationHandler handler = new MyInvocationHandler();
        // 使用指定的 InvocationHandler 来生成一个动态代理对象
        Person p = (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(), new Class[]{Person.class}, handler);
        // 调用动态代理对象的 walk 和 sayHello 方法
        p.walk();
        p.sayHello("alvin");
    }
}
