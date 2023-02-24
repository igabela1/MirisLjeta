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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The type My profile page controller.
 */
public class MyProfile{

    /**
     * The Root.
     */
    public Pane root;

    /**
     * The Log out button.
     */
    public ImageView logOutButton;


    private User user;

    /**
     * Instantiates a new My profile page controller.
     *
     * @param u the u
     */
    public MyProfile(User u){this.user=u;}

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
     * The Username label.
     */
    @FXML
    public Label usernameLabel = new Label();

    /**
     * The First name label.
     */
    @FXML
    public Label firstNameLabel = new Label();

    /**
     * The Last name label.
     */
    @FXML
    public Label lastNameLabel = new Label();

    /**
     * The Email label.
     */
    @FXML
    public Label emailLabel = new Label();

    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;

    /**
     * Initialize.
     */
    public void initialize() {

        logOutButton.setOnMouseClicked(event -> logOut());

        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        emailLabel.setText(user.getEmail());
        usernameLabel.setText(user.getUsername());

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);




    }

    private void logOut() {
        // Close the current window
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        // Open the login window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/Main.fxml"));
            Parent root = fxmlLoader.load();
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, Color.TRANSPARENT));
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void closeButtonMouseExited(MouseEvent mouseEvent){closeButton.getStyleClass().remove("closeButtonWhenHovered");}
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }
}
