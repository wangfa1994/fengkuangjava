package unit13_jdbc.spi;

/**
 * @author alvin
 * @date 2020-08-30 12:49
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
