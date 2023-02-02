package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EventObject;

public class AboutCampController extends Parent{


   /*public void switchToHome(ActionEvent event) throws IOException{
       root=FXMLLoader.load(getClass().getResource("home.fxml"));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene=new Scene(root);
       stage.setScene(scene);
       stage.show();

   }*/

    @FXML
    private ImageView goBack;
    private EventObject event;

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

    }
}
