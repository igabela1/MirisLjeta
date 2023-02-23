package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
/**
 * The type Account admin page controller.
 */
public class AdminAccountController {
    /**
     * The Home button.
     */
    @FXML
    public Button homeButton;
    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;

    /**
     * The Log out button.
     */
    @FXML
    public ImageView logOutButton;
    /**
     * The Name field.
     */
    @FXML
    public Label nameField = new Label();
    /**
     * The Surname field.
     */
    @FXML
    public Label surnameField = new Label();
    /**
     * The Username field.
     */
    @FXML
    public Label usernameField = new Label();
    /**
     * The Email field.
     */
    @FXML
    public Label emailField = new Label();

    private User user;

    /**
     * Instantiates a new Account admin page controller.
     *
     * @param u the u
     */
    public AdminAccountController(User u){
        this.user = u;
    }

    /**
     * Instantiates a new Account admin page controller.
     */
    public AdminAccountController(){}

    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void closeButtonMouseExited(MouseEvent mouseEvent){ closeButton.getStyleClass().remove("closeButtonWhenHovered"); }
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    @FXML
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    @FXML
    public User getUser() {
        return user;
    }

    /**
     * Initialize.
     */
    public void initialize() {

        nameField.setText(user.getFirstName());
        surnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());

        logOutButton.setOnMouseClicked(this::logOut);

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);
    }



    private void logOut(MouseEvent event) {
        // Close the current window
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        // Open the login window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/home.fxml"));
            Parent root = fxmlLoader.load();
            Stage loginStage = new Stage();
        //    loginStage.getIcons().add(new Image("images.png"));
            loginStage.setScene(new Scene(root, Color.TRANSPARENT));
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
