package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class HomeController extends Parent{


    @FXML
    public Button aboutUsButton;

    @FXML
    public Button myProfileButton;
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
    }
}
