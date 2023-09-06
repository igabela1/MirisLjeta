package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * The type My profile page controller.
 */
public class MyProfileController {
    @FXML private Label usernameLabel, firstNameLabel, lastNameLabel, emailLabel;
    @FXML private ImageView logOutButton, goBack;
    private final User user;

    public MyProfileController(User u) { this.user = u; }

    private final Utils utils= new Utils();
    public void initialize() {

        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        emailLabel.setText(user.getEmail());
        usernameLabel.setText(user.getUsername());

        String fxmlTitle = (user.getRole() == 1) ? "Admin Panel" : "Home Page";
        String fxmlPath = (user.getRole() == 1) ? "/fxml/Admin/AdminAccount.fxml" : "/fxml/Home.fxml";
        goBack.setOnMouseClicked(event -> utils.changeWindow(goBack, fxmlTitle, fxmlPath, (user.getRole() == 1) ? new AdminAccountController(user) : new HomeController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxml/Home.fxml", new HomeController()));
    }
}
