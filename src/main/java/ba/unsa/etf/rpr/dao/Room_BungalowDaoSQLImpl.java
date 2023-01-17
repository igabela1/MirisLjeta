package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Domain.Room_Bungalow;
import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class Room_BungalowDaoSQLImpl extends AbstractDao<Room_Bungalow> implements Room_BungalowDao {
    private static Room_BungalowDaoSQLImpl instance = null;
    private Room_BungalowDaoSQLImpl() {
        super("ROOMS");
    }


    public static Room_BungalowDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new Room_BungalowDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Room_Bungalow row2object(ResultSet rs) throws Room_BungalowException {
        try {
            Room_Bungalow room = new Room_Bungalow();
            room.setId(rs.getInt("id"));
            return room;
        } catch (Exception e) {
            throw new Room_BungalowException(e.getMessage(), e);
        }
    }


    @Override
    public Map<String, Object> object2row(Room_Bungalow object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());

        return item;
    }
}

   /* private Connection connection;
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

    public static Room_BungalowDao getInstance() {
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
*/








