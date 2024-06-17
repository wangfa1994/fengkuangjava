package unit13_jdbc.spi;

/**
 * @author alvin
 * @date 2020-08-30 12:48
 */
public class Cat implements IShout {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}
