package unit08_collection.c83;

import java.util.HashSet;
import java.util.Iterator;

class R {
    int count;
    public R(int count){
        this.count = count;
    }
    public String toString() {
        return "R[count:"+count+"]";
    }
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj != null && obj.getClass() == R.class){
            R r = (R)obj;
            return this.count == r.count;
        }
        return false;
    }
}

/**
 * 当向 HashSet 中添加可变对象时，必须十分小心，如果修改 HashSet 集合中的对象，
 * 有可能导致该对象与集合中的其他对象相等，从而导致 HashSet 无法准确访问该对象。
 */
public class HashSetTest2 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));
        System.out.println(hs);
        Iterator it = hs.iterator();
        R first = (R)it.next();
        first.count = -3;
        // 此时发现集合中有重复元素
        System.out.println(hs);
        System.out.println(hs.remove(new R(-3)));
        // 可以看到被删除了一个R元素
        System.out.println(hs);
        // false
        System.out.println("hs 是否包含-3：" + hs.contains(new R(-3)));
        // false
        System.out.println("hs 是否包含-2：" + hs.contains(new R(-2)));
    }
}
