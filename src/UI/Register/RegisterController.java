package UI.Register;

import Classes.Repositories.IUserRepo;
import Classes.Repositories.SQLUserRepo;
import Classes.User;
import UI.Login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class RegisterController {
    private User user;
    private IUserRepo userRepo = new SQLUserRepo();

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private PasswordField txt_password_confirm;

    public RegisterController() throws SQLException, IOException, ClassNotFoundException {
        // Empty constructor with declared exceptions, thus allowing form-to-form navigation.
    }

    @FXML
    private void cancelRegister() throws IOException {
        backToLogin(false);
    }

    @FXML
    private void confirmRegister() throws IOException {
        if (txt_username.getText() == null || txt_username.getText().trim().isEmpty()) {
            showMessageDialog(null, "Username is a required field.");
            System.out.println("Username is a required field.");
        }
        else if (txt_username.getText().matches("[0-9]+")) {
            showMessageDialog(null, "Username cannot consist of numbers exclusively.");
            System.out.println("Username cannot consist of numbers exclusively.");
        }
        else if (txt_password.getText() == null || txt_password.getText().trim().isEmpty()) {
            showMessageDialog(null, "Password is a required field.");
            System.out.println("Password is a required field.");
        }
        else if (!txt_password.getText().equals(txt_password_confirm.getText())) {
            // Passwords do NOT match.
            showMessageDialog(null, "Passwords don't match.");
            System.out.println("Passwords don't match.");
        }
        else if (userRepo.checkUsernameExists(txt_username.getText().toLowerCase())) {
            // Username already taken
            showMessageDialog(null, "This username is already in use.");
            System.out.println("This username is already in use.");
        }
        else {
            user = new User(txt_username.getText().toLowerCase(), txt_password.getText());
            if (userRepo.addUser(user)) {
                backToLogin(true);
            }
            else {
                showMessageDialog(null, "Shit went wrong and I don't even know, man.");
            }
        }

    }

    /**
     * Navigate back to the login screen, either as cancellation or confirmation of creating a new account.
     * @param accountCreated Boolean to declare whether the register process was cancelled or completed.
     * @throws IOException
     */
    private void backToLogin(Boolean accountCreated) throws IOException {
        // Set the next "page" (scene) to display.
        // Note that an incorrect path will result in unexpected NullPointer exceptions!
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Login/login.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        LoginController controller = fxmlLoader.<LoginController>getController();

        Scene loginScreen = new Scene(root);

        Stage stage;
        stage = (Stage) txt_username.getScene().getWindow(); // Weird backwards logic trick to get the current scene window.

        stage.setScene(loginScreen);
        stage.show();
    }
}
