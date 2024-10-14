package unit08_collection.c84;

import java.util.ArrayList;
import java.util.List;

/**
 * List 基础用法
 */
public class ListTest {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add(new String("test1"));
        books.add(new String("test2"));
        books.add(new String("test3"));

        // 将新字符串对象插入到第二个位置
        books.add(1, "new test2");
        // 发现变为4个元素了
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        // 删除第三个元素
        books.remove(2);
        System.out.println(books);
        // 输出1，表明位与第二位
        // 虽然是new的对象，但是List是根据 equals 判断相等的
        System.out.println(books.indexOf(new String("new test2")));
        // 将第二个元素替换回来
        books.set(1, new String("test2"));
        System.out.println(books);
        // 0 =< x < 1
        System.out.println(books.subList(0,1));

        System.out.println("=========");
        // add/set 只能是有效索引，否则数组越界
        // books.set(10, "aaa");
        // books.add(10, "aaa");
    }
}
