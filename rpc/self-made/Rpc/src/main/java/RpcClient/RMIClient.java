package RpcClient;

import RpcServer.RMIFileStatus;
import RpcServer.RMIQueryStatus;
import RpcServer.RMIServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) {
        try {
            RMIQueryStatus queryStatus = (RMIQueryStatus) Naming.lookup(RMIServer.RMI_URL);
            RMIFileStatus status = queryStatus.getFileStatus("testRMI");
            System.out.println(status.getFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
