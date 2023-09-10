package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RoomListController {

    @FXML public Label totalLabel;
    @FXML private Button bookNowButton, myReservationsButton, myProfileButton, bookButton;
    @FXML public DatePicker checkIn, checkOut;
    @FXML private ImageView logOutButton;
    public TableView<Room_Bungalow> roomsTable;
    public TableColumn<Room_Bungalow, Integer> capacityColumn;
    public TableColumn<Room_Bungalow, Double> pricePerNightColumn;
    private final User user;
    private final ReservationManager r = new ReservationManager();
    public RoomListController(User u){
        this.user = u;
    }
    private final Utils utils = new Utils();
    public void initialize() {

        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxml/MyProfile.fxml", new MyProfileController(user)));
        myReservationsButton.setOnMouseClicked(event -> utils.changeWindow(myReservationsButton, "My Reservations", "/fxml/ReservationList.fxml", new ReservationListController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxml/Home.fxml", new HomeController()));
        bookButton.setOnMouseClicked(event -> bookRoom());
        pricePerNightColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        refreshTables();

        checkIn.valueProperty().addListener((observable, oldValue, newValue) -> updateTotalLabel());
        checkOut.valueProperty().addListener((observable, oldValue, newValue) -> updateTotalLabel());

    }

    private void updateTotalLabel() {
        Room_Bungalow selectedRoom = roomsTable.getSelectionModel().getSelectedItem();

        if (selectedRoom == null || !selectedRoom.getStatus()) {
            totalLabel.setText(""); // Ako nema izabrane sobe ili soba nije dostupna, postavite totalLabel na prazan string
            return;
        }

        LocalDate checkInDate = checkIn.getValue();
        LocalDate checkOutDate = checkOut.getValue();

        if (checkInDate == null || checkOutDate == null || checkOutDate.isBefore(checkInDate)) {
            totalLabel.setText(""); // Ako su datumi neispravni, postavite totalLabel na prazan string
            return;
        }

        long numNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        if (numNights < 1) {
            totalLabel.setText(""); // Ako je broj noćenja manji od 1, postavite totalLabel na prazan string
            return;
        }

        int totalPrice = (int) (numNights * selectedRoom.getPricePerNight());
        totalLabel.setText(totalPrice + " $");
    }

    /**
     * fetch data from DB
     */
    void refreshTables() {
        try {
            // Fetch data from your data source (e.g., UserManager, RoomManager and ReservationManager)
            List<Room_Bungalow> roomList = RoomBungManager.getAll();

            // Update the table views with the new data
            roomsTable.setItems(FXCollections.observableList(roomList));

            // Refresh the table views to update the UI
            roomsTable.refresh();
        } catch (Room_BungalowException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void bookRoom() {
        try {
            Room_Bungalow selectedRoom = roomsTable.getSelectionModel().getSelectedItem();

            if (selectedRoom == null) {
                showAlert("Please select a room");
                return;
            }

            if (!selectedRoom.getStatus()) {
                showAlert("This room is already booked");
                return;
            }

            LocalDate checkInDate = checkIn.getValue();
            LocalDate checkOutDate = checkOut.getValue();

            if (checkInDate == null || checkOutDate == null || checkOutDate.isBefore(checkInDate)) {
                showAlert("Invalid check-in and check-out dates.");
                return;
            }

            long numNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            if (numNights < 1) {
                showAlert("Please select a check-out date that is after the check-in date and allows for at least one night stay");
                return;
            }

            int totalPrice = (int) (numNights * selectedRoom.getPricePerNight());
            totalLabel.setText(totalPrice + " $");

            Reservation reservation = new Reservation();
            reservation.setCheckIn(checkInDate);
            reservation.setCheckOut(checkOutDate);
            reservation.setTotal(totalPrice);
            reservation.setUsername(user);
            reservation.setRoomId(selectedRoom);

            r.add(reservation);
            selectedRoom.setStatus(false);
        } catch (Exception e) {
            showAlert("An error occurred while booking the room.");
            e.printStackTrace();
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
