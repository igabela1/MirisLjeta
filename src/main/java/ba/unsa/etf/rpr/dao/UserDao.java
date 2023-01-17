package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.Domain.User;
import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;

public interface UserDao extends Dao<User> {
    User findUsername(String usernameField) throws Room_BungalowException;
}
