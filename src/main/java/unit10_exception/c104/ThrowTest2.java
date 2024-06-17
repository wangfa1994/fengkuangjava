package unit10_exception.c104;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author alvin
 * @date 2020-05-03 14:32
 */
public class ThrowTest2 {
    public static void main(String[] args)
        // Java 7 以前，必须声明抛出 Exception。
        throws FileNotFoundException
    {
        try{
            new FileOutputStream("a.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
}
