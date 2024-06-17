package unit06_oo2.c65;

public class Triangle extends Shape {
    // 定义三角形三边
    private double a;
    private double b;
    private double c;
    public Triangle(String color, double a, double b, double c)
    {
        // 调用父类构造器
        super(color);
        this.setSides(a,b,c);
    }
    public void setSides(double a, double b, double c)
    {
        if(a >= b+c || b >= a+c || c >= a+b)
        {
            System.out.println("三角形两边之和必须大于第三边");
            return;
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double calPerimeter() {
        return a + b + c;
    }
    @Override
    public String getType() {
        return "三角形";
    }
    public static void main(String[] args) {
        Shape s1 = new Triangle("黑色",3,4,5);
        System.out.println(s1.getType());
        System.out.println(s1.calPerimeter());
    }
}
