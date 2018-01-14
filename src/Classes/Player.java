package Classes;

public class Player implements IUser {
    Integer user_id;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    String username;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    String ip;

    public Player() {

    }

    public Player(Integer id, String username) {
        this.user_id = id;
        this.username = username;
        this.ip = "127.0.0.1"; // For now.
    }
}
