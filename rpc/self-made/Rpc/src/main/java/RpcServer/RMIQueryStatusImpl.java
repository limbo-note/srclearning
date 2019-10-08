package RpcServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIQueryStatusImpl extends UnicastRemoteObject implements RMIQueryStatus{
    protected RMIQueryStatusImpl() throws RemoteException {
    }

    public RMIFileStatus getFileStatus(String filename) throws RemoteException {
        return new RMIFileStatusImpl(filename);
    }
}
