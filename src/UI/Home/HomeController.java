package UI.Home;

import Classes.Player;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

public class HomeController {
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
