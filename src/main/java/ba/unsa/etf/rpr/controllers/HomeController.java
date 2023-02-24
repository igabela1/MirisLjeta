package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Domain.User;
import com.sun.javafx.scene.control.InputField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.image.ImageView;
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
    @FXML
    private Button bookButton = new Button();
    @FXML
    public Button reservationsButton;
    private User user = new User();
    @FXML
    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    public User getUser() {
        return user;
    }
   public HomeController() {
    }
    public HomeController(User finalUser){
        this.user = finalUser;
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
       /*// reservationsButton.setOnAction(event -> {
            // Close the current window
         //   Stage stage = (Stage) reservationsButton.getScene().getWindow();
           // stage.close();
            // Open the about us page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/MyProfile.fxml"));
               // ReservationListController controller = new ReservationListController(user);
                //fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();
                Stage stage2 = new Stage();
               // stage2.getIcons().add(new Image("images/"));
                stage2.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage2.setScene(scene);
                stage2.show();
              //  stage.hide();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
}



