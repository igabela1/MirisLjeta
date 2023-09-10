package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class UpdateRoomController {

    @FXML private TextField capacityField, pricePerNightField;
    @FXML private RadioButton statusYesRadioButton, statusNoRadioButton;
    @FXML private Button saveButton, cancelButton;

    private final Utils utils = new Utils();
    private final RoomBungManager rm = new RoomBungManager();
    private final Room_Bungalow roomToUpdate;
    private AdminAccountController adminAccountController;
    private User user = new User();

    public UpdateRoomController(AdminAccountController adminAccountController, User user, Room_Bungalow room) {
        this.adminAccountController = adminAccountController;
        this.user = user;
        this.roomToUpdate = room;
    }

    @FXML
    private void initialize() {
        if (roomToUpdate == null) {
            // Prikazati upozorenje korisniku da nijedna soba nije izabrana
            showAlert("Please select a room to update.");
            utils.closeCurrentStage(saveButton);
            return;
        }

        // Postavite polja za unos informacija o sobi na trenutne vrednosti sobe
        capacityField.setText(String.valueOf(roomToUpdate.getCapacity()));
        pricePerNightField.setText(String.valueOf(roomToUpdate.getPricePerNight()));
        statusYesRadioButton.setSelected(roomToUpdate.getStatus());
        statusNoRadioButton.setSelected(!roomToUpdate.getStatus());

        saveButton.setOnAction(event -> updateRoom());
        cancelButton.setOnAction(event -> utils.closeCurrentStage(cancelButton));
    }


    @FXML
    private void updateRoom() {
        try {
            // Validacija unosa
            String capacityText = capacityField.getText().trim();
            String pricePerNightText = pricePerNightField.getText().trim();

            if (capacityText.isEmpty() || pricePerNightText.isEmpty()) {
                showAlert("Please fill in all fields.");
                return;
            }

            int capacity = Integer.parseInt(capacityText);
            int pricePerNight = Integer.parseInt(pricePerNightText);

            if (capacity < 0 || pricePerNight < 0) {
                showAlert("Capacity and price per night must be positive numbers.");
                return;
            }

            boolean isStatusYesSelected = statusYesRadioButton.isSelected();
            boolean isStatusNoSelected = statusNoRadioButton.isSelected();

            if (isStatusYesSelected == isStatusNoSelected) {
                showAlert("Please select either Available or Not Available status.");
                return;
            }

            // Ažuriranje informacija o sobi
            roomToUpdate.setCapacity(capacity);
            roomToUpdate.setPricePerNight(pricePerNight);
            roomToUpdate.setStatus(isStatusYesSelected);

            // Ažuriranje sobe u bazi podataka
            rm.update(roomToUpdate);

            // Ažuriranje tabele u AdminAccountController-u
            adminAccountController.refreshTables();

            utils.closeCurrentStage(saveButton);
        } catch (NumberFormatException | Room_BungalowException e) {
            showAlert("Invalid input. Please enter valid numeric values.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
