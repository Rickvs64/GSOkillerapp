package UI.Home;

import Classes.ILobby;
import Classes.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ThreadLocalRandom;

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
    @FXML
    private Button btn_sendBoop;


    // TEMP
    public HomeController() {
        int randomNum = ThreadLocalRandom.current().nextInt(100, 1000 + 1);
        player = new Player(0, "Testplayer" + randomNum);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                updateUserInfo();
                btn_sendBoop.setVisible(false);
            }
        });

    }

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
            System.setProperty("java.rmi.server.hostname","127.0.0.1");

            // Get registry from port 1099.
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);

            // Try to obtain an active lobby service from the user given lobby name.
            lobby = (ILobby) myRegistry.lookup(lobbyName);

            // Call the boop method to check connection.
            lobby.boop(player.getUsername());

            System.out.println("Message Sent");
            btn_sendBoop.setVisible(true);
            txt_lobbyName.setVisible(false);
            lbl_lobbyName.setVisible(false);
            btn_joinLobby.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendBoop(ActionEvent actionEvent) {
        try {
            lobby.boop(player.getUsername());
            System.out.println("Message Sent");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
