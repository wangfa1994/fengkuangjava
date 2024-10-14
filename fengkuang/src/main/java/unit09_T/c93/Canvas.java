package unit09_T.c93;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alvin
 * @date 2020-04-26 20:59
 */
// 定义一个抽象类 Shape
abstract class Shape{
    abstract void draw(Canvas c);
}
class Circle extends Shape{
    @Override
    void draw(Canvas c) {
        System.out.println("在画布" + c + "上画了一个圆");
    }
}
class Rectangle extends Shape{
    @Override
    void draw(Canvas c) {
        System.out.println("在画布" + c + "上画了一个矩形");
    }
}
public class Canvas {
    // 同时画多个形状
    public void drawAll(List<? extends Shape> shapes){
        for(Shape s : shapes){
            s.draw(this);
        }
    }

    // 同样，使用泛型上限后，无法添加元素
    public void addRectangle(List<? extends Shape> shapes){
        // 编译报错
        // shapes.add(0, new Rectangle());
        // 正常
        shapes.add(0, null);
    }

    public static void main(String[] args) {
        List<Circle> circleList = new ArrayList<>();
        circleList.add(new Circle());
        Canvas c = new Canvas();
        // 如果不用泛型上线，编译报错。改写后正常
         c.drawAll(circleList);
    }
}
