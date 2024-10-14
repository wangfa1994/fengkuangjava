package unit06_oo2.c69;

public enum Gender implements GenderDesc{
    // 此处枚举值必须调用对应构造器创建
    // 花括号部分实际上是一个类体部分
    MALE("男")
    {
        @Override
        public void info() {
            System.out.println("这个枚举值代表男性");
        }
    },
    FEMALE("女")
    {
        @Override
        public void info() {
            System.out.println("这个枚举值代表女性");
        }
    };
    private final String name;
    // 枚举类构造器只能用 private 修饰
    private Gender(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    // 这样写，所有枚举实例行为全部一样
//    @Override
//    public void info() {
//        System.out.println("这是一个用于定义性别的枚举类");
//    }
}
