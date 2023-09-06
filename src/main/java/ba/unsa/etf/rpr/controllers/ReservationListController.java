package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.sql.Date;
import java.sql.SQLException;

public class ReservationListController {

    @FXML private Button aboutUsButton, homeButton, myProfileButton, closeButton;
    @FXML private ImageView logOutButton;
    private final User user;

    public ReservationListController(User u){this.user=u;}
    @FXML
    public TableView<Reservation> ReservationListTable;
    @FXML public TableColumn<Reservation, String> roomColumn;
    @FXML public TableColumn<Reservation, Date> checkInColumn, checkOutColumn;
    @FXML public TableColumn<Reservation, Integer> priceColumn;

    private final ReservationManager r = new ReservationManager();
    private final RoomBungManager rm = new RoomBungManager();
    private final Utils utils = new Utils();

    void refreshTable(){
        try {
            ReservationListTable.setItems(FXCollections.observableList(r.getAllForUser(user)));
            ReservationListTable.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        refreshTable();

    }
}