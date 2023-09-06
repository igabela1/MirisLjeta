package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {

    @FXML private Button aboutUsButton, signInButton, signUpButton, closeButton;
    private User user = new User();
    private final Utils utils = new Utils();

    public HomeController() {}

    public HomeController(User user) {
        this.user = user;
    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(signInButton, "Sign In", "/fxml/SignIn.fxml", new SignInController());
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(signUpButton, "Sign Up", "/fxml/SignUp.fxml", new SignUpController());
    }

    public void aboutUsOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(aboutUsButton, "About Us", "/fxml/AboutCamp.fxml", new AboutCampController());
    }
}
