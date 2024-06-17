package unit17_socket.c177.v2;

/**
 * @author alvin
 * @date 2020-06-21 20:13
 * 混杂模式（只有一个线程负责 accept，每个都会被分配 client，进行 R/W）
 */
public class MainThread {
    public static void main(String[] args) {
        // 这里不做关于 IO 和业务的事情

        // 1. 创建 IO Thread（一个或者多个）
        SelectorThreadGroup stg = new SelectorThreadGroup(3);

        // 2. 应该把监听的 server 注册到某一个 selector 上
        stg.bind(9999);
    }
}
