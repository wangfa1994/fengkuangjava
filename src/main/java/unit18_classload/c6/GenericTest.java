package unit18_classload.c6;

import unit17_socket.c172.GetPostTest;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author alvin
 * @date 2020-05-05 0:05
 */
public class GenericTest {
    private Map<String, Integer> score;

    public static void main(String[] args) throws Exception{
        Class<GenericTest> clazz = GenericTest.class;
        final Field f = clazz.getDeclaredField("score");
        // 直接使用 getType() 取出类型只对普通类型的成员变量有效
        final Class<?> a = f.getType();
        // 仅仅输出 java.util.Map
        System.out.println("score的类型是：" + a);
        // 获得成员变量 f 的泛型类型
        final Type gType = f.getGenericType();
        // 如果 gType 是 ParameterizedType 对象
        if(gType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)gType;
            // 获取原始类型
            final Type rType = pType.getRawType();
            System.out.println("原始类型是：" + rType);
            // 取得泛型类型的泛型参数
            final Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型信息是：" );
            for(int i=0; i<tArgs.length; i++) {
                System.out.println("第" + i + "个泛型类型是:" + tArgs[i]);
            }
        } else {
            System.out.println("获取泛型类型出错！");
        }
    }
}
