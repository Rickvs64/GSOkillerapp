package Server.Lobby;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lobby extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
        primaryStage.setTitle("Lobby");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
