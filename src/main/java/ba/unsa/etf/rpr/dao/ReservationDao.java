package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.Domain.Reservation;
import ba.unsa.etf.rpr.Domain.User;

import java.sql.SQLException;
import java.util.List;

import ba.unsa.etf.rpr.Domain.Reservation;

public interface ReservationDao extends Dao<Reservation> {
    public List<Reservation> getAllForUser(User user) throws SQLException;
}

