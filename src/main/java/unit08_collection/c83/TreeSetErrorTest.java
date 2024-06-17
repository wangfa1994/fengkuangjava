package unit08_collection.c83;

import java.util.TreeSet;

class Err {}
public class TreeSetErrorTest {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        // 添加失败， Err 没有实现 Comparable 接口
        ts.add(new Err());
    }
}
