package Classes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILobby extends Remote {
    void boop(String playername) throws RemoteException;
    void setListener(IBoopListener listener) throws RemoteException;
    Status getStatus() throws RemoteException;
    void setStatus(Status newStatus) throws RemoteException;
}
