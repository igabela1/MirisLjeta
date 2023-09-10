package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeleteRoomController {

    @FXML private Label confirmationLabel;
    @FXML private Button confirmButton, cancelButton;

    private final Utils utils = new Utils();
    private final RoomBungManager rm = new RoomBungManager();
    private final Room_Bungalow roomToDelete;
    private User user = new User();
    private AdminAccountController adminAccountController;

    public DeleteRoomController(AdminAccountController adminAccountController, User user, Room_Bungalow roomToDelete) {
        this.adminAccountController = adminAccountController;
        this.user = user;
        this.roomToDelete = roomToDelete;
    }

    @FXML
    private void initialize() {
        if (roomToDelete == null) {
            // Prikazati upozorenje korisniku da nijedna soba nije izabrana
            showAlert("Please select a room to delete.");
            utils.closeCurrentStage(confirmationLabel);
            return;
        }

        String confirmationMessage = "Are you sure you want to delete Room #" + roomToDelete.getId() + "?";
        confirmationLabel.setText(confirmationMessage);

        confirmButton.setOnAction(event -> deleteRoom());
        cancelButton.setOnAction(event -> utils.closeCurrentStage(confirmationLabel));
    }

    @FXML
    private void deleteRoom() {
        try {
            // Obriši sobu iz baze podataka
            rm.delete(roomToDelete.getId());

            // Ažuriraj tabelu u AdminAccountController-u
            adminAccountController.refreshTables();

            utils.closeCurrentStage(confirmationLabel);
        } catch (Room_BungalowException e) {
            showAlert("An error occurred while deleting the room.");
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
