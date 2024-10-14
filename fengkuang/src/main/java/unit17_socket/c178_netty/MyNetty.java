package unit17_socket.c178_netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author alvin
 * @date 2020-06-30 20:10
 */
public class MyNetty {

    /**
     * 理解 netty 的 Bytebuf
     */
    @Test
    public void myBytebuf() {
        // 分配
//        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
        // unpool isDirect=false
//        ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        // pool
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        print(buf);

        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        // IndexOutOfBoundsException
//        buf.writeBytes(new byte[]{1,2,3,4});
//        print(buf);
    }

    public static void print(ByteBuf buf) {
        // read
        System.out.println("isReadable      :"+buf.isReadable()); // 是否可读取
        System.out.println("readerIndex      :"+buf.readerIndex()); // 从哪里开始读取
        System.out.println("readableBytes      :"+buf.readableBytes());// 可以读多少
        // write
        System.out.println("isWritable      :"+buf.isWritable());
        System.out.println("writerIndex      :"+buf.writerIndex());
        System.out.println("writableBytes      :"+buf.writableBytes());
        // capacity
        System.out.println("capacity      :"+buf.capacity());
        System.out.println("maxCapacity      :"+buf.maxCapacity());
        // isDirect
        System.out.println("isDirect      :"+buf.isDirect()); // 是否堆外
        // ---
        System.out.println("-----------------------");
    }

    /**
     * NioEventLoopGroup，可以理解为线程池
     */
    @Test
    public void testGroup() {
        NioEventLoopGroup selector = new NioEventLoopGroup(2);
        selector.execute(()->{
            while (true) {
                System.out.println("hello world");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        selector.execute(()->{
            while (true) {
                System.out.println("hello world2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端
     */
    @Test
    public void clientMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioSocketChannel client = new NioSocketChannel();

        // thread 管事件,事件第一步,读写第二步
        // client 注册进入 thread
        ChannelFuture register = thread.register(client); // epoll_ctl(5, ADD, 3)
        // register.sync();

        // client 增加 handler
        ChannelPipeline p = client.pipeline();
        p.addLast(new MyInHandler());

        // 连接服务器
        ChannelFuture connect = client.connect(new InetSocketAddress("192.168.238.66", 9090));
        // ChannelFuture sync = connect.sync();

        // 向服务器发送数据
        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
        // send.sync();

        connect.channel().closeFuture().sync();
        System.out.println("client over...");
    }

    /**
     * 服务端
     */
    @Test
    public void serverMode() throws InterruptedException {

        NioEventLoopGroup thread = new NioEventLoopGroup(1);

        NioServerSocketChannel server = new NioServerSocketChannel();
        thread.register(server);

        ChannelPipeline p = server.pipeline();
        // 由于服务端应该可以接收多个客户端连接，这里使用一个 ChannelInit 作为中间类
        // 而不是具体的 MyInHandler，因为 netty 要求这里的 Handler 必须是单例的
        // 但是 MyInHandler 是用户业务处理逻辑，这里要求用户必须的类必须为单例是不合适的
        p.addLast(new MyAcceptHandler(thread, new ChannelInit())); // accept 接收客户端,并注册

        ChannelFuture bind = server.bind(new InetSocketAddress("192.168.238.1", 9090));
        bind.sync().channel().closeFuture().sync();
        System.out.println("server close...");
    }

    @Test
    public void nettyClient() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(group)
                .channel(NioSocketChannel.class)
//                .handler(new ChannelInit())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new MyInHandler());
                    }
                })
                .connect(new InetSocketAddress("192.168.238.66", 9090));
        Channel client = connect.sync().channel();

        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
        send.sync();

        client.closeFuture().sync();
    }

    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture bind = bs.group(group, group)
                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInit())
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new MyInHandler());
                    }
                })
                .bind(new InetSocketAddress("192.168.238.1", 9090));

        bind.sync().channel().closeFuture().sync();
    }
}

/**
 * accept 处理器，接收客户端连接后注册客户端
 */
class MyAcceptHandler extends ChannelInboundHandlerAdapter {
    EventLoopGroup selector;
    ChannelHandler handler;
    public MyAcceptHandler(EventLoopGroup selector, ChannelHandler handler) {
        this.selector = selector;
        this.handler = handler;
    }
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server registered...");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // server socket => accept client
        // socket => msg R/W
        SocketChannel client = (SocketChannel) msg; // 框架 accept 后直接返回 client
        // 2. 响应式的 handler
        ChannelPipeline p = client.pipeline();
        p.addLast(handler); // 1. client::pipeline[ChannelInit]

        // 1. 注册
        selector.register(client);
    }
}

/**
 * 为啥也要有一个 init handler？
 * 可以不加，但是 MyInHandler 就必须设计为 单例的（Sharable），会影响用户使用。
 */
@ChannelHandler.Sharable
class ChannelInit extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel client = ctx.channel();
        ChannelPipeline p = client.pipeline();
        // 更优雅的写法应该把本类修改为抽象类,提供 init 方法
        p.addLast(new MyInHandler()); //2. client::pipeline[ChannelInit, MyInHandler]
        ctx.pipeline().remove(this); // 已经不需要
    }
}

/**
 * 就是用户自己实现的,你能说让用户放弃属性的操作吗?
 * 这是用户的业务处理逻辑，@ChannelHandler.Sharable 不应该被强压给用户
 */
//@ChannelHandler.Sharable
class MyInHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client registered...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        // read 操作会移动指针
//        CharSequence str = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
        // 给操作不会移动指针
        CharSequence str = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(str);

        // 上面必须要使用 get，不然这里 buf 中已经没有数据可以读取
        ctx.writeAndFlush(buf); // 收到啥返回啥
    }
}