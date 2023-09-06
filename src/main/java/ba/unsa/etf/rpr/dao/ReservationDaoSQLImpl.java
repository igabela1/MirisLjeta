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
        super("RESERVATIONS");
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
            reservation.setUsername(DaoFactory.userDao().getById(rs.getInt("user_id")));
            reservation.setCheckIn(rs.getDate("checkIn").toLocalDate());
            reservation.setCheckOut(rs.getDate("checkOut").toLocalDate());
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
        item.put("user_id", object.getUsername().getId());
        return item;
    }


    public List<Reservation> getAllForUser(User user){
        List<Reservation> userReservations = new ArrayList<>();
        // Connect to the database
        try{
            // Prepare a statement to execute the query
            String query = "SELECT * FROM RESERVATIONS WHERE user_id = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setObject(1, user.getId());
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                LocalDate checkIn = resultSet.getDate("checkIn").toLocalDate();
                LocalDate checkOut = resultSet.getDate("checkOut").toLocalDate();
                int user_id = resultSet.getInt("user_id");
                int room_id = resultSet.getInt("room_id");
                Room_Bungalow room = r.getById(room_id);
                User user2 = u.getById(user_id);
                Reservation reservation = new Reservation(checkIn, checkOut, room, user2);
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

/*package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Domain.Reservation;
import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao {
    private static ReservationDaoSQLImpl instance = null;
    private ReservationDaoSQLImpl() {
        super("RESERVATIONS");
    }

    public static ReservationDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ReservationDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Reservation row2object(ResultSet rs) throws Room_BungalowException{
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setRoomId(DaoFactory.room_bungalowDao().getById(rs.getInt("room_id")));
            reservation.setCheckIn(rs.getDate("checkIn"));
            reservation.setCheckOut(rs.getDate("checkOut"));
            return reservation;
        } catch (Exception e) {
            throw new Room_BungalowException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Reservation object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("checkIn",object.getCheckIn());
        item.put("checkOut",object.getCheckOut());
        item.put("user_id", object.getId());
        item.put("room_id",object.getRoomId());
        return item;
    }
}
   /* private int numberOfBeds;
    private Connection connection;
    private String tableName;

    public ReservationDaoSQLImpl(String tableName) {
        try{
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public ReservationDaoSQLImpl() {

    }
*/
/*
    public static ReservationDao getInstance() {
    }

    public Connection getConnection(){
        return this.connection;
    }    @Override
    public Reservation add(Reservation item) {
        return null;
    }

    @Override
    public Reservation update(Reservation item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    // @Override
    //public List<Reservation> searchByNumberOfBeds() {
    //  return null;
    //}



}
*/