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

public class AddRoomController {

    @FXML private TextField capacityField, pricePerNightField;

    @FXML private RadioButton statusYesRadioButton, statusNoRadioButton;

    @FXML private Button saveButton, cancelButton;

    private final Utils utils = new Utils();
    private final User user;
    private final RoomBungManager rm = new RoomBungManager();

    private AdminAccountController adminAccountController;

    public AddRoomController(AdminAccountController adminAccountController, User user) {
        this.user = user;
        this.adminAccountController = adminAccountController;
    }

    @FXML
    private void initialize() {
        saveButton.setOnMouseClicked(event -> saveRoom());
        cancelButton.setOnMouseClicked(event -> utils.closeCurrentStage(cancelButton));
    }

    @FXML
    private void saveRoom() {
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

            Room_Bungalow room = new Room_Bungalow();
            room.setCapacity(capacity);
            room.setPricePerNight(pricePerNight);
            room.setStatus(isStatusYesSelected);

            // Dodavanje sobe u bazu
            rm.add(room);

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
