package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;

import java.sql.SQLException;

public interface UserDao extends Dao<User> {
    User findUsername(String usernameField) throws Room_BungalowException;
    public int totalUsers() throws SQLException;
}
