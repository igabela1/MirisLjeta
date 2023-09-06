package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.business.UserManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 */
public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao {

    private static ReservationDaoSQLImpl instance = null;
    private final RoomBungManager r = new RoomBungManager();
    private final UserManager u = new UserManager();
    private ReservationDaoSQLImpl() {
        super("reservations");
    }

    public static ReservationDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ReservationDaoSQLImpl();
        return instance;
    }

    /**
     * Remove instance.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Reservation row2object(ResultSet rs) throws Room_BungalowException{
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setUsername(DaoFactory.userDao().getById(rs.getInt("userID")));
            reservation.setCheckIn(rs.getDate("checkIn").toLocalDate());
            reservation.setCheckOut(rs.getDate("checkOut").toLocalDate());
            reservation.setTotal(rs.getInt("total"));
            reservation.setRoomId(DaoFactory.room_bungalowDao().getById(rs.getInt("room_id")));
            return reservation;
        } catch (Exception e) {
            throw new Room_BungalowException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Reservation object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("checkIn",object.getCheckIn());
        item.put("checkOut",object.getCheckOut());
        item.put("userID", object.getUsername().getId());
        item.put("roomID",object.getRoomId().getId());
        item.put("total",object.getTotal());
        return item;
    }

    @Override
    public int totalIncome() throws SQLException {
        int totalIncome = 0;
        String query = "SELECT SUM(total) AS total_price FROM reservations";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) totalIncome = result.getInt("total_price");
        }
        return totalIncome;
    }

    public List<Reservation> getAllForUser(User user){
        List<Reservation> userReservations = new ArrayList<>();
        // Connect to the database
        try{
            // Prepare a statement to execute the query
            String query = "SELECT * FROM reservations WHERE userID = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setObject(1, user.getId());
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                LocalDate checkIn = resultSet.getDate("checkIn").toLocalDate();
                LocalDate checkOut = resultSet.getDate("checkOut").toLocalDate();
                int user_id = resultSet.getInt("userID");
                int room_id = resultSet.getInt("roomID");
                Room_Bungalow room = r.getById(room_id);
                User user2 = u.getById(user_id);
                int total = resultSet.getInt("total");
                Reservation reservation = new Reservation(total, user2, room, checkIn, checkOut);
                userReservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Room_BungalowException e) {
            throw new RuntimeException(e);
        }
        // Return the list of hotels
        return userReservations;
    }
}