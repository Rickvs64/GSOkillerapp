package Classes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILobby extends Remote {
    void boop(String playername) throws RemoteException;
}
