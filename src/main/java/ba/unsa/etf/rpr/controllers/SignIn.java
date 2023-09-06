package ba.unsa.etf.rpr.controllers;

        import ba.unsa.etf.rpr.business.UserManager;
        import ba.unsa.etf.rpr.domain.User;
        import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.geometry.Pos;
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

        import java.io.IOException;
        import java.security.NoSuchAlgorithmException;
        import java.util.Objects;


public class SignIn {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button signInButton;
    @FXML
    private ImageView goBack;
    @FXML
    public Label badUsernameIN;
    @FXML
    public Label badPasswordIN;
    @FXML
    public Label errorLabel;

    private UserManager u = new UserManager();


    @FXML
    public void initialize() {
        usernameField.setOnAction(this::moveToNextTextField);
        passwordField.setOnAction(this::moveToTheSignIn);

    }


    @FXML
    public void moveToNextTextField(ActionEvent event) {
        passwordField.requestFocus();
    }

    @FXML
    public void moveToTheSignIn(ActionEvent event){
        signInButton.setDisable(false);
        signInButton.setVisible(true);
        signInButton.fire();
    }

    @FXML
    private void handleLogin() throws Room_BungalowException, NoSuchAlgorithmException {

        String username = usernameField.getText();
        String password = passwordField.getText();


        boolean loginSuccessful;
        User user = new User();


        if (!username.trim().isEmpty()) {
            // Display an error message
            badUsernameIN.setText("");
        }else badUsernameIN.setText("Username cannot be empty.");

        if(!password.trim().isEmpty()){
            // Display an error message
            badPasswordIN.setText("");
            loginSuccessful=true;
        }else{
            badPasswordIN.setText("Password cannot be empty");
            loginSuccessful=false;
        }

        if(loginSuccessful){
            // Check the input against the database
            user = u.findUsername(username);
            errorLabel.setAlignment(Pos.CENTER);
            if (user!=null) {
                // hashing the password then comparing it with the one in the database
                String hashedPassword = UserManager.hashPassword(password);
                if(Objects.equals(user.getPassword(), hashedPassword)){
                    try {
                        // Load the popup box FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main/PopupBox.fxml"));
                        Parent root = loader.load();
                        // Get the controller for the popup box
                        PopupBoxController popupBoxController = loader.getController();
                        // Set the message to be displayed in the popup box
                        popupBoxController.setMessage("You have successfully logged in!");
                        // Create the scene for the stage
                        Scene scene = new Scene(root);
                        // Create the stage for the popup box
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } }else {
                // Display an error message
                errorLabel.setText("Invalid username or password!");
                return;
            }
        }

        // Login for Admin
        if(loginSuccessful && Objects.requireNonNull(user).getisAdministrator()==1){
            // Transfer to the new window after a delay
            User finalUser = user;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                // Create the new window
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Admin Panel");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Admin/AdminAccount.fxml"));
                    AdminAccountController controller = new AdminAccountController(finalUser);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                stage.show();
                // Close the login window
                signInButton.getScene().getWindow().hide();
            }));
            timeline.play();
        }
        // Login for regular User
        else if(loginSuccessful){
            // Transfer to the new window after a delay
            User finalUser = user;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                // Create the new window
                Stage stage = new Stage();
                stage.setTitle("Home Page");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/home.fxml"));
                    HomeController controller = new HomeController(finalUser);
                    fxmlLoader.setController(controller);
                    stage.setScene(new Scene(fxmlLoader.load()));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                stage.show();
                // Close the login window
                signInButton.getScene().getWindow().hide();
            }));
            timeline.play();
        }
    }

}
