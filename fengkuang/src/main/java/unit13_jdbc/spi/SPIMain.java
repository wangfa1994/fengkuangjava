package unit13_jdbc.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author alvin
 * @date 2020-08-30 12:51
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        Iterator<IShout> iterator = shouts.iterator();
        while (iterator.hasNext()) {
            IShout s = iterator.next();
            s.shout();
        }
    }
}
