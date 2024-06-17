package unit08_collection.c86;

import java.util.HashMap;
import java.util.Iterator;

public class HashMapErrorTest {
    public static void main(String[] args) {
        HashMap ht = new HashMap();
        ht.put(new A(60000), "test1");
        ht.put(new A(87563), "test2");
        Iterator it = ht.keySet().iterator();
        // 取出 Map 中第一个 key，并修改 count
        A first = (A)it.next();
        first.count = 1;
        System.out.println(ht);
        // 只能删除没有被修改过的 key
        ht.remove(new A(87563));
        System.out.println(ht);
        // 无法获取被修改过的 key null
        System.out.println(ht.get(new A(1)));
        System.out.println(ht.get(new A(60000)));
    }
}
