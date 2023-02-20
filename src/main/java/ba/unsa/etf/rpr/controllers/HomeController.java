package ba.unsa.etf.rpr.controllers;

import com.sun.javafx.scene.control.InputField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class HomeController {


    @FXML
    public Button aboutUsButton;

    @FXML
    public Button myProfileButton;
    @FXML
    public Button signUpButton;
    @FXML
    public Button signInButton;
   public HomeController() {
    }
    @FXML
    public void initialize() {
        aboutUsButton.setOnAction(event -> {
            // Exit current window
            Stage stage = (Stage) aboutUsButton.getScene().getWindow();
            stage.close();
            // Open the about camp page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/AboutCamp.fxml"));
                Parent root = fxmlLoader.load();
                Stage aboutUsStage = new Stage();
                aboutUsStage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                aboutUsStage.setScene(scene);
                aboutUsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

     myProfileButton.setOnAction(event -> {
            // Exit current window
            Stage stage = (Stage) myProfileButton.getScene().getWindow();
            stage.close();
            // Open the profile page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/MyProfile.fxml"));
                Parent root = fxmlLoader.load();
                Stage aboutUsStage = new Stage();
                aboutUsStage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                aboutUsStage.setScene(scene);
                aboutUsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        signInButton.setOnAction(event -> {
            // Exit current window
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.close();
            // Open the profile page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/SignIn.fxml"));
                Parent root = fxmlLoader.load();
                Stage aboutUsStage = new Stage();
                aboutUsStage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                aboutUsStage.setScene(scene);
                aboutUsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signUpButton.setOnAction(event -> {
            // Exit current window
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            stage.close();
            // Open the profile page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/SignUp.fxml"));
                Parent root = fxmlLoader.load();
                Stage aboutUsStage = new Stage();
                aboutUsStage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                aboutUsStage.setScene(scene);
                aboutUsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
