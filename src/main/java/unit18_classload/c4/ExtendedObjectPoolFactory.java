package unit18_classload.c4;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author alvin
 * @date 2020-05-04 21:59
 */
public class ExtendedObjectPoolFactory {
    // 定义一个对象池
    private Map<String, Object> objectPool = new HashMap<>();
    private Properties config = new Properties();
    // 从指定属性文件中初始化 Properties 对象
    public void init(String fileName) {
        try(FileInputStream fis = new FileInputStream(fileName)){
            config.load(fis);
        } catch (IOException ex) {
            System.out.println("读取" + fileName + "异常");
        }
    }
    // 定义一个创建对象的方法
    private Object createObject(String clazzName) throws Exception{
        Class<?> clazz = Class.forName(clazzName);
        return clazz.getConstructor().newInstance();
    }
    // 根据指定文件来初始化对象池
    public void initPool() throws Exception {
        for(String name : config.stringPropertyNames()) {
            if(!name.contains("%")){
                objectPool.put(name, createObject(config.getProperty(name)));
            }
        }
    }
    // 根据属性文件调用指定对象的 setter 方法
    public void initProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String name : config.stringPropertyNames()){
            if(name.contains("%")){
                // 将配置文件中的 key 按 % 分隔
                String[] objAndProp = name.split("%");
                // 获取调用 setter 方法的参数值
                Object target = getObject(objAndProp[0]);
                // 获取 setter 方法名：set+"首字母大写"+剩下部分
                String mtdName = "set" +
                        objAndProp[1].substring(0,1).toUpperCase()
                        + objAndProp[1].substring(1);
                // 获取 Class 对象
                final Class<?> targetClass = target.getClass();
                // 获取希望调用的 setter 方法
                final Method mtd = targetClass.getMethod(mtdName, String.class);
                // 调用方法
                mtd.invoke(target, config.getProperty(name));
            }
        }
    }
    public Object getObject(String name) {
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception{
        final ExtendedObjectPoolFactory epf = new ExtendedObjectPoolFactory();
        epf.init("a.txt");
        epf.initPool();
        epf.initProperty();
        System.out.println(epf.getObject("a"));
    }
}
