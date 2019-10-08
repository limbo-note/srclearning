package RpcServer;

import java.io.Serializable;
import java.rmi.Remote;

public interface RMIFileStatus extends Serializable {

    public long serialVersionUID = 1L;
    public String getFilename();
}
