package Classes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBoopListener extends Remote {
    void notifyBoop(String sender) throws RemoteException;
}
