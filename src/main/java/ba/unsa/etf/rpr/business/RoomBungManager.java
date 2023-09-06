package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.dao.DaoFactory;
        import ba.unsa.etf.rpr.Domain.Room_Bungalow;
        import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;

import java.util.List;

public class RoomBungManager {
    public void delete(int RoomId) throws Room_BungalowException {
        try {
            DaoFactory.room_bungalowDao().delete(RoomId);
        } catch (Room_BungalowException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new Room_BungalowException("NO");
            }
            throw e;
        }

    }

    public static Room_Bungalow add(Room_Bungalow r) throws Room_BungalowException {
        return DaoFactory.room_bungalowDao().add(r);
    }

    public Room_Bungalow getById(int id) throws Room_BungalowException {
        return DaoFactory.room_bungalowDao().getById(id);
    }

    public Room_Bungalow update(Room_Bungalow r) throws Room_BungalowException {
        return DaoFactory.room_bungalowDao().update(r);
    }

    public static List<Room_Bungalow> getAll() throws Room_BungalowException {
        return DaoFactory.room_bungalowDao().getAll();
    }

}