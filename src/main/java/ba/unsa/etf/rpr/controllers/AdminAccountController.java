package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class AdminAccountController {

    @FXML private Button myProfileButton, addRoomButton, updateRoomButton, deleteRoomButton, addReservationButton, updateReservationButton, deleteReservationButton;
    @FXML private ImageView logOutButton;
    @FXML private Label nameLabel, totalIncome, totalRooms;
    public TableView<User> usersTable;
    public TableView<Room_Bungalow> roomsTable;
    public TableView<Reservation> reservationsTable;
    public TableColumn<User, Integer> idUserColumn;
    public TableColumn<User, String> firstNameColumn, lastNameColumn, emailColumn, usernameColumn;
    public TableColumn<Room_Bungalow, Integer> idRoomColumn, capacityColumn;
    public TableColumn<Room_Bungalow, Double> pricePerNightColumn;
    public TableColumn<Room_Bungalow, Boolean> statusColumn;
    public TableColumn<Reservation, Integer> reservationIdColumn, roomIdColumn;
    public TableColumn<Reservation, LocalDate> checkInColumn, checkOutColumn;
    public TableColumn<Reservation, Double> totalColumn;
    public TableColumn<Reservation, String> userColumn;
    private final User user;
    private final ReservationManager r = new ReservationManager();
    private final RoomBungManager rm = new RoomBungManager();
    public AdminAccountController(User u){
        this.user = u;
    }
    private final Utils utils = new Utils();
    public void initialize() throws SQLException {

        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxml/MyProfile.fxml", new MyProfileController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxml/Home.fxml", new HomeController()));
        nameLabel.setText(user.getFirstName());

        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        idRoomColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pricePerNightColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        // statusColumn.setCellValueFactory(new PropertyValueFactory<Room_Bungalow, Boolean>("status"));
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(column -> new TableCell<Room_Bungalow, Boolean>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Boolean statusValue = getTableView().getItems().get(getIndex()).getStatus();
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    imageView.setImage(statusValue ? new Image("/images/trueIcon.png") : new Image("/images/falseIcon.png"));
                    setGraphic(imageView);
                }
            }
        });

        refreshTables();

        totalIncome.setText(r.totalIncome() +" $");
        totalRooms.setText(String.valueOf(rm.totalRooms()));

        updateRoomButton.setOnMouseClicked(event -> {
            Room_Bungalow selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
            if (selectedRoom == null) {
                // Prikazati upozorenje korisniku da nijedna soba nije izabrana
                showAlert("Please select a room to update.");
            } else {
                utils.openDialog("Update Room", "/fxml/Admin/Room/updateRoomDialog.fxml", new UpdateRoomController(this, user, selectedRoom));
            }
        });

        deleteRoomButton.setOnMouseClicked(event -> {
            Room_Bungalow selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
                if (selectedRoom == null) {
                    // Prikazati upozorenje korisniku da nijedna soba nije izabrana
                    showAlert("Please select a room to update.");
                } else {
                    utils.openDialog("Delete Room", "/fxml/Admin/Room/deleteRoomDialog.fxml", new DeleteRoomController(this, user, selectedRoom));
                }
        });

        updateReservationButton.setOnMouseClicked(event -> {
            Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();

            if (selectedReservation == null) {
                // Prikazati upozorenje korisniku da nijedna rezervacija nije izabrana
                showAlert("Please select a reservation to update.");
            } else {
                // Assuming you have a method to open a dialog for reservation update
                utils.openDialog("Update Reservation", "/fxml/Admin/Reservation/updateReservationDialog.fxml", new UpdateReservationController(this, user, selectedReservation));
            }
        });

        deleteReservationButton.setOnMouseClicked(event -> {
            Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();

            if (selectedReservation == null) {
                // Prikazati upozorenje korisniku da nijedna rezervacija nije izabrana
                showAlert("Please select a reservation to delete.");
            } else {
                // Assuming you have a method to open a dialog for reservation deletion
                utils.openDialog("Delete Reservation", "/fxml/Admin/Reservation/deleteReservationDialog.fxml", new DeleteReservationController(this, user, selectedReservation));
            }
        });


        addRoomButton.setOnMouseClicked(event -> utils.openDialog("Add Room", "/fxml/Admin/Room/addRoomDialog.fxml", new AddRoomController(this, user)));
        addReservationButton.setOnMouseClicked(event -> utils.openDialog("Add Reservation", "/fxml/Admin/Reservation/addReservationDialog.fxml", new AddReservationController(this, user)));

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * fetch data from DB
     */
    public void refreshTables() {
        try {
            // Fetch data from your data source (e.g., UserManager, RoomManager and ReservationManager)
            List<User> userList = UserManager.getAll();
            List<Room_Bungalow> roomList = RoomBungManager.getAll();
            List<Reservation> reservationList = ReservationManager.getAll();

            // Update the table views with the new data
            usersTable.setItems(FXCollections.observableList(userList));
            roomsTable.setItems(FXCollections.observableList(roomList));
            reservationsTable.setItems(FXCollections.observableList(reservationList));

            // Refresh the table views to update the UI
            usersTable.refresh();
            roomsTable.refresh();
            reservationsTable.refresh();
        } catch (Room_BungalowException e) {
            throw new RuntimeException(e);
        }
    }

}
