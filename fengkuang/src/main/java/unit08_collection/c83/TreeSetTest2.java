package unit08_collection.c83;

import java.util.TreeSet;

class Z implements Comparable{
    int age;
    public Z(int age){
        this.age = age;
    }
    public boolean equals(Object obj) {
        return true;
    }
    public int compareTo(Object o) {
        return 1;
    }
    public String toString() {
        return String.format("Z[%s]", age);
    }
}
public class TreeSetTest2 {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        Z z1 = new Z(6);
        set.add(z1);
        // 再次添加 z1 还是成功，因为 compareTo 没有返回 0
        System.out.println(set.add(z1));
        System.out.println(set);
        ((Z)set.first()).age = 9;
        // 发现两个元素全部变为了 9
        System.out.println(set);
    }
}
