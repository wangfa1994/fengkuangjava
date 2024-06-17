package unit17_socket.c173.aio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author alvin
 * @date 2020-05-02 13:01
 */
public class AIOClient {
    final static String UTF_8 = "utf-8";
    final static int PORT = 30000;
    // 与服务器端通信的异步 Channel
    AsynchronousSocketChannel clientChannel;
    JFrame mainWin = new JFrame("多人聊天");
    JTextArea jta = new JTextArea(16, 48);
    JTextField jtf = new JTextField(40);
    JButton sendBn = new JButton("发送");
    public void init() {
        mainWin.setLayout(new BorderLayout());
        jta.setEditable(false);
        mainWin.add(new JScrollPane(jta), BorderLayout.CENTER);
        JPanel jp = new JPanel();
        jp.add(jtf);
        jp.add(sendBn);
        // 发送消息的 Action,Action 是 ActionListener 的子接口
        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = jtf.getText();
                if (content.trim().length() > 0) {
                    try {
                        // 将 content 内容写入 Channel 中
                        clientChannel.write(ByteBuffer.wrap(content.trim().getBytes(UTF_8))).get();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                // 清空输入框
                jtf.setText("");
            }
        };
        sendBn.addActionListener(sendAction);
        // 将 Ctrl + Enter 键和 send 关联
        jtf.getInputMap().put(KeyStroke.getKeyStroke('\n',
                InputEvent.CTRL_DOWN_MASK), "send");
        // 将 send 和 sendAction 关联
        jtf.getActionMap().put("send", sendAction);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.add(jp, BorderLayout.SOUTH);
        mainWin.pack();
        mainWin.setVisible(true);
    }
    public void connect() throws Exception {
        // 定义一个 ByteBuffer 准备读取数据
        final ByteBuffer buff = ByteBuffer.allocate(1024);
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(80);
        // 以指定线程池来创建一个 AsynchronousChannelGroup
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        // 以 channelGroup 作为组管理器来创建 AsynchronousSocketChannel
        clientChannel = AsynchronousSocketChannel.open(channelGroup);
        // 让 AsynchronousSocketChannel 连接到指定 IP 端口
        clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
        jta.append("---与服务器连接成功---\n");
        buff.clear();
        clientChannel.read(buff, null,
                new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
                String content = StandardCharsets.UTF_8.decode(buff).toString();
                // 显示从服务器端读取的数据
                jta.append("某人说:" + content + "\n");
                buff.clear();
                clientChannel.read(buff, null, this);
            }
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败: " + exc);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        AIOClient client = new AIOClient();
        client.init();
        client.connect();
    }
}
