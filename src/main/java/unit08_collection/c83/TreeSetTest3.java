package unit08_collection.c83;

import java.util.TreeSet;

class T implements Comparable {
    int count;
    public T(int count){
        this.count = count;
    }
    public String toString(){
        return "T[count:"+count+"]";
    }
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj != null && obj.getClass() == T.class){
            T r = (T)obj;
            return r.count == this.count;
        }
        return false;
    }
    public int compareTo(Object obj){
        T r = (T)obj;
        return count > r.count ? 1 :
                count < r.count ? -1 : 0;
    }
}

/**
 * 1. 修改可变元素后，排序可能变化，但是不会重新调整位置
 * 2. 实例变量被改变后，元素无法删除；但是没被改变并且没有重复的元素可以正常删除
 * 3. 为了程序健壮，推荐不要修改放入 HashSet 和 TreeSet 中元素的关键实例变量
 */
public class TreeSetTest3 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add(new T(5));
        ts.add(new T(-3));
        ts.add(new T(9));
        ts.add(new T(-2));
        // 输出是有序的
        System.out.println(ts);
        // 取出第一个元素，并重新赋值
        ((T)ts.first()).count = 20;
        // 取出最后一个员，并重新赋值（值和第二个元素相同）
        ((T)ts.last()).count = -2;
        // 输出是无序的，并且有重复元素
        System.out.println(ts);
        // 删除实例变量被改变的元素，删除失败
        System.out.println(ts.remove(new T(-2)));
        System.out.println(ts);
        // 删除实例变量没有被改变的元素，删除成功
        System.out.println(ts.remove(new T(5)));
        System.out.println(ts);
    }
}
