package ba.unsa.etf.rpr.Business;
import ba.unsa.etf.rpr.dao.DaoFactory;
        import ba.unsa.etf.rpr.Domain.User;
        import ba.unsa.etf.rpr.Exceptions.Room_BungalowException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserManager {
    public void delete(int UserId) throws Room_BungalowException {
        try {
            DaoFactory.userDao().delete(UserId);
        } catch (Room_BungalowException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new Room_BungalowException("NO");
            }
            throw e;
        }

    }

    public static User add(User r) throws Room_BungalowException {
        return DaoFactory.userDao().add(r);
    }

    public User getById(int id) throws Room_BungalowException {
        return DaoFactory.userDao().getById(id);
    }

    public User update(User r) throws Room_BungalowException {
        return DaoFactory.userDao().update(r);
    }

    public static List<User> getAll() throws Room_BungalowException {
        return DaoFactory.userDao().getAll();
    }

    public User findUsername(String usernameField) throws Room_BungalowException{
        return DaoFactory.userDao().findUsername(usernameField);
    }
    private static final String HASHING_ALGORITHM = "SHA-256";

    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        messageDigest.update(password.getBytes());

        byte[] hashedPassword = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedPassword) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}