package unit08_collection.c83;

import java.util.TreeSet;

class M {
    int age;
    public M(int age){
        this.age = age;
    }
    public String toString(){
        return "M[age:" + age + "]";
    }
}

/**
 * 定制排序，构造器给出 Comparator 的实现
 */
public class TreeSetTest4 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet((o1, o2) -> {
            M m1 = (M)o1;
            M m2 = (M)o2;
            // age 越大， M 对象越小
            return m1.age > m2.age ? -1
                    : m1.age < m2.age ? 1 : 0;
        });
        ts.add(new M(5));
        ts.add(new M(-3));
        ts.add(new M(9));
        System.out.println(ts);
    }
}
