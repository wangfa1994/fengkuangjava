package jdkcglib;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EchoProxy implements InvocationHandler {
    /**
    * @Desc :
    * @Author : Mr.WangF
    **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        // 保存生成的字节码文件
        addClassToDisk(proxy.getClass().getName(), proxy.getClass());

        System.out.println("进入代理开始========");

        if(method.getName().equals("echo") && method.getDeclaringClass().isAssignableFrom(EchoService.class)){
            EchoServiceImpl echoService = new EchoServiceImpl();
            System.out.println("进入了代理类中的方法，开始方法前逻辑");
            String echo = echoService.echo((String) args[0]);
            System.out.println("进入了代理类的方法，方法执行结束:"+echo);
            System.out.println("进入代理结束========");
            return echo;
        }

        return null;
    }

    private void addClassToDisk(String className, Class<?> cl) {
        //用于生产代理对象的字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
        FileOutputStream out = null;
        try {
            // jdk 动态代理会在项目的根目录下生成对应的class文件，$Proxy
            out = new FileOutputStream("$Proxy.class");
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
