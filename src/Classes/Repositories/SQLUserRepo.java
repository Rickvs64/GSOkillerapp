package Classes.Repositories;

import Classes.CryptWithMD5;
import Classes.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SQLUserRepo implements IUserRepo {

    private Connection connection;

    public SQLUserRepo() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream("config.properties");
        prop.load(in);
        in.close();

        String driver = prop.getProperty("jdbc.driver");
        String connectionURL = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");

        Class.forName(driver);

        connection = DriverManager.getConnection(connectionURL, username, password);
    }

    @Override
    public Boolean addUser(User newUser) {
        try (Statement statement = connection.createStatement()) {
            String encrypted = CryptWithMD5.cryptWithMD5(newUser.getPassword());
            newUser.setPassword(encrypted);

            statement.executeUpdate("INSERT INTO table_users (name_user, pass_user) VALUES ('" + newUser.getUsername() + "', '" + newUser.getPassword() + "')");
            return true;
        }
        catch (Exception ex) {
            // Fuck it.
            System.out.println("Something broke, try again later.");
            return false;
        }
        finally {

        }
    }

    @Override
    public void deleteUser(User delUser) {

    }

    @Override
    public Boolean attemptLogin(User user){
        try (Statement statement = connection.createStatement()){
            String encrypted = CryptWithMD5.cryptWithMD5(user.getPassword());
            user.setPassword(encrypted);

            ResultSet result = statement.executeQuery("SELECT * FROM table_users WHERE name_user = '" + user.getUsername() + "' AND pass_user='" + user.getPassword() + "';");

            if (!result.next()) {   // If next() returns false there are no matches
                return false;
            }
            else {
                return true;
            }

        }
        catch (Exception ex) {
            // Fuck it.
            System.out.println("Something broke, try again later.");
            return false;
        }
    }

    @Override
    public Boolean checkUsernameExists(String username) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT name_user FROM table_users WHERE name_user = '" + username + "';");

            if (!result.next()) {   // If next() returns false there are no matches
                return false;
            }
            else {
                return true;
            }

        }
        catch (Exception ex) {
            // Fuck it.
            System.out.println("Something broke, try again later.");
            return false;
        }
    }

    @Override
    public User lookUpUser(Integer id) {
        return null;
    }
}
