package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;

import java.sql.SQLException;
import java.util.List;

public class ReservationManager {
    public void delete(int ReservationId) throws Room_BungalowException {
        try {
            DaoFactory.reservationDao().delete(ReservationId);
        } catch (Room_BungalowException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new Room_BungalowException("NO");
            }
            throw e;
        }

    }

    public Reservation add(Reservation r) throws Room_BungalowException {
        return DaoFactory.reservationDao().add(r);
    }

    public Reservation getById(int id) throws Room_BungalowException {
        return DaoFactory.reservationDao().getById(id);
    }

    public Reservation update(Reservation r) throws Room_BungalowException {
        return DaoFactory.reservationDao().update(r);
    }

    public List<Reservation> getAll() throws Room_BungalowException {
        return DaoFactory.reservationDao().getAll();
    }

    public List<Reservation> getAllForUser(User user) throws SQLException{
        return DaoFactory.reservationDao().getAllForUser(user);
    }
}