package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.sql.Date;
import java.sql.SQLException;

public class ReservationListController {

    @FXML private Button bookNowButton, myProfileButton;
    @FXML private ImageView logOutButton;
    private final User user;

    public ReservationListController(User u){this.user=u;}
    @FXML
    public TableView<Reservation> reservationsTable;
    @FXML public TableColumn<Reservation, String> roomIdColumn;
    @FXML public TableColumn<Reservation, Date> checkInColumn, checkOutColumn;
    @FXML public TableColumn<Reservation, Integer> totalColumn;

    private final ReservationManager r = new ReservationManager();
    private final RoomBungManager rm = new RoomBungManager();
    private final Utils utils = new Utils();

    void refreshTable(){
        try {
            reservationsTable.setItems(FXCollections.observableList(r.getAllForUser(user)));
            reservationsTable.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() throws SQLException {
        bookNowButton.setOnMouseClicked(event -> utils.changeWindow(bookNowButton, "Book Now", "/fxml/RoomList.fxml", new RoomListController(user)));
        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxml/MyProfile.fxml", new MyProfileController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxml/Home.fxml", new HomeController()));

        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        refreshTable();
    }
}