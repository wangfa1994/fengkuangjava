package unit22_reactive;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1. 实例化 CompletableFuture
 * supply开头：这种方法，可以返回异步线程执行之后的结果
 * run开头：这种不会返回结果，就只是执行线程任务
 *
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor);
 * public static CompletableFuture<Void> runAsync(Runnable runnable);
 * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor);
 *
 * 2. 获取结果
 * public T    get()
 * public T    get(long timeout, TimeUnit unit)
 * public T    getNow(T valueIfAbsent)
 * public T    join()
 *
 * join() 与get() 区别在于join() 返回计算的结果或者抛出一个unchecked异常(CompletionException)，而get() 返回一个具体的异常.
 *
 * 3. 计算完成后续操作1 ---- complete
 * public CompletableFuture<T>     whenComplete(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * public CompletableFuture<T>     exceptionally(Function<Throwable,? extends T> fn)
 * 方法1和2的区别在于是否使用异步处理
 * 2和3的区别在于是否使用自定义的线程池，前三个方法都会提供一个返回结果和可抛出异常，我们可以使用lambda表达式的来接收这两个参数，然后自己处理。
 * 方法4，接收一个可抛出的异常，且必须return一个返回值，类型与钻石表达式种的类型一样
 *
 * 4. 计算完成后续操作2 ---- handle
 * public <U> CompletableFuture<U>     handle(BiFunction<? super T,Throwable,? extends U> fn)
 * public <U> CompletableFuture<U>     handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
 * public <U> CompletableFuture<U>     handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
 * 和complete的区别就在于返回值，没错，虽然同样返回CompletableFuture类型，但是里面的参数类型，handle方法是可以自定义的。
 *
 * 5. 计算完成后续操作3 ---- apply
 * public <U> CompletableFuture<U>     thenApply(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U>     thenApplyAsync(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U>     thenApplyAsync(Function<? super T,? extends U> fn, Executor executor
 * 和handle唯一的不同是，handle方法会给出异常，可以让用户自己在内部处理，而apply方法只有一个返回结果，如果异常了，会被直接抛出，交给上一层处理。
 * 如果不想每个链式调用都处理异常，那么就使用apply吧。
 *
 * 6. 计算完成后续操作4 ---- accept
 * public CompletableFuture<Void>  thenAccept(Consumer<? super T> action)
 * public CompletableFuture<Void>  thenAcceptAsync(Consumer<? super T> action)
 * public CompletableFuture<Void>  thenAcceptAsync(Consumer<? super T> action, Executor executor)
 * accept（）三个方法只做最终结果的消费，注意此时返回的CompletableFuture是空返回。只消费，无返回，有点像流式编程的终端操作。
 *
 * 7. 捕获中间产生的异常——exceptionally
 * public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
 * exceptionally() 可以帮我们捕捉到所有中间过程的异常，方法会给我们一个异常作为参数，我们可以处理这个异常，同时返回一个默认值，跟服务降级 有点像，
 * 默认值的类型和上一个操作的返回值相同。
 * 向线程池提交任务的时候发生的异常属于外部异常，是无法捕捉到的，毕竟还没有开始执行任务。作者也是在触发线程池拒绝策略的时候发现的。
 * exceptionally（） 无法捕捉RejectedExecutionException（）
 *
 */
public class T01_demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test00();
//        test01();
//        test02();
        test03();
    }

    public static void test00() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        // 将会一直阻塞，因为任务一直未完成
        Object o = objectCompletableFuture.get();
        System.out.println("test01 done");
    }

    public static void test01() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10086;
        });
        future.whenComplete((result, error) -> {
            System.out.println("拨打" + result);
            //error.printStackTrace();
        });
    }

    public static void test02() throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<String>> future = CompletableFuture.supplyAsync(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("语文");
            list.add("数学");
            // 获取今天的所有课程
            return list;
        });
        // 使用handle()方法接收list数据和error异常
        CompletableFuture<Integer> future2 = future.handle((list,error)-> {
            // 如果报错，就打印出异常
            //error.printStackTrace();
            // 如果不报错，返回一个包含Integer的全新的CompletableFuture
            return list.size();
            // 注意这里的两个CompletableFuture包含的返回类型不同
        });
        System.out.println(future2.get());
    }

    public static void test03() {
        // 实例化一个CompletableFuture,返回值是Integer
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 返回null
            return null;
        });

        CompletableFuture<String> exceptionally = future.thenApply(result -> {
            // 制造一个空指针异常NPE
            int i = result;
            return i;
        }).handle((result, error) -> {
            // 注意这里不处理 error，相当于被吃掉了。
            return "出异常了 handle 也会执行";
        }).thenApply(result -> {
            // 这里不会执行，因为上面出现了异常。如果是 handle 就会执行
            String words = "现在是" + result + "点钟";
            return words;
        }).exceptionally(error -> {
            // 我们选择在这里打印出异常
            error.printStackTrace();
            // 并且当异常发生的时候，我们返回一个默认的文字
            return "出错啊~";
        });
        exceptionally.thenAccept(System.out::println);
    }

    public static void test1() {
        CompletableFuture.supplyAsync(() -> 1)
                .whenComplete((result, error) -> {
                    System.out.println(result);
                    error.printStackTrace();
                })
                .handle((result, error) -> {
                    error.printStackTrace();
                    return error;
                })
                .thenApply(Objects::toString)
                .thenApply(Integer::valueOf)
                .thenAccept(param -> System.out.println("done"));
    }

    public static void test2() {
        long begin = System.currentTimeMillis();
        // 自定义一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 循环创建10个CompletableFuture
        List<CompletableFuture<Integer>> collect = IntStream.range(1, 10).mapToObj(i -> {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                        // 在i=5的时候抛出一个NPE
                        if (i == 5) {
                            throw new NullPointerException();
                        }
                        try {
                            // 每个依次睡眠1-9s，模拟线程耗时
                            TimeUnit.SECONDS.sleep(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);
                        return i;
                    }, executorService)
                    // 这里处理一下i=5时出现的NPE
                    // 如果这里不处理异常，那么异常会在所有任务完成后抛出,小伙伴可自行测试
                    .exceptionally(Error -> {
                        try {
                            TimeUnit.SECONDS.sleep(5);
                            System.out.println(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 100;
                    });
            return future;
        }).collect(Collectors.toList());
        // List列表转成CompletableFuture的Array数组,使其可以作为allOf()的参数
        // 使用join()方法使得主线程阻塞，并等待所有并行线程完成
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();
        System.out.println("最终耗时" + (System.currentTimeMillis() - begin) + "毫秒");
        executorService.shutdown();
    }
}
