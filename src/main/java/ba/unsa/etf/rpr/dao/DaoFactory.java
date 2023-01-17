package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final Room_BungalowDao room_bungalowDao = Room_BungalowDaoSQLImpl.getInstance();

    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoSQLImpl.getInstance();


    private DaoFactory(){
    }

    public static Room_BungalowDao room_bungalowDao(){
        return room_bungalowDao;
    }

    public static ReservationDao reservationDao(){
        return reservationDao;
    }


    public static UserDao userDao(){
        return userDao;
    }

    public static Dao<Object> Room_BungalowDao() {
        return null;
    }
}