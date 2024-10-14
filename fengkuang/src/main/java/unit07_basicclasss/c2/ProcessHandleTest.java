package unit07_basicclasss.c2;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author alvin
 * @date 2020-05-24 8:53
 */
public class ProcessHandleTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Runtime rt = Runtime.getRuntime();
        // 运行记事本程序
        final Process p = rt.exec("notepad.exe");
        final ProcessHandle ph = p.toHandle();
        System.out.println("进程是否在运行:" + ph.isAlive());
        System.out.println("进程ID:" + ph.pid());
        System.out.println("父进程:" + ph.parent());

        final ProcessHandle.Info info = ph.info();
        // 通过 info 获取进程相关信息
        System.out.println("进程命令:" + info.command());
        System.out.println("进程参数:" + info.arguments());
        System.out.println("进程启动时间:" + info.startInstant());
        System.out.println("进程累计运行时间:" + info.totalCpuDuration());
        // 进程结束时运行某个任务
        final CompletableFuture<ProcessHandle> cf = ph.onExit();
        cf.thenRunAsync(()-> {
            System.out.println("程序退出");
        });
        Thread.sleep(5000);
    }
}
