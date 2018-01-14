package Classes.Repositories;

import Classes.ILobby;
import Classes.Player;
import Classes.User;

import java.util.List;

public interface ILobbyRepo {
    List<ILobby> fetchCurrentLobbies();
    void saveMatch(ILobby lobby, Integer winnerIndex);
    List<ILobby> fetchMatchHistory(Player player);
    List<ILobby> fetchMatchHistory(User user);
}
