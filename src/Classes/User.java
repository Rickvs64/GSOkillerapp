package Classes;

public class User implements IUser{
    Integer id;
    String username;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
    Double winrate;

    public User() {

    }

    public User(String username, String password) { // For logging in.
        this.username = username;
        this.password = password;
    }

    public Player convertToPlayer() {
        return new Player(id, username);
    }

    public Double calculateWinrate(Integer matchesWon, Integer totalMatches) {
        double a = (double) matchesWon;
        double b = (double) totalMatches;
        return (a / b);
    }
}
