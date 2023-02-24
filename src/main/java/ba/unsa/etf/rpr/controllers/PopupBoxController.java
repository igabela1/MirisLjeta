package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * The type Popup box controller.
 */
public class PopupBoxController {
    @FXML
    private Label messageLabel;

    @FXML
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
