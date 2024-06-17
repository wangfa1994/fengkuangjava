package unit14_annotation.c143.simpleevent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author alvin
 * @date 2020-05-04 9:41
 */
public class AnnotationTest {
    private JFrame mainWin = new JFrame("使用注解绑定事件监听器");
    // 使用 @ActionListenerFor 注解为 ok 按钮绑定事件监听器
    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("确定");
    // 使用 @ActionListenerFor 注解为 cancel 按钮绑定事件监听器
    @ActionListenerFor(listener = CancelListener.class)
    private JButton cancel = new JButton("取消");

    public void init(){
        // 初始化界面的方法
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);

        ActionListenerInstaller.processAnnotations(this);

        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args) {
        new AnnotationTest().init();
    }
}
// 定义 ok 按钮的事件监听器事件
class OkListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "单击了确认按钮");
    }
}
// 定义 cancel 按钮的事件监听器事件
class CancelListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "单击了取消按钮");
    }
}
