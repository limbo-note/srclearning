package RpcServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static String RMI_URL = "rmi://localhost:12090/query";
    public static void main(String[] args) {
        try{
            RMIQueryStatus queryStatus = new RMIQueryStatusImpl();

            LocateRegistry.createRegistry(12090);
            Naming.rebind(RMI_URL,queryStatus);

            System.out.println("Server Ready.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
