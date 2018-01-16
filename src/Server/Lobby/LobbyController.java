package Server.Lobby;

import Classes.ActiveLobby;
import Classes.IBoopListener;
import Classes.ILobby;
import Classes.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LobbyController extends UnicastRemoteObject implements IBoopListener {
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


    public LobbyController() throws RemoteException {
        // Initialize the UI.
        initUI();
    }

    public void initUI() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                btn_start.setVisible(false);
                lbl_status.setText("Input a lobby name.");
            }
        });
    }


    @FXML
    public void LobbyNameSubmit(ActionEvent actionEvent) {
        lbl_lobbyname.setText(txt_lobbyname.getText());
        lbl_lobbynameInput.setVisible(false);
        txt_lobbyname.setVisible(false);
        btn_lobbynameSubmit.setVisible(false);
        lbl_status.setText("Waiting for players.");

        startServer();
    }

    private void startServer(){
        try {
            lobby = new ActiveLobby(txt_lobbyname.getText(),"127.0.0.1");
            lobby.setListener(this);

            // Create this lobby on port 1099.
            Registry registry = LocateRegistry.createRegistry(1099);

            // Create a new service using the lobby's name.
            registry.rebind(txt_lobbyname.getText(), lobby); // The lobby name is used for registry lookup.
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("My body is ready.");
    }

    @FXML
    public void StartGame(ActionEvent actionEvent) {
    }

    @Override
    public void notifyBoop(String sender) throws RemoteException {
        System.out.println(sender + " sent me a notify.");
    }
}
