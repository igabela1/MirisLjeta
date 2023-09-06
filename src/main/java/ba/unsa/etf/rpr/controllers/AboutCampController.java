package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class AboutCampController {

    @FXML private ImageView goBack;
    private final Utils utils = new Utils();

    public void initialize(){
        goBack.setOnMouseClicked(event -> utils.changeWindow(goBack, "Home Page", "/fxml/Home.fxml", new HomeController()));
    }

}
