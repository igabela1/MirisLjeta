package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final Room_BungalowDao Room_BungalowDao = Room_BungalowDaoSQLImpl.getInstance();

    private static final UserDao UserDao = UserDaoSQLImpl.getInstance();
    private static final ReservationDao ReservationDao = ReservationDaoSQLImpl.getInstance();


    private DaoFactory(){
    }

    public static Room_BungalowDao room_bungalowDao(){
        return Room_BungalowDao;
    }

    public static ReservationDao reservationDao(){
        return ReservationDao;
    }


    public static UserDao userDao(){
        return UserDao;
    }



}