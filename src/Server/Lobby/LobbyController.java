package Server.Lobby;

import Classes.*;
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
import java.util.ArrayList;
import java.util.List;

public class LobbyController extends UnicastRemoteObject implements IBoopListener {
    private Player player1;
    private Player player2;
    private ILobby lobby;
    private List<String> playernames;
    private List<String> breakingPlayers;

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

        playernames = new ArrayList<String>();
        breakingPlayers = new ArrayList<String>();
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
        btn_start.setVisible(false);
        try {
            startMatch();
        }
        catch (RemoteException ex) {
            System.out.println("Unexpected RemoteException in: private void startMatch()");
        }
    }

    private void startMatch() throws RemoteException {
        lobby.setStatus(Status.inProgress);
        lbl_player1STAT.setText("Racing");
        lbl_player2STAT.setText("Racing");
        lbl_status.setText("Match in progress.");
    }

    @Override
    public void notifyBoop(String sender) throws RemoteException {
        System.out.println(sender + " sent me a notify.");
        switch (lobby.getStatus()) {
            case lookingForPlayers: {
                if (playernames.contains(sender)) {
                    System.out.println("We're still looking for players!");
                }
                else {
                    playernames.add(sender);

                    if (playernames.size() >= 2) {
                        readyForStart();

                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                lbl_player2.setText(sender);
                            }
                        });
                    }
                    else {
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                lbl_player1.setText(sender);
                            }
                        });
                    }
                }
                break;
            }
            case readyToStart: {
                System.out.println("Stop it, we're waiting for someone to start this match!");
                break;
            }
            case inProgress: {
                if (breakingPlayers.contains(sender)) {
                    System.out.println(sender + " is already braking!");
                }
                else {
                    breakingPlayers.add(sender);
                    System.out.println(sender + " has started braking!");

                    if (lbl_player1.getText().equals(sender)) {
                        // Player 1 started braking.
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                lbl_player1STAT.setText("Braking");
                            }
                        });
                    }
                    else {
                        // Player 2 started braking.
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                lbl_player2STAT.setText("Braking");
                            }
                        });
                    }
                }
                break;
            }
            case undefined: {
                System.out.println("Some weird voodoo errors going on here.");
                break;
            }
        }
    }

    private void readyForStart() throws RemoteException {
        btn_start.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override public void run() {
                lbl_status.setText("Ready to start.");
            }
        });

        lobby.setStatus(Status.readyToStart);
    }
}
