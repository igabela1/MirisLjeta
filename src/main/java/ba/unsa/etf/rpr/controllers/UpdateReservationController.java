package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UpdateReservationController {
    @FXML private DatePicker checkInDatePicker, checkOutDatePicker;
    @FXML private Button updateButton;
    @FXML private ComboBox<String> roomComboBox;

    private final Utils utils = new Utils();
    private final ReservationManager r = new ReservationManager();
    private final RoomBungManager rm = new RoomBungManager();
    private final User user;
    private AdminAccountController adminAccountController;
    private Reservation reservationToUpdate;
    public UpdateReservationController(AdminAccountController adminAccountController, User user, Reservation r) {
        this.adminAccountController = adminAccountController;
        this.user = user;
        this.reservationToUpdate = r;
    }

    @FXML
    private void initialize() throws Room_BungalowException {
        updateButton.setOnAction(event -> updateReservation());

        List<Room_Bungalow> rooms = rm.getAll();

        // Extract room IDs and populate the ComboBox
        for (Room_Bungalow room : rooms) {
            roomComboBox.getItems().add(String.valueOf(room.getId()));
        }

        // Set initial values for the fields based on the reservationToUpdate
        checkInDatePicker.setValue(reservationToUpdate.getCheckIn());
        checkOutDatePicker.setValue(reservationToUpdate.getCheckOut());
        roomComboBox.setValue(String.valueOf(reservationToUpdate.getRoomId().getId()));
    }

    private void updateReservation() {
        try {
            // Validacija unosa
            String checkInText = checkInDatePicker.getValue() != null ? checkInDatePicker.getValue().toString() : null;
            String checkOutText = checkOutDatePicker.getValue() != null ? checkOutDatePicker.getValue().toString() : null;
            String selectedRoomId = roomComboBox.getValue().toString();

            if (checkInText == null || checkOutText == null || selectedRoomId == null) {
                showAlert("Please select a room and valid check-in/check-out dates.");
                return;
            }

            LocalDate checkInDate = LocalDate.parse(checkInText);
            LocalDate checkOutDate = LocalDate.parse(checkOutText);

            if (!checkOutDate.isAfter(checkInDate)) {
                showAlert("Invalid check-in and check-out dates.");
                return;
            }

            int numNights = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            System.out.println("Num of nights: "+numNights);
            if (numNights < 1) {
                showAlert("Please select a check-out date that is after the check-in date and allows for at least one night stay.");
                return;
            }

            int totalPrice = numNights * rm.getById(Integer.parseInt(selectedRoomId)).getPricePerNight();
            // Calculate the total based on room price and number of nights

            reservationToUpdate.setCheckIn(checkInDate);
            reservationToUpdate.setCheckOut(checkOutDate);
            reservationToUpdate.setTotal(totalPrice);
            reservationToUpdate.setUsername(user);
            System.out.println("ID SOBE JE "+selectedRoomId);
            // Get the Room_Bungalow object based on the selected room ID
            Room_Bungalow selectedRoom = rm.getById(Integer.parseInt(selectedRoomId));
            reservationToUpdate.setRoomId(selectedRoom);

            r.update(reservationToUpdate);

            // Ažuriranje tabele u AdminAccountController-u
            adminAccountController.refreshTables();

            // Close the dialog or perform other actions as needed
            utils.closeCurrentStage(updateButton);
        } catch (DateTimeParseException e) {
            showAlert("Invalid date format. Please enter valid dates.");
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
