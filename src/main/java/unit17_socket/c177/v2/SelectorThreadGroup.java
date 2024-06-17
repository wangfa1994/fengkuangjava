package unit17_socket.c177.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alvin
 * @date 2020-06-21 20:37
 */
public class SelectorThreadGroup { // 天生都是 boss

    SelectorThread[] sts;

    ServerSocketChannel server = null;

    AtomicInteger xid = new AtomicInteger(0);

//    SelectorThreadGroup stg = this;
//    public void setWorker(SelectorThreadGroup stg) {
//        this.stg = stg;
//    }

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

    public void nextSelector(Channel c) {
        // 在 main 线程中，取到堆里的 selectorThread 对象
        SelectorThread st = next();

        // 1. 通过队列传递数据，消息
        st.lbq.add(c);
        // 2. 通过打断阻塞，让对应的线程去自己在打断后完成注册 selector
        st.selector.wakeup();

        // 重点： c 有可能是 server，有可能是 client
//        ServerSocketChannel s = (ServerSocketChannel) c;
//        try {
//            // 这时 selector.select() 已经被调用，所以这里会被柱塞
//            s.register(st.selector, SelectionKey.OP_ACCEPT);
//            st.selector.wakeup(); // 功能是让 selector 的 select() 方法立刻返回，不阻塞
//            System.out.println("aaaa");
//
//        } catch (ClosedChannelException e) {
//            e.printStackTrace();
//        }

    }

//    public void nextSelectorV3(Channel c) {
//        try {
//            if (c instanceof ServerSocketChannel) {
//                SelectorThread st = next();
//                st.lbq.put(c);
//                st.setWorker();
//                st.selector.wakeup();
//            } else {
//
//            }
//        }
//    }

    // 无论 serversockerchannel 还是 socketchannel 都复用这个方法
    private SelectorThread next() {
        // 轮询，可能会倾斜
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }

    private SelectorThread nextV3() {
        // 轮询，可能会倾斜
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }
}
