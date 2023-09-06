package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    @Test
    public void testUserConstructor() {
        User user = new User("Ilhana", "Gabela", "ilhana.gabela@example.com", 1, "ilhanag1", "123");
        assertEquals("Ilhana", user.getFirstName());
        assertEquals("Gabela", user.getLastName());
        assertEquals("ilhana.gabela@example.com", user.getEmail());
        assertEquals(1, user.getRole());
        assertEquals("ilhanag1", user.getUsername());
        assertEquals("123", user.getPassword());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setFirstName("Faris");
        user.setLastName("Gabela");
        user.setEmail("Faris.Gabela@example.com");
        user.setRole(2);
        user.setUsername("Farisg");
        user.setPassword("fg123");

        assertEquals("Faris", user.getFirstName());
        assertEquals("Gabela", user.getLastName());
        assertEquals("Faris.Gabela@example.com", user.getEmail());
        assertEquals(2, user.getRole());
        assertEquals("Farisg", user.getUsername());
        assertEquals("fg123", user.getPassword());
    }
}
