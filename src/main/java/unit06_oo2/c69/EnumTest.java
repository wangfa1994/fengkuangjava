package unit06_oo2.c69;

public class EnumTest {
    public void judge(SeasonEnum s)
    {
        // switch 语句里可以是枚举值
        switch (s)
        {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case FALL:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }
    }

    public static void main(String[] args) {
        // 枚举类默认有 values() 方法，返回所有实例
        for (SeasonEnum s : SeasonEnum.values())
        {
            System.out.println(s);
        }
        // 使用枚举类时，EnumClass.variable
        new EnumTest().judge(SeasonEnum.SUMMER);
    }
}
