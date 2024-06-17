package unit17_socket.c177.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alvin
 * @date 2020-06-21 20:37
 */
public class SelectorThreadGroup {

    // 线程集合
    SelectorThread[] sts;
    // 服务端
    ServerSocketChannel server = null;
    // 轮询器
    AtomicInteger xid = new AtomicInteger(0);
    // 线程组构造器
    SelectorThreadGroup (int num) {
        // num 线程数
        sts = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            sts[i] = new SelectorThread(this);
            new Thread(sts[i]).start(); // 启动线程
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            // server 注册到哪个 selector 呢？
            nextSelector(server);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 无论 ServerSocketChannel 还是 SocketChannel 都复用这个方法
    public void nextSelector(Channel c) {
        // 在 main 线程中，取到堆里的 selectorThread 对象
        SelectorThread st = next();

        // 1. 通过队列传递数据，消息。让 SelectorThread 自己去注册
        st.lbq.add(c);
        // 2. 通过打断阻塞，让对应的线程去自己在打断后完成注册 selector
        st.selector.wakeup();
    }

    private SelectorThread next() {
        // 轮询，可能会倾斜
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }
}
