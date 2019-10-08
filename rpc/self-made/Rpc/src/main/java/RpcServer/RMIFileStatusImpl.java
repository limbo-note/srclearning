package RpcServer;

public class RMIFileStatusImpl implements RMIFileStatus {
    private String filename;

    public RMIFileStatusImpl(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
