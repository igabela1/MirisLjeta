package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * The type Add room dialog controller.
 */
public class AddRoomController {


    /**
     * The Type combo box.
     */
    public ComboBox typeComboBox;
    @FXML
    private TextField capacityField;
    @FXML
    private TextField priceField;

    /**
     * The Save button.
     */
    public Button saveButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;

    private Room_Bungalow room = new Room_Bungalow();

    /**
     * Instantiates a new Add room dialog controller.
     *
     * @throws Room_BungalowException
     */
    public AddRoomController() throws Room_BungalowExceptionn {
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public Room_Bungalow getRoom() {
        return room;
    }

    /**
     * Sets room.
     *
     * @param r the r
     */
    public void setRoom(Room_Bungalow r) {
        this.room = r;
    }

    private boolean okClicked = false;

    /**
     * Is ok clicked boolean.
     *
     * @return the boolean
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    /**
     * Save room.
     */
    @FXML
    public void saveRoom(){
        room.setPrice(Integer.parseInt(priceField.getText()));
        room.setStatus(1);
        System.out.println(typeComboBox.getValue());
        System.out.println(room.toString());
        okClicked = true;
        // close the dialog
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    /**
     * Cancel room.
     */
    @FXML
    public void cancelRoom() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }


    private class Room_BungalowExceptionn extends Exception {
    }
}
