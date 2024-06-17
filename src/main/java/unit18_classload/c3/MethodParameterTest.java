package unit18_classload.c3;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @author alvin
 * @date 2020-05-04 17:58
 */
class Test {
    public void replace(String str, List<String> list){}
}
public class MethodParameterTest {
    public static void main(String[] args) throws NoSuchMethodException {
        // 获取 String 的类
        final Class<Test> clazz = Test.class;
        // 获取 Test 类带有两个参数的 replace 方法
        final Method replace = clazz.getMethod("replace", String.class, List.class);
        // 获取指定方法的参数个数
        System.out.println("参数个数：" + replace.getParameterCount());
        // 获取所有参数信息
        final Parameter[] parameters = replace.getParameters();
        int index = 1;
        for(Parameter p : parameters) {
            System.out.println("----第" + index++ + "个参数信息----");
            System.out.println("参数名：" + p.getName());
            System.out.println("形参类型：" + p.getType());
            System.out.println("泛型类型：" + p.getParameterizedType());
        }
    }
}
