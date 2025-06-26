package apacheCommonPool;

import com.jcraft.jsch.ChannelSftp;

public class Main {

    public static void main(String[] args) throws Exception {

        String sftpHost ="172.0.0.139";
        int sftpPort = 7800;
        String sftpUserName="";
        String sftpPassword="";

        SftpClientPool pool = new SftpClientPool(sftpHost,sftpPort,sftpUserName,sftpPassword);

            SftpClient client = pool.borrowObject();
            ChannelSftp channel = client.getChannel();
            channel.cd("/");
            System.out.println("SFTP connected and directory changed.");
            pool.returnObject(client);


        SftpClient client1 = pool.borrowObject();
        ChannelSftp channel1 = client.getChannel();
        channel.cd("/");
        System.out.println("SFTP connected and directory changed.");

        System.out.println("=======");



    }
}
