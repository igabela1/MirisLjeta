package ba.unsa.etf.rpr.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * controller for the main page which opens first when the app loads

 */
public class MainController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;
    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e)->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home/SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                ex.printStackTrace();
                System.out.println("Can't load file");
            }
        });

        minimizeButton.setOnAction(event -> {
            Stage stage = (Stage) minimizeButton.getScene().getWindow();
            stage.setIconified(true);
        });
    }


    @FXML
    private void open_signin(){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home/SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println("Can't load file");
            }
        });
    }


    @FXML
    private void open_signup(){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home/SignUp.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println("Can't load file");
            }
        });
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void closeButtonMouseEntered() {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }

    @FXML
    private void closeButtonMouseExited() {
        closeButton.getStyleClass().remove("closeButtonWhenHovered");
    }
}