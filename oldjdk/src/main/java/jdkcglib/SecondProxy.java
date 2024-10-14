package jdkcglib;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SecondProxy implements InvocationHandler {

    private Object target;

    public SecondProxy(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入二层代理开始========");

        // 保存生成的字节码文件
        addClassToDisk(proxy.getClass().getName(), proxy.getClass());

        Object result = method.invoke(target, args);

        System.out.println("进入二层代理结束========");
        return result;
    }

    private void addClassToDisk(String className, Class<?> cl) {
        //用于生产代理对象的字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
        FileOutputStream out = null;
        try {
            // jdk 动态代理会在项目的根目录下生成对应的class文件，$Proxy
            out = new FileOutputStream("$SecondProxy.class");
            //将代理对象的class字节码写到硬盘上
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
