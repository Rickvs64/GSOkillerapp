package Classes;

public class ActiveLobby implements ILobby {
    String lobby_name;
    String ip;
    Status status;

    public ActiveLobby(String name, String ip) {
        this.lobby_name = name;
        this.ip = ip;
        status = Status.lookingForPlayers;
    }
}
