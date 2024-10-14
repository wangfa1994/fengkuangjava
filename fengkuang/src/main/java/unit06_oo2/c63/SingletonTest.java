package unit06_oo2.c63;

class Singleton {
    // 使用一个类变量来缓存曾经创建的实例
    private static Singleton instance;
    // 隐藏构造器
    private Singleton(){}
    // 提供一个静态方法，返回Singleton对象
    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
}
public class SingletonTest
{
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
