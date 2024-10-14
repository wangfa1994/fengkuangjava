package cglib;

import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class EchoProxyMain {


    public static void main(String[] args) {

        firstEnhancer();

        //secondEnhancer();
    }

    private static void firstEnhancer() {
        Enhancer enhancer = new Enhancer(); //Enhancer 相当于jdk代理中的Proxy
        enhancer.setSuperclass(SampleClass.class);
        // 进行回调
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("hello cglib loadObject");
                return "loadObject method callback!";
            }
        });

       /* // 进行文件保存
        enhancer.setStrategy(new DefaultGeneratorStrategy() {
            protected byte[] transform(byte[] b) {
                addClassToDisk(b);
                return b;

            }});*/

        SampleClass proxy = (SampleClass) enhancer.create();
        // System.out.println(proxy.hashCode()); // 报错，因为FixedValue 拦截的总会返回一个String对象 而hashCode则需要number
        System.out.println(proxy.test(null));
    }



    private static void secondEnhancer() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);

        FixedValue helloCglibLoadObject = new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("hello cglib loadObject");
                return "loadObject method callback!";
            }
        };

        InvocationHandler helloCglibInvoke = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("hello cglib invoke");
                return "invoke method callback";
            }

            ;
        };

        // 进行回调
        enhancer.setCallbacks(new Callback[]{helloCglibLoadObject,helloCglibInvoke});
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));


    }

    private static void addClassToDisk(byte[] b) {

        FileOutputStream out = null;
        try {
            // jdk 动态代理会在项目的根目录下生成对应的class文件，$Proxy
            out = new FileOutputStream("$CglibProxy.class");
            //将代理对象的class字节码写到硬盘上
            out.write(b);
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
