package unit09_T.c92;

/**
 * @author alvin
 * @date 2020-04-23 23:42
 */
public class A2 extends Apple {
    @Override
    public String getInfo() {
        // super.getIno() 方法返回值是 Object 类型
        return super.getInfo().toString();
    }
}
