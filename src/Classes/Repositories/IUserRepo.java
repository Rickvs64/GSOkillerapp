package Classes.Repositories;

import Classes.User;

public interface IUserRepo {
    Boolean addUser(User newUser);
    void deleteUser(User delUser);
    Boolean attemptLogin(User user);
    User lookUpUser(Integer id);

    Boolean checkUsernameExists(String username);
}
