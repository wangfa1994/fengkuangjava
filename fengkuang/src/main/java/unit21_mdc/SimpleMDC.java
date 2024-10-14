package unit21_mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

public class SimpleMDC {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMDC.class);
    public static final String TRACE_ID = "traceId";

    public static void main(String[] args) {
        // 查看logback路径方法
//        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);
//        // 查看classpath路径方法
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println("classpath=>" + path);
//        String s = SimpleMDC.class.getResource("/").toString();
//        System.out.println("classpath=>" + s);

//        MDC.put(TRACE_ID, UUID.randomUUID().toString());
//        logger.info("开始调用服务A，进行业务处理");
//        logger.info("业务处理完毕");
//        MDC.remove(TRACE_ID);
//        logger.info("移除MDC后没有traceId了。");

        new BizHandel("F1001").start();
        new BizHandel("F1002").start();
    }
}

class BizHandel extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMDC.class);

    private String funCode;

    public BizHandel(String funCode) {
        this.funCode = funCode;
    }

    @Override
    public void run() {
        MDC.put("traceId", UUID.randomUUID().toString());
        logger.info("开始调用服务{}，进行业务处理", funCode);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("服务{}处理完毕", funCode);
        MDC.remove("traceId");
    }
}
