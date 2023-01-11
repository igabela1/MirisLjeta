package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Domain.Room_Bungalow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class Room_BungalowDaoSQLImpl implements Room_BungalowDao {


    private Connection connection;
    private String tableName;

    public Room_BungalowDaoSQLImpl(String tableName) {
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

    public Connection getConnection(){
        return this.connection;
    }

    @Override
    public Room_Bungalow add(Room_Bungalow item) {
        return null;
    }

    @Override
    public Room_Bungalow update(Room_Bungalow item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Room_Bungalow> getAll() {
        return null;
    }



}





