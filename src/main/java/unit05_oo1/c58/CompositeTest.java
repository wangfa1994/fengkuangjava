package unit05_oo1.c58;
class Animal1
{
    private void beat()
    {
        System.out.println("心脏跳动...");
    }
    public void breath()
    {
        beat();
        System.out.println("吸气，吐气，呼吸中...");
    }
}
class Bird1
{
    // 将原来的父类组合到原来的子类中，作为子类的一个组合成分
    private Animal1 a;
    public Bird1(Animal1 a)
    {
        this.a = a;
    }
    public void breath()
    {
        a.breath();
    }
    public void fly()
    {
        System.out.println("我在天空自在的飞翔...");
    }
}
class Wolf1
{
    private Animal1 a;
    public Wolf1(Animal1 a)
    {
        this.a = a;
    }
    public void breath()
    {
        a.breath();
    }
    public void run()
    {
        System.out.println("我在地上快速的奔跑...");
    }
}
public class CompositeTest {
    public static void main(String[] args) {
        Animal1 a1 = new Animal1();
        Bird1 b = new Bird1(a1);
        b.breath();
        b.fly();
        Animal1 a2 = new Animal1();
        Wolf1 w = new Wolf1(a2);
        w.breath();
        w.run();
    }
}
