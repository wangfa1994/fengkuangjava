package unit18_classload.c6;

import javax.swing.*;
import java.util.Date;

/**
 * @author alvin
 * @date 2020-05-04 23:54
 */
public class CrazyitObjectFactory {
    public static Object getInstance(String name){
        try{
            // 创建指定类对应的 Class 对象
            Class cls = Class.forName(name);
            return cls.newInstance();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    Date d = (Date)CrazyitObjectFactory.getInstance("java.util.Date");
    // 将导致编译正常，但是运行时报错，而且需要强转
//    JFrame f = (JFrame)CrazyitObjectFactory.getInstance("java.util.Date");
}
