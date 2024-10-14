package unit08_collection.c86;

import java.util.TreeMap;

class R implements Comparable{
    int count;
    public R(int count){
        this.count = count;
    }
    public String toString(){
        return "R[count:"+count+"]";
    }
    public boolean equals(Object o){
        if(this == o) return true;
        if(o != null && o.getClass() == R.class){
            R r = (R)o;
            return r.count == this.count;
        }
        return false;
    }
    public int compareTo(Object o){
        R r = (R)o;
        return count > r.count ? 1 : count < r.count ? -1 : 0;
    }
}
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap tm = new TreeMap();
        tm.put(new R(3), "test3");
        tm.put(new R(-5), "test-5");
        tm.put(new R(9), "test9");
        System.out.println(tm);
        // 取出第一个 entry -5
        System.out.println(tm.firstEntry());
        // 取出最后一个 key 9
        System.out.println(tm.lastKey());
        // 取出大于2的一个 key 3
        System.out.println(tm.higherKey(new R(2)));
        // 取出小于2的一个 entry -5
        System.out.println(tm.lowerEntry(new R(2)));
        // -5 =< x < 9 -5 3
        System.out.println(tm.subMap(new R(-5), new R(9)));
    }
}
