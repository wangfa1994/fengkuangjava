package unit20_stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.common.collect.Lists;

/**
 * java 流式处理两个常见问题测试
 * 1、parallelStream 线程安全问题
 * 2、toMap key 重复报错问题
 */
public class Test01_StreamCommonProblem {

    /**
     * 直接使用 parallelStream，数量不对
     */
    @Test
    public void testParallelStream() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list.size());
        List<Integer> resultList = new ArrayList<>();
        list.parallelStream().forEach(item -> {
            // item++;
            // to do something
            resultList.add(item);
        });
        System.out.println(resultList.size());
        resultList.stream().sorted().forEach(item -> {
            System.out.print(item + " ");
        });
    }

    /**
     * 修改为使用 stream，结果正确
     */
    @Test
    public void testStream() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list.size());
        List<Integer> resultList = new ArrayList<>();
        list.stream().forEach(item -> {
            item++;
            resultList.add(item);
        });
        System.out.println(resultList.size());
        resultList.stream().sorted().forEach(item -> {
            System.out.print(item + " ");
        });
    }

    /**
     * 修改为使用 collect，结果正确
     * 并行流内部不能对外部共享变量作写操作，有县城安全问题。如果有需要，使用收集器实现。
     */
    @Test
    public void testStreamCollect() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list.size());
        List<Integer> resultList = list.parallelStream()
                .map(item -> {
                    // to do something
                    return ++item;
                })
                .collect(Collectors.toList());
        System.out.println(resultList.size());
        resultList.stream().sorted().forEach(item -> {
            System.out.print(item + " ");
        });
    }

    /**
     * 没有指定 mergeFunction，当 list 数据重复，会有报错风险
     */
    @Test
    public void testToMapDangerous() {
        List<String> list = Lists.newArrayList();
        list.add("item1");
        list.add("item1");
        list.add("item2");
        Map<String, String> resultMap = list.stream()
                .collect(Collectors.toMap(item -> item, Function.identity()));
    }

    /**
     * 指定 mergeFunction，程序正常
     */
    @Test
    public void testToMapRight() {
        List<String> list = Lists.newArrayList();
        list.add("item1");
        list.add("item1");
        list.add("item2");
        Map<String, String> resultMap = list.stream()
                .collect(Collectors.toMap(item -> item, Function.identity(), (oldItem, newItem) -> newItem));
    }
}
