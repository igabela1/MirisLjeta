package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;

public interface UserDao extends Dao<User> {
    User findUsername(String usernameField) throws Room_BungalowException;
}
