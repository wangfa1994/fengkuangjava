package unit17_socket.c173.personal;

import java.util.*;

/**
 * @author alvin
 * @date 2020-05-01 16:38
 */
// 通过组合 HashMap 对象来实现 UserMap，要求 value 也不能重复
public class UserMap<K,V> {
    // 创建一个线程安全的 map
    public Map<K,V> map = Collections.synchronizedMap(new HashMap<K,V>());
    // 根据 value 来删除指定项
    public synchronized void removeByValue(Object value) {
        for (Object key : map.keySet()) {
            if(map.get(key) == value) {
                map.remove(key);
                break;
            }
        }
    }
    // 获取所有 value 组成的 set 集合
    public synchronized Set<V> valueSet() {
        Set<V> result = new HashSet<>();
        // 将 map 中所有 value 添加到 result 中
        map.forEach((key, value) -> result.add(value));
        return result;
    }
    // 根据 value 查找 key
    public synchronized K getKeyByValue(V val) {
        for(K key : map.keySet()) {
            if(map.get(key) == val || map.get(key).equals(val)) {
                return key;
            }
        }
        return null;
    }
    // 实现 put 方法，该方法不允许 value 重复
    public synchronized V put(K key, V value) {
        for(V val : valueSet()) {
            if(val.equals(value) && val.hashCode() == value.hashCode()) {
                throw new RuntimeException("UserMap 实例中不允许有重复的 value");
            }
        }
        return map.put(key, value);
    }
}
