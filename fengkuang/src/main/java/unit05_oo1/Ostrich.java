package unit05_oo1;

public class Ostrich extends Bird {
    // 重写 Bird 类的 fly() 方法
    public void fly()
    {
        System.out.println("我只能在地上奔跑...");
    }

    public void callOverrideMethod()
    {
        // 在子类方法中通过super显示调用父类被覆盖的实例方法
        super.fly();
    }

    public static void main(String[] args) {
        Ostrich os = new Ostrich();
        os.fly();
        os.callOverrideMethod();
    }
}
