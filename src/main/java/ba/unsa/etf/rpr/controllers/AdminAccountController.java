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
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.List;

public class AdminAccountController {

    @FXML private Button myProfileButton;
    @FXML private ImageView logOutButton;
    @FXML private Label nameLabel;
    public TableView<User> usersTable;
    public TableView<Room_Bungalow> roomsTable;
    public TableView<Reservation> reservationsTable;
    public TableColumn<User, Integer> idUserColumn;
    public TableColumn<User, String> firstNameColumn;
    public TableColumn<User, String> lastNameColumn;
    public TableColumn<User, String> emailColumn;
    public TableColumn<User, String> usernameColumn;
    public TableColumn<Room_Bungalow, Integer> idRoomColumn;
    public TableColumn<Room_Bungalow, Double> pricePerNightColumn;
    public TableColumn<Room_Bungalow, Integer> capacityColumn;
    public TableColumn<Room_Bungalow, Boolean> statusColumn;
    public TableColumn<Reservation, Integer> reservationIdColumn;
    public TableColumn<Reservation, LocalDate> checkInColumn;
    public TableColumn<Reservation, LocalDate> checkOutColumn;
    public TableColumn<Reservation, Integer> roomIdColumn;
    public TableColumn<Reservation, Double> totalColumn;
    public TableColumn<Reservation, String> userColumn;
    private final User user;
    public AdminAccountController(User u){
        this.user = u;
    }
    private final Utils utils = new Utils();
    public void initialize() {

        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxml/MyProfile.fxml", new MyProfileController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxml/Home.fxml", new HomeController()));
        nameLabel.setText(user.getFirstName());

        idUserColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        idRoomColumn.setCellValueFactory(new PropertyValueFactory<Room_Bungalow, Integer>("id"));
        pricePerNightColumn.setCellValueFactory(new PropertyValueFactory<Room_Bungalow, Double>("pricePerNight"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Room_Bungalow, Integer>("capacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Room_Bungalow, Boolean>("status"));
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("checkOut"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("roomID"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("total"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("userID"));

        refreshTables();
    }

    /**
     * fetch data from DB
     */
    void refreshTables() {
        try {
            // Fetch data from your data source (e.g., UserManager, RoomManager and ReservationManager)
            List<User> userList = UserManager.getAll();
            List<Room_Bungalow> roomList = RoomBungManager.getAll();
            List<Reservation> reservationList = ReservationManager.getAll();


            // Ispis elemenata iz roomList
            System.out.println("Rooms:");
            for (Room_Bungalow room : roomList) {
                System.out.println("ID: " + room.getId() +
                        ", Price Per Night: " + room.getPricePerNight() +
                        ", Status: " + room.getStatus() +
                        ", Capacity: " + room.getCapacity());
            }

            // Ispis elemenata iz reservationList
            System.out.println("Reservations:");
            for (Reservation reservation : reservationList) {
                System.out.println("ID: " + reservation.getId() +
                        ", Check-In: " + reservation.getCheckIn() +
                        ", Check-Out: " + reservation.getCheckOut() +
                        ", Room ID: " + reservation.getRoomId() +
                        ", Total: " + reservation.getTotal() +
                        ", Username: " + reservation.getUsername());
            }

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
