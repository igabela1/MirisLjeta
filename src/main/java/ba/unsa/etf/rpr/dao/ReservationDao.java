package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDao extends Dao<Reservation> {
    public List<Reservation> getAllForUser(User user) throws SQLException;
}

