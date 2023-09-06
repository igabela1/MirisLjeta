package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;


public class SignUp extends Component {

    private UserManager u = new UserManager();
    @FXML
    private ImageView goBack;

    public TextField name;
    public Label badName;
    public TextField surname;
    public TextField email;

    public TextField username;

    public PasswordField password;
    public Button signUpButton;
    public Label badSurname;
    public Label badEmail;
    public Label badUsername;
    public Label badPassword;



    public void initialize(){
        name.textProperty().addListener((observable, oldValue, newValue) -> {

            badName.setText("");
        });

        surname.textProperty().addListener((observable, oldValue, newValue) -> {
            badSurname.setText("");
        });

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            badUsername.setText("");
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            badPassword.setText("");
        });

        email.textProperty().addListener((observable, oldValue, newValue) -> {
            badEmail.setText("");
        });
    }

    @FXML
    private void signUpButtonActionPerformed() throws NoSuchAlgorithmException {

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

        }
        if (!check) {
            // Create a new user data object and set the instance variables
            User user = new User();
            user.setFirstName(nameInput);
            user.setLastName(surnameInput);
            user.setEmail(emailInput);
            user.setUsername(usernameInput);
            user.setPassword(UserManager.hashPassword(passwordInput));

            try {
                FileReader reader = new FileReader("src/main/resources/db.properties");
                Properties p = new Properties();
                p.load(reader);
                String s1 = p.getProperty("username"), s2 = p.getProperty("password"), s3 = p.getProperty("server");
                Connection connection = DriverManager.getConnection(s3, s1, s2);

                // Add the new user to the database
                User insertedUser = u.add(user);

                // Check if the user object was returned by the add User method from UserDaoSQLImpl.java
                if (insertedUser != null) {
                    // Show a success message
                    showPopupBox("User registered successfully!");
                } else {
                    // Show an error message
                    showPopupBox("Error registering user. Please try again.");
                }

                // Close the connection to the database
                connection.close();

            } catch (Exception exc) {
                exc.printStackTrace();
            } catch (Room_BungalowException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void showPopupBox(String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/PopupBox.fxml"));
            Parent root = fxmlLoader.load();
            PopupBoxController controller = fxmlLoader.getController();
            controller.setMessage(message);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setAlwaysOnTop(true);
            stage.setScene(new Scene(root));

            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
