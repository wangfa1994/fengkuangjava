package apacheCommonPool;

import java.io.Serializable;

public class BatchSftpDto implements Serializable {
    private static final long serialVersionUID = -4202515429597725865L;

    private String remotePath;
    private String remoteFilename;
    private String localFileName;

    public BatchSftpDto() {
    }

    public BatchSftpDto(String remotePath, String remoteFilename, String localFileName) {
        this.remotePath = remotePath;
        this.remoteFilename = remoteFilename;
        this.localFileName = localFileName;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getRemoteFilename() {
        return remoteFilename;
    }

    public void setRemoteFilename(String remoteFilename) {
        this.remoteFilename = remoteFilename;
    }

    public String getLocalFileName() {
        return localFileName;
    }

    public void setLocalFileName(String localFileName) {
        this.localFileName = localFileName;
    }

}
