package unit08_collection.c86;

import java.util.Hashtable;

class A {
    int count;
    public A(int count){
        this.count = count;
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o != null && o.getClass() == A.class){
            A a = (A)o;
            return this.count == a.count;
        }
        return false;
    }
    public int hashCode(){
        return this.count;
    }
}
class B {
    public boolean equals(Object o) {
        return true;
    }
}
public class HashTableTest {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        ht.put(new A(60000), "疯狂 Java 讲义");
        ht.put(new A(87563), "请量级");
        ht.put(new A(1232), new B());
        System.out.println(ht);
        // value：只要 equals 相等即可 true
        System.out.println(ht.containsValue("test"));
        // key：需要 equals 和 hashCode 都相等 true
        System.out.println(ht.containsKey(new A(87563)));
        // 删除最后一个entry
        ht.remove(new A(1232));
        System.out.println(ht);
    }
}
