package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutCampController {

    @FXML
    private ImageView goBack;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public void initialize() {
        goBack.setOnMouseClicked(event -> {
            // Close current window
            Stage stage = (Stage) goBack.getScene().getWindow();
            stage.close();
            // Open previous window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home/home.fxml"));
                HomeController controller = new HomeController();
                controller.setUser(user);
                fxmlLoader.setController(controller);
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
