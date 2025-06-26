package apacheCommonPool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class SftpClientFactory extends BasePooledObjectFactory<SftpClient> {
    private String host;
    private int port;
    private String username;
    private String password;

    public SftpClientFactory(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public SftpClient create() throws Exception {
        return new SftpClient(host, port, username, password);
    }

    @Override
    public PooledObject<SftpClient> wrap(SftpClient client) {
        return new DefaultPooledObject<>(client);
    }

    @Override
    public void destroyObject(PooledObject<SftpClient> p) throws Exception {
        p.getObject().close(); // 关闭 SFTP 连接
    }

    @Override
    public boolean validateObject(PooledObject<SftpClient> p) {
        return p.getObject().getChannel().isConnected(); // 验证连接是否有效
    }
}
