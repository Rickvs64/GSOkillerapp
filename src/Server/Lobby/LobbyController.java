package Server.Lobby;

import Classes.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LobbyController {
    private Player player;

    @FXML
    private Label lbl_username;


    private void updateUserInfo() {
        lbl_username.setText(player.getUsername());
    }

    public void initialize(Player player) {
        this.player = player;
        updateUserInfo();
    }
}
