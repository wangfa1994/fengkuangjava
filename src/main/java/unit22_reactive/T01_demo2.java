package unit22_reactive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 参考文档：https://zhuanlan.zhihu.com/p/344431341
 *
 * 组合两个completableFuture
 * 将两个异步计算合并为一个，这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果。
 * thenApply()
 *
 * 将两个异步计算合并为一个，这两个异步计算之间相互独立，互不依赖
 * thenCompose()
 * thenCombine()
 *
 * 获取所有完成结果——allOf
 * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
 * allOf方法，当所有给定的任务完成后，返回一个全新的已完成CompletableFuture
 *
 * 获取率先完成的任务结果——anyOf
 * 如果最快完成的任务出现了异常，也会先返回异常，如果害怕出错可以加个exceptionally() 去处理一下可能发生的异常并设定默认返回值
 * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
 */
public class T01_demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test01("lixianfu");
        //test02();
        //test03();
        //test04();
        test05();
    }

    /**
     * 假设一个场景，我是一个小学生，我想知道今天我需要上几门课程 此时我需要两个步骤，
     * 1.根据我的名字获取我的学生信息
     * 2.根据我的学生信息查询课程 我们可以用下面这种方式来链式调用api，使用上一步的结果进行下一步操作
     */
    public static void test01(String name) throws ExecutionException, InterruptedException {
        CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> {
            // 根据学生姓名获取学生信息
            System.out.println("name " + name);
            return "alvin";
        }).thenApply(student -> {
            // 再根据学生信息获取今天的课程
            System.out.println("student info " + student);
            return Arrays.asList("语文", "数学");
        });
        List<String> strings = future.get();
        strings.forEach(System.out::println);
    }

    /**
     * 假设一个场景，我是一个小学生，今天有劳技课和美术课，我需要查询到今天需要带什么东西到学校
     */
    public static void test02() {
        CompletableFuture<List<String>> total = CompletableFuture.supplyAsync(() -> {
            System.out.println("美术课 start");
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stuff.add("画笔");
            stuff.add("颜料");
            System.out.println("美术课 end");
            return stuff;
        }).thenCompose(list -> {
            System.out.println("劳技课 start");
            // 向第二个任务传递参数list(上一个任务美术课所需的东西list)
            CompletableFuture<List<String>> insideFuture = CompletableFuture.supplyAsync(() -> {
                List<String> stuff = new ArrayList<>();
                System.out.println("劳技课2 start");
                // 第二个任务获取劳技课所需的工具
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stuff.add("剪刀");
                stuff.add("折纸");
                // 合并两个list，获取课程所需所有工具
                List<String> allStuff = Stream.of(list, stuff).flatMap(Collection::stream).collect(Collectors.toList());
                System.out.println("劳技课2 end");
                return allStuff;
            });
            System.out.println("劳技课 end");
            return insideFuture;
        });
        System.out.println(total.join().size());
    }

    public static void test03() {
        CompletableFuture<List<String>> painting = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stuff.add("画笔");
            stuff.add("颜料");
            return stuff;
        });
        CompletableFuture<List<String>> handWork = CompletableFuture.supplyAsync(() -> {
            // 第二个任务获取劳技课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stuff.add("剪刀");
            stuff.add("折纸");
            return stuff;
        });
        CompletableFuture<List<String>> total = painting
                // 传入handWork列表，然后得到两个CompletableFuture的参数Stuff1和2
                .thenCombine(handWork, (stuff1, stuff2) -> {
                    // 合并成新的list
                    List<String> totalStuff = Stream.of(stuff1, stuff1)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
                    return totalStuff;
                });
        System.out.println(total.join().size());
    }

    public static void test04() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                //使用sleep()模拟耗时操作
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 2;
        });
        CompletableFuture.allOf(future1, future1);
        // 输出3
        System.out.println(future1.join()+future2.join());
    }

    public static void test05() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new NullPointerException();
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                // 睡眠3s模拟延时
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        CompletableFuture<Object> anyOf = CompletableFuture
                .anyOf(future, future2)
                .exceptionally(error -> {
                    error.printStackTrace();
                    return 2;
                });
        System.out.println(anyOf.join());
    }
}
