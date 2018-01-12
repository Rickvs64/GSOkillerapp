package UI.Login;

import Classes.Repositories.IUserRepo;
import Classes.Repositories.SQLUserRepo;
import Classes.User;
import UI.Home.HomeController;
import UI.Register.RegisterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private IUserRepo userRepo = new SQLUserRepo();

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_submit;
    @FXML
    private Label lbl_accountCreated;

    public LoginController() throws SQLException, IOException, ClassNotFoundException {
    }

    //login
    @FXML
    private void login(ActionEvent event) throws IOException {
        User user = new User(txt_username.getText(), txt_password.getText());
        Boolean loginResult = userRepo.attemptLogin(user);
        if (loginResult == true) {
            toHomeScreen(user);
        }
        else
        {
            System.out.println("Wrong user credentials, mate.");
        }

    }
    //register
    @FXML
    private void register() throws IOException {
        toRegisterScreen();
    }

    /**
     * Navigate to the home screen while sending an instance of User to be used later in the application.
     * @param user
     * @throws IOException
     */
    private void toHomeScreen(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Home/home.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        HomeController controller = fxmlLoader.<HomeController>getController();
        controller.initialize(user.convertToPlayer()); // Initializing the home screen to know which user/player is connected.
        Scene homeScreen = new Scene(root);
        Stage stage;
        stage = (Stage) txt_username.getScene().getWindow();
        stage.setScene(homeScreen);
        stage.show();
    }

    /**
     * Navigate to the register screen
     * @throws IOException
     */
    private void toRegisterScreen() throws IOException {
        // Set the next "page" (scene) to display.
        // Note that an incorrect path will result in unexpected NullPointer exceptions!
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Register/register.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        RegisterController controller = fxmlLoader.<RegisterController>getController();

        // There's no additional data required by the newly opened form.
        Scene registerScreen = new Scene(root);

        Stage stage;
        stage = (Stage) txt_username.getScene().getWindow(); // Weird backwards logic trick to get the current scene window.

        stage.setScene(registerScreen);
        stage.show();
    }
}
