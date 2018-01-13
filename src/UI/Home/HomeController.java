package UI.Home;

import Classes.ActiveLobby;
import Classes.ILobby;
import Classes.Player;
import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class HomeController {
    private Player player;
    private ILobby lobby;

    @FXML
    private Label lbl_username;
    @FXML
    private Label lbl_lobbyName;
    @FXML
    private TextField txt_lobbyName;
    @FXML
    private Button btn_joinLobby;


    private void updateUserInfo() {
        lbl_username.setText(player.getUsername());
    }

    public void initialize(Player player) {
        this.player = player;
        updateUserInfo();
    }

    @FXML
    public void joinLobby(ActionEvent actionEvent) {
        connectToLobby(txt_lobbyName.getText());
    }

    private void connectToLobby(String lobbyName){
        try {
            // Get registry from port 1099.
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);

            // Try to obtain an active lobby service from the user given lobby name.
            lobby = (ILobby) myRegistry.lookup(lobbyName);

            // TEMP: Call the boop method to check connection.
            lobby.boop(player.getUsername());

            System.out.println("Message Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
