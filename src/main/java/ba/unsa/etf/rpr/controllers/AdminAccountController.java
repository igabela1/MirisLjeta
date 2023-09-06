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

        System.out.println("usla u admin account controller");

        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxml/MyProfile.fxml", new MyProfileController(user)));

        System.out.println("prosla my profile button");

        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxml/Home.fxml", new HomeController()));

        System.out.println("pred tabele sam");

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

        System.out.println("nakon kolona sam a prije refresh tables");

        refreshTables();
    }

    /**
     * fetch data from DB
     */
    void refreshTables() {
        try {
            System.out.println("usla u refresh tables");

            // Fetch data from your data source (e.g., UserManager, RoomManager and ReservationManager)
            List<User> userList = UserManager.getAll();
            List<Room_Bungalow> roomList = RoomBungManager.getAll();
            List<Reservation> reservationList = ReservationManager.getAll();

            System.out.println("prikupila podatke od userlist i ostalih listi");
            System.out.println(userList.toString());
            System.out.println(roomList.toString());
            System.out.println(reservationList.toString());

            // Update the table views with the new data
            usersTable.setItems(FXCollections.observableList(userList));
            roomsTable.setItems(FXCollections.observableList(roomList));
            reservationsTable.setItems(FXCollections.observableList(reservationList));

            System.out.println("updateovala tabele");

            // Refresh the table views to update the UI
            usersTable.refresh();
            roomsTable.refresh();
            reservationsTable.refresh();

            System.out.println("refreshala tabele");
        } catch (Room_BungalowException e) {
            throw new RuntimeException(e);
        }
    }
}
