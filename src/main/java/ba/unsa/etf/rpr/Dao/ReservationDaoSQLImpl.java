package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class ReservationDaoSQLImpl implements ReservationDao {

    //private int numberOfBeds;
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
