package unit18_classload.c5.aop;

/**
 * @author alvin
 * @date 2020-05-04 23:46
 */
public class Test {
    public static void main(String[] args) {
        // 创建一个原始的 GunDog 对象，作为 target
        final Dog target = new GunDog();
        // dog 实际上是动态代理对象，只是该动态代理对象也实现了 Dog 接口
        Dog dog = (Dog)MyProxyFactotγ.getProxy(target);
        dog.info();
        dog.run();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}
