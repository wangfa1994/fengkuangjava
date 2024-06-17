package unit06_oo2.c61_warpperclass;

public class AutoBoxingUnboxing
{
    public static void main(String[] args)
    {
        // 自动装箱，直接把一个基本类型变量赋值给 Integer 对象
        Integer inObj = 5;
        // 自动装箱，直接把一个boolean类型变量赋值给一个Object类型变量
        Object boolObj = true;
        // 自动拆箱，直接把一个Inte ger 对象赋给int 类型的变量
        int it = inObj;
        if(boolObj instanceof Boolean)
        {
            boolean b = (Boolean)boolObj;
            System.out.println(b);
        }
    }
}
