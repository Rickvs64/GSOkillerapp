package Classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ActiveLobby extends UnicastRemoteObject implements ILobby {
    String lobby_name;
    String ip;
    Status status;

    public ActiveLobby(String name, String ip) throws RemoteException {
        this.lobby_name = name;
        this.ip = ip;
        status = Status.lookingForPlayers;
    }

    @Override
    public void boop(String playername) throws RemoteException {
        System.out.println(playername + " sent a simple command.");
    }
}
