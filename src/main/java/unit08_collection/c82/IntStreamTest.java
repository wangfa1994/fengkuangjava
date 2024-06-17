package unit08_collection.c82;

import java.util.stream.IntStream;

public class IntStreamTest {
    public static void main(String[] args) {
        IntStream is = IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();
        // 下面调用聚集方法的代码每次只能执行一行
        //System.out.println(String.format("最大值：[%s]", is.max().getAsInt()));
        //System.out.println(String.format("最小值：[%s]", is.min().getAsInt()));
        //System.out.println(String.format("是否所有元素的平方都大于20：[%s]",
        //       is.allMatch(ele -> ele * ele > 20)));
        // 映射成一个新的 stream
        IntStream newIs = is.map(ele -> ele * 2);
        newIs.forEach(System.out::println);
    }
}
