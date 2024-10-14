package unit18_classload.c6;

import java.util.Date;

/**
 * @author alvin
 * @date 2020-05-04 23:58
 */
public class CrazyitObjectFactory2 {
    public static <T> T getInstance(Class<T> cls){
        try{
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    public static void main(String[] args) {
        // 获取实例后无需类型转换
        final Date d = CrazyitObjectFactory2.getInstance(Date.class);
    }
}
