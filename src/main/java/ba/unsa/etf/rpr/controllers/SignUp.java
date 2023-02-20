package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.Business.UserManager;
import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
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


     
    public void initialize() {
        goBack.setOnMouseClicked(event -> {
            // Close current window
            Stage stage = (Stage) goBack.getScene().getWindow();
            stage.close();
            // Open previous window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/home.fxml"));
                Parent root = fxmlLoader.load();
                Stage homeStage = new Stage();
                homeStage.setScene(new Scene(root));
                homeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // Add an event listener to the name field
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the name field is changed
            badName.setText("");
        });
        // Add an event listener to the surname field
        surname.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the surname field is changed
            badSurname.setText("");
        });
        // Add an event listener to the username field
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the username field is changed
            badUsername.setText("");
        });
        // Add an event listener to the password field
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the password field is changed
            badPassword.setText("");
        });
        // Add an event listener to the email field
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the email field is changed
            badEmail.setText("");
        });
    }

    @FXML
    private void signUpButtonAction() {

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
            // user.setFirstName(nameInput);
            // user.setLastName(surnameInput);
            user.setEmail(emailInput);
            user.setUsername(usernameInput);
            user.setPassword(passwordInput);
            //UserDaoSQLImpl u = new UserDaoSQLImpl();

            try {
                FileReader reader = new FileReader("src/main/resources/db.properties");
                Properties p = new Properties();
                p.load(reader);
                String s1 = p.getProperty("username"), s2 = p.getProperty("password"), s3 = p.getProperty("server");
                Connection connection = DriverManager.getConnection(s3, s1, s2);

                // Add the new user to the database
                //User insertedUser = u.add(user);

                // Check if the user object was returned by the add User method from UserDaoSQLImpl.java
                //  if (insertedUser != null) {
                // Show a success message
                //    showPopupBox("User registered successfully!");
                //} else {
                // Show an error message
                //  showPopupBox("Error registering user. Please try again.");
                //}

                // Close the connection to the database
                connection.close();

            } catch (Exception exc) {
                exc.printStackTrace();

            }
        }
    }
}




    /**
     Displays a pop-up box with the given message.
    // @param message the message to display in the pop-up box
     */
  /*  public void showPopupBox(String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main/PopupBox.fxml"));
            Parent root = fxmlLoader.load();
            //PopupBoxController controller = fxmlLoader.getController();
           // controller.setMessage(message);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setAlwaysOnTop(true);
            stage.setScene(new Scene(root));

            stage.show();

            // Add a slide animation to the popup box
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.6), root);
            transition.setFromY(root.getLayoutY() + root.getLayoutBounds().getHeight());
            transition.setToY(root.getLayoutY());
            transition.play();

            // Close the popup box after 2 seconds
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> stage.close()));

            timeline.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

