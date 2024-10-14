package jdkcglib;

import java.lang.reflect.Proxy;

public class EchoProxyMain {

    public static void main(String[] args) {

        EchoService proxyInstance = (EchoService)Proxy.newProxyInstance(EchoProxyMain.class.getClassLoader(), new Class[]{EchoService.class}, new EchoProxy());

        //String echo = proxyInstance.echo("hello");


        // 进行二层代理
        //EchoService secondProxy = (EchoService)Proxy.newProxyInstance(EchoProxyMain.class.getClassLoader(), new Class[]{EchoService.class}, new SecondProxy(proxyInstance));
        EchoService secondProxy = (EchoService)Proxy.newProxyInstance(EchoProxyMain.class.getClassLoader(), proxyInstance.getClass().getInterfaces(), new SecondProxy(proxyInstance));

        String echo2 = secondProxy.echo("hello");
        System.out.println("进行了二次代理:"+echo2);

    }
}
