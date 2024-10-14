package unit17_socket.c173.personal;

/**
 * @author alvin
 * @date 2020-05-01 16:32
 */
public interface ChatProtocol {
    // 定义协议字符串的长度
    int PROTOCOL_LEN = 1;
    // 下面是一些协议字符串，服务器端和客户端交换信息都应该在前、后添加这种特殊字符
    String MSG_ROUND = "$";     // 公聊
    String USER_ROUND = "Π";    // 登录
    String LOGIN_SUCCESS = "1"; // 登录成功
    String NAME_REP = "-1";     // 登录失败
    String PRIVATE_ROUND = "⭐";// 私聊
    String SPLIT_SING = "❄";    // 私聊时分隔符
}
