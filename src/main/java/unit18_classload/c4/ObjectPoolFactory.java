package unit18_classload.c4;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-04 21:41
 */
public class ObjectPoolFactory {
    // 定义一个对象池，前面的对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    // 定义一个创建对象的方法
    private Object createObject(String clazzName) throws
            ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {
        final Class<?> clazz = Class.forName(clazzName);
        return clazz.getConstructor().newInstance();
    }
    // 该方法根据指定文件来初始化对象池
    public void initPool(String fileName) {
        try(FileInputStream fis = new FileInputStream(fileName)){
            final Properties props = new Properties();
            props.load(fis);
            for(String name : props.stringPropertyNames()){
                objectPool.put(name, createObject(props.getProperty(name)));
            }
        } catch (Exception ex) {
            System.out.println("读取" + fileName + "异常");
        }
    }
    public Object getObject(String name) {
        return objectPool.get(name);
    }

    public static void main(String[] args) {
        final ObjectPoolFactory pf = new ObjectPoolFactory();
        pf.initPool("a.txt");
        System.out.println(pf.getObject("a"));
        System.out.println(pf.getObject("b"));
    }
}
