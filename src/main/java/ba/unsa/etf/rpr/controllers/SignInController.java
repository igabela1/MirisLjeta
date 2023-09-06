package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class SignInController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button signInButton;
    @FXML private Label badUsername, badPassword, errorLabel;
    @FXML private ImageView goBack;

    private final UserManager userManager = new UserManager();
    private final Utils utils = new Utils();

    @FXML
    public void initialize() {

        usernameField.setOnAction(event -> passwordField.requestFocus());
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> badUsername.setText(""));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> badPassword.setText(""));

        goBack.setOnMouseClicked(event -> utils.changeWindow(goBack, "Home Page", "/fxml/Home.fxml", new HomeController()));
    }

    @FXML
    private void handleLogin() throws Room_BungalowException, NoSuchAlgorithmException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean showBadUsername = username.trim().isEmpty();
        boolean showBadPassword = password.trim().isEmpty();

        if (showBadUsername || showBadPassword) {
            if (showBadUsername) {
                badUsername.setText("Username cannot be empty.");
            } else badUsername.setText("");

            if (showBadPassword) {
                badPassword.setText("Password cannot be empty");
            } else badPassword.setText("");

            return;
        }

        User user = userManager.findUsername(username);

        if (user == null || !(Objects.equals(user.getPassword(), UserManager.hashPassword(password)))){
            errorLabel.setText("Invalid username or password!");
            return;
        }

        String fxmlTitle = (user.getRole() == 1) ? "Admin Panel" : "My Profile";
        String fxmlPath = (user.getRole() == 1) ? "/fxml/Admin/AdminAccount.fxml" : "/fxml/MyProfile.fxml";
        utils.changeWindow(signInButton, fxmlTitle, fxmlPath, (user.getRole() == 1) ? new AdminAccountController(user) : new MyProfileController(user));
    }
}
