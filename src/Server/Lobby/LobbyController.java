package Server.Lobby;

import Classes.ILobby;
import Classes.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LobbyController {
    private Player player1;
    private Player player2;
    private ILobby lobby;

    @FXML
    private Label lbl_status;
    @FXML
    private Label lbl_lobbyname;
    @FXML
    private TextField txt_lobbyname;
    @FXML
    private Label lbl_lobbynameInput;
    @FXML
    private Button btn_lobbynameSubmit;
    @FXML
    private Label lbl_player1;
    @FXML
    private Label lbl_player2;
    @FXML
    private Label lbl_player1DIST;
    @FXML
    private Label lbl_player2DIST;
    @FXML
    private Label lbl_player1STAT;
    @FXML
    private Label lbl_player2STAT;
    @FXML
    private Button btn_start;


    public LobbyController() {
        // Initialize the UI.
        initUI();
    }

    public void initUI() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                btn_start.setVisible(false);
            }
        });
    }


    @FXML
    public void LobbyNameSubmit(ActionEvent actionEvent) {
        System.out.println("Hoi");
    }

    @FXML
    public void StartGame(ActionEvent actionEvent) {
    }
}
