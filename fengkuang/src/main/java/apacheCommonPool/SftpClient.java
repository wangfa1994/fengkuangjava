package apacheCommonPool;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SftpClient implements AutoCloseable {


    private Session session;
    private ChannelSftp sftp;

    public SftpClient(String host, int port, String username, String password) throws Exception {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        sftp = (ChannelSftp) session.openChannel("sftp");
        sftp.connect();
    }

    public ChannelSftp getChannel() {
        return sftp;
    }

    @Override
    public void close() {
        if (sftp != null) {
            sftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }


    public boolean batchUploadFile(List<BatchSftpDto> fileLists)
            throws Exception {
        boolean success = false;

        FileInputStream fis = null;
        try {
            // 建立连接

            for (BatchSftpDto fileList : fileLists) {
                String remotePath = fileList.getRemotePath();
                String remoteFilename = fileList.getRemoteFilename();
                String localFileName = fileList.getLocalFileName();
                // 更改服务器目录
                if (null != remotePath && remotePath.trim() != "") {
                    //判断是否存在远程目录时候已经进入到该目录
                    createDirectories(sftp,remotePath);
                }
                File localFile = new File(localFileName);
                fis = new FileInputStream(localFile);
                // 发送文件
                sftp.put(fis, remoteFilename);
                success = true;
            }
        } catch (SftpException e) {
            //logger.error("发送文件时有SftpException异常!",e);
            throw e;
        } catch (Exception e) {
            //logger.error("发送文件时有异常!",e);
            throw e;
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
                // 关闭连接
            } catch (IOException e) {
                //logger.error("关闭文件时出错!",e);
            }
        }
        return success;
    }

    /**
     * 递归创建远程目录
     *
     * @param sftp ChannelSftp 对象
     * @param remotePath 远程目录路径
     * @throws SftpException 如果创建目录时发生错误
     */
    public void createDirectories(ChannelSftp sftp, String remotePath) throws SftpException {
        try {
            sftp.cd(remotePath);
        } catch (SftpException e) {
            String[] directories = remotePath.split("/");
            if (directories.length > 0) {
                for (String dir : directories) {
                    if (dir.trim().length() > 0) {
                        try {
                            sftp.cd(dir);
                        } catch (SftpException ex) {
                            sftp.mkdir(dir);
                            sftp.cd(dir);
                        }
                    }
                }
            }
        }
    }

}
