package apacheCommonPool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class SftpClientPool {

    private GenericObjectPool<SftpClient> pool;

    public SftpClientPool(String host, int port, String username, String password) {
        SftpClientFactory factory = new SftpClientFactory(host, port, username, password);
        GenericObjectPoolConfig<SftpClient> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(10); // 最大连接数
        config.setMinIdle(2);  // 最小空闲连接数
        config.setMaxIdle(5);  // 最大空闲连接数
        config.setTestOnBorrow(true); // 借出时验证对象
        config.setTestOnReturn(true); // 归还时验证对象

        this.pool = new GenericObjectPool<>(factory, config);
    }

    public SftpClient borrowObject() throws Exception {
        return pool.borrowObject();
    }

    public void returnObject(SftpClient client) {
        //进行归还的时候，是否需要将目录设置到根目录

        pool.returnObject(client);
    }

    public void close() {
        pool.close();
    }
}
