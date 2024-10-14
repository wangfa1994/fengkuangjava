package unit05_oo1.c58;
class Animal
{
    private void beat()
    {
        System.out.println("心脏跳动...");
    }
    public  void breath()
    {
        beat();
        System.out.println("吸气，吐气，呼吸中...");
    }
}
class Bird extends Animal
{
    public void fly()
    {
        System.out.println("我在天空自在的飞翔...");
    }
}
class Wolf extends Animal
{
    public void run()
    {
        System.out.println("我在陆地自由的奔跑...");
    }
}
public class InheritTest {
    public static void main(String[] args) {
        Bird b = new Bird();
        b.breath();
        b.fly();
        Wolf w = new Wolf();
        w.breath();
        w.run();
    }
}
