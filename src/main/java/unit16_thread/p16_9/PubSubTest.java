package unit16_thread.p16_9;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class PubSubTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建发布者(ForkJoinPool.commonPool())
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        // 创建订阅者
        MySubscriber<String> subscriber = new MySubscriber<>();
        // 注册订阅者
        publisher.subscribe(subscriber);
        // 发布几个数据
        System.out.println("开始发布数据");
        List.of("Java", "C", "C++", "Lua")
            .forEach(i -> {
                publisher.submit(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        // 发布结束
        publisher.close();
        // 发布结束后，为了让发布者线程不死亡，暂停线程
        synchronized ("fkjava"){
            "fkjava".wait();
        }

    }
}
// 定义订阅者
class MySubscriber<T> implements Flow.Subscriber<T>{

    // 发布者、订阅者之间的纽带
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        // 开始请求数据
        subscription.request(1);
    }
    // 接收到数据时触发
    @Override
    public void onNext(T item) {
        System.out.println("获取到数据：" + item);
        // 请求下一条数据
        subscription.request(1);
    }
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        synchronized ("fkjava"){
            "fkjava".notifyAll();
        }
    }
    @Override
    public void onComplete() {
        System.out.println("订阅结束");
        synchronized ("fkjava"){
            "fkjava".notifyAll();
        }
    }
}
