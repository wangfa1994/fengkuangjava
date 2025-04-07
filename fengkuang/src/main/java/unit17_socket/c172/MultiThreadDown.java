package unit17_socket.c172;

/**
 * @author alvin
 * @date 2020-05-01 11:26
 */
public class MultiThreadDown {
    public static void main(String[] args) throws Exception {
        // 初始化 DownUtil 对象
        final DownUtil downUtil = new DownUtil("http://wiki.monkey-kong.cn/images/java/c16/%E7%BA%BF%E7%A8%8B%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.png",
                "./fengkuang/src/main/java/unit17_socket/c172/thread.png", 4);
        // 开始下载
        downUtil.download();
        new Thread(()->{
            while (downUtil.getCompleteRate() < 1) {
                // 每隔 0.1 秒查询一次任务进度
                System.out.println("已完成：" + downUtil.getCompleteRate());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
