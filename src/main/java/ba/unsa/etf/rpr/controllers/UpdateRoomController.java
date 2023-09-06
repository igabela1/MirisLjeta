package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Domain.Room_Bungalow;
import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;
import ba.unsa.etf.rpr.business.RoomBungManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Update room controller.
 */
public class UpdateRoomController {
    /**
     * The Type combo box.
     */
    public ComboBox<String> typeComboBox;
    /**
     * The Capacity field.
     */
    public TextField capacityField;

    public TextField priceField;

    /**
     * The Update button.
     */
    public Button updateButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;
    /**
     * The Room combo box.
     */
    public ComboBox<Room_Bungalow> roomComboBox;

    private boolean okClicked = false;

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
     * @param room the room
     */
    public void setRoom(Room_Bungalow room) {
        this.room = room;
    }
    private List<Room_Bungalow> rooms = new ArrayList<>();
    private Room_Bungalow room;
    private final RoomBungManager rm = new RoomBungManager();
    @FXML
    private void handleOk() throws Room_BungalowException {
        Room_Bungalow selectedRoom = roomComboBox.getSelectionModel().getSelectedItem();
        selectedRoom.setPrice(Integer.parseInt(priceField.getText()));
        selectedRoom.setCapacity(Integer.parseInt(capacityField.getText()));
        rm.update(selectedRoom);
        okClicked = true;
        ((Stage) updateButton.getScene().getWindow()).close();
    }

    @FXML
    private void handleCancel() {((Stage) cancelButton.getScene().getWindow()).close(); }




    public void initialize() throws Room_BungalowException, SQLException {


        List<Room_Bungalow> allRooms = rm.getAll();
        rooms = FXCollections.observableArrayList(allRooms);
        roomComboBox.setItems((ObservableList<Room_Bungalow>) rooms);
        roomComboBox.getSelectionModel().selectFirst();

        roomComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                typeComboBox.setValue(newValue.getType());
                capacityField.setText(String.valueOf(newValue.getCapacity()));

                priceField.setText(String.valueOf(newValue.getPrice()));

            }
        });
    }

    /**
     * Is ok clicked boolean.
     *
     * @return the boolean
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setHotel(Room_Bungalow room) {
        this.room = room;
    }
}

