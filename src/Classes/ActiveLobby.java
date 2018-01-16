package Classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ActiveLobby extends UnicastRemoteObject implements ILobby {
    String lobby_name;
    String ip;
    Status status;
    IBoopListener listener;

    public ActiveLobby(String name, String ip) throws RemoteException {
        this.lobby_name = name;
        this.ip = ip;
        status = Status.lookingForPlayers;
    }

    @Override
    public void setListener(IBoopListener listener) throws RemoteException {
        this.listener = listener;
    }

    @Override
    public void boop(String playername) throws RemoteException {
        System.out.println(playername + " sent a simple command.");
        listener.notifyBoop(playername);

//        // Notify all listeners.
//        for (IBoopListener listener : listeners) {
//            listener.notifyBoop(playername);
//        }
    }
}
