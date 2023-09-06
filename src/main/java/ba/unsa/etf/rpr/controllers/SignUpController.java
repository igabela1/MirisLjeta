package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpController extends Component {
    @FXML private ImageView goBack;

    public TextField name, surname, email, username;
    public Label badName, badSurname, badEmail, badUsername, badPassword;

    public PasswordField password;
    @FXML private Button signUpButton;
    private User user = new User();
    private final Utils utils = new Utils();
    private final UserManager userManager = new UserManager();
    @FXML
    public void initialize(){

        name.textProperty().addListener((observable, oldValue, newValue) -> badName.setText(""));
        surname.textProperty().addListener((observable, oldValue, newValue) -> badSurname.setText(""));
        username.textProperty().addListener((observable, oldValue, newValue) -> badUsername.setText(""));
        password.textProperty().addListener((observable, oldValue, newValue) -> badPassword.setText(""));
        email.textProperty().addListener((observable, oldValue, newValue) -> badEmail.setText(""));

        goBack.setOnMouseClicked(event -> utils.changeWindow(goBack, "Home Page", "/fxml/Home.fxml", new HomeController()));
    }

    @FXML
    private void signUpButtonAction() throws NoSuchAlgorithmException, Room_BungalowException {

        // Retrieve user input from form fields
        String nameInput = name.getText();
        String surnameInput = surname.getText();
        String emailInput = email.getText();
        String usernameInput = username.getText();
        String passwordInput = password.getText();
        boolean check = false;
        // Validate the input
        if (Objects.equals(name.getText(), "")) {
            // Display an error message
            badName.setText("Name can't be empty.");
            check = true;
        }
        if (Objects.equals(surname.getText(), "")) {
            // Display an error message
            badSurname.setText("Surname can't be empty.");
            check = true;
        }
        if (Objects.equals(username.getText(), "")) {
            // Display an error message
            badUsername.setText("Username can't be empty.");
            check = true;
        }
        if (Objects.equals(email.getText(), "")) {
            // Display an error message
            badEmail.setText("Email can't be empty.");
            check = true;
        }
        if (Objects.equals(password.getText(), "")) {
            // Display an error message
            badPassword.setText("Password can't be empty");
            check = true;
        } else {
            // Use a regular expression to check the email format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(emailInput).matches()) {
                // Display an error message
                badEmail.setText("Invalid email format.");
                check = true;
            }
            // Check if the username already exists
            if (userManager.findUsername(usernameInput) != null) {
                badUsername.setText("Username is already taken.");
                return;
            }
        }
        if (!check) {
            // Create a new user data object and set the instance variables
            user.setFirstName(nameInput);
            user.setLastName(surnameInput);
            user.setEmail(emailInput);
            user.setUsername(usernameInput);
            user.setPassword(UserManager.hashPassword(passwordInput));

            try {
                // Add the new user to the database
                user = UserManager.add(user);
                utils.changeWindow(signUpButton, "Sign In", "/fxml/SignIn.fxml", new SignInController());
            } catch (Exception e) {
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }
        }
    }
}
