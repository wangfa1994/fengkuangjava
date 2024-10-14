package unit17_socket.c177.v1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author alvin
 * @date 2020-06-21 20:14
 */
public class SelectorThread implements Runnable{
    // 每个线程对应一个 selector，
    // 多线程情况下，该主机/该程序的并发客户端被分配到多个 selector 上
    // 注意，每个客户端只绑定到其中一个 selector
    // 其实不会有交互问题

    Selector selector = null;
    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();
    // 线程所属组
    SelectorThreadGroup stg;

    // 构造器
    SelectorThread(SelectorThreadGroup stg) {
        try {
            this.stg = stg;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // loop
        while (true) {
            try {
                // 1. 阻塞的 select
                // System.out.println(Thread.currentThread().getName() + " ==>before select......" + selector.keys().size());
                int nums = selector.select();
                // System.out.println(Thread.currentThread().getName() + " ==>after select......" + selector.keys().size());

                // 2. 处理 selectkeys
                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    // 线性处理 keys
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isAcceptable()) {
                            // 复杂，接收客户端的过程（接收之后要注册，多线程情况下，注册到哪里？）
                            // 轮询注册
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        } else if (key.isWritable()) {
                            // TODO:
                        }
                    }
                }

                // 3. 处理一些 task
                if (!lbq.isEmpty()) {
                    Channel c = lbq.take();
                    if (c instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) c;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName() + " register accept......");
                    } else if (c instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) c;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName() + " register read......");
                    }
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " ==>readHandler.... ");
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        SocketChannel client = (SocketChannel)key.channel();
        buffer.clear();
        while (true) {
            try {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip(); // 将读到的内容翻转，然后直接写出
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else if (num < 0) {
                    // 客户端断开了(异常情况)
                    System.out.println("client: " + client.getRemoteAddress() + " closed......");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " ==>acceptHandler.... ");
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        try {
            // accept client
            SocketChannel client = server.accept();
            client.configureBlocking(false);

            // choose a selector and register
            stg.nextSelector(client);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}