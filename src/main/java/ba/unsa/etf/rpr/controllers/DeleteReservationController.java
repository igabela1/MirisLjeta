package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeleteReservationController {

    @FXML private Label confirmationLabel;
    @FXML private Button confirmButton, cancelButton;

    private final Utils utils = new Utils();
    private final ReservationManager r = new ReservationManager();
    private final Reservation reservationToDelete;
    private AdminAccountController adminAccountController;
    private User user = new User();

    public DeleteReservationController(AdminAccountController adminAccountController, User u, Reservation reservationToDelete) {
        this.adminAccountController = adminAccountController;
        this.reservationToDelete = reservationToDelete;
        this.user = u;
    }

    @FXML
    private void initialize() {
        if (reservationToDelete == null) {
            // Display a warning to the user that no reservation is selected
            showAlert("Please select a reservation to delete.");
            utils.closeCurrentStage(confirmationLabel);
            return;
        }

        String confirmationMessage = "Are you sure you want to delete Reservation #" + reservationToDelete.getId() + "?";
        confirmationLabel.setText(confirmationMessage);

        confirmButton.setOnAction(event -> deleteReservation());
        cancelButton.setOnAction(event -> utils.closeCurrentStage(cancelButton));
    }

    @FXML
    private void deleteReservation() {
        try {
            // Delete the reservation from the database (you should implement this logic)
            r.delete(reservationToDelete.getId());

            // Update the table in AdminAccountController
            adminAccountController.refreshTables();

            utils.closeCurrentStage(confirmButton);
        } catch (Exception e) {
            showAlert("An error occurred while deleting the reservation.");
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
