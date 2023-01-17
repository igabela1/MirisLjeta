package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.Domain.User;
import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private static UserDaoSQLImpl instance = null;
    private UserDaoSQLImpl() {
        super("USERS");
    }


    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public User row2object(ResultSet rs) throws Room_BungalowException {
        try {
            User person = new User();
            person.setId(rs.getInt("id"));
            person.setUsername(rs.getString("username"));
            //person.setFirstName(rs.getString("first_name"));
           // person.setLastName(rs.getString("last_name"));
            person.setEmail(rs.getString("email"));
           // person.setRole(rs.getInt("role"));
            person.setPassword(rs.getString("password"));
            return person;
        } catch (Exception e) {
            throw new Room_BungalowException(e.getMessage(), e);
        }
    }


    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("username", object.getUsername());
       // item.put("first_name", object.getFirstName());
        //item.put("last_name", object.getLastName());
        item.put("email", object.getEmail());
        //item.put("role", object.getRole());
        item.put("password", object.getPassword());
        return item;
    }

    @Override
    public User findUsername(String usernameField) throws Room_BungalowException{
        String insert = "SELECT id from USERS where username='" + usernameField +"'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return getById(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
    /*private Connection connection;
    private String tableName;

    public UserDaoSQLImpl(String tableName) {
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

    public static UserDao getInstance() {
    }

    public Connection getConnection(){
        return this.connection;
    }

    @Override
    public User add(User item) {
        return null;
    }

    @Override
    public User update(User item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public String toString() {
        return "UserDaoSQLImpl{}";
    }

    public UserDaoSQLImpl() {
    }

}
*/