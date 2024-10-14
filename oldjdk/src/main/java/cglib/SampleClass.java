package cglib;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SampleClass {

    // https://www.cnblogs.com/CarpenterLee/p/8241042.html
    //https://dzone.com/articles/cglib-missing-manual
    // https://mydailyjava.blogspot.com/2013/11/cglib-missing-manual.html


    /**
     * 继承代理，会代理Object 的 hashCode,toString, equals三个方法
     * 无法代理final方法，
     * 生成的字节码和代理类在同一个包中
     * FixedValue 拦截器所拦截的所有方法都会返回此值
     * InvocationHandler  拦截可能导致无限循环调用
     * MethodInterceptor
     *
     */

    public String test(String input) {
        return "Hello world!";
    }




    @Test
    public void testFixedValue() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib!";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
    }

    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
    }


    @Test
    public void testMethodInterceptor() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    return proxy.invokeSuper(obj, args);
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }

    @Test
    public void testCallbackFilter() throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib!";
                        };
                    };
                } else {
                    return NoOp.INSTANCE; // A singleton provided by NoOp.
                }
            }
        };
        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }


    class MyClass{

    }


}
