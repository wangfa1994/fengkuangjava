package unit06_oo2.c65;

public abstract class Shape {
    {
        System.out.println("执行 Shape 的初始化块...");
    }
    private String color;
    // 定义一个计算周长的抽象方法
    public abstract double calPerimeter();
    // 定义一个返回形状的抽象方法
    public abstract String getType();
    // 定义构造器，用于被子类调用
    public Shape(){}
    public Shape(String color)
    {
        System.out.println("执行 Shape 的构造器...");
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
