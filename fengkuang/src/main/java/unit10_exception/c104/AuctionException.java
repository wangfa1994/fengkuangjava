package unit10_exception.c104;

/**
 * @author alvin
 * @date 2020-05-03 14:25
 */
public class AuctionException extends Exception {
    // 无参数构造器
    public AuctionException(){}
    // 带一个字符串参数的构造器
    public AuctionException(String msg) {
        super(msg);
    }
    // 接收 Throwable 参数的构造器
    // 用于封装原始异常，从而实现对异常的链式处理
    public AuctionException(Throwable t){
        super(t);
    }
}
