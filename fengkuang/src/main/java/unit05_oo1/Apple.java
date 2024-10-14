package unit05_oo1;

public class Apple {
    public String name;
    public String color;
    public double weight;
    public Apple(){}
    public Apple(String name, String color)
    {
        this.name = name;
        this.color = color;
    }
    public Apple(String name, String color, double weight)
    {
        // 调用两个参数的构造器
        this(name, color);
        this.weight = weight;
    }
}
