package unit09_T.c92;

/**
 * @author alvin
 * @date 2020-04-23 23:37
 */
public class A1 extends Apple<String> {
    // 正确重写了父类的方法，返回值
    // 与父类Apple<String>的返回值完全相同
    @Override
    public String getInfo() {
        return "子类" + super.getInfo();
    }

    // 编译报错，重写父类方法时，返回值不一致
//    @Override
//    public Object getInfo() {
//        return "子类";
//    }
}
