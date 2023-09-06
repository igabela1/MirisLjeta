package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room_Bungalow;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Room_BungalowDaoTest {
    @Test
    public void testGettersAndSetters() {
        Room_Bungalow room = new Room_Bungalow(2, 0, 1, true);
        room.setId(1);
        // room.setCapacity(2);


        assertEquals(1, room.getId());
        // assertEquals(2, room.getCapacity());

    }
    /*
        @Test
        public void testEquals() {
            Room_Bungalow room1 = new Room_Bungalow( 2, 0, 1, 3);
            Room_Bungalow room2 = new Room_Bungalow( 2, 0, 1, 4);

            assertEquals(room1, room2);
        }
    */
    @Test
    public void testHashCode() {
        Room_Bungalow room1 = new Room_Bungalow( 2, 1, 1, false);
        Room_Bungalow room2 = new Room_Bungalow( 2, 1, 1, false);

        assertEquals(room1.hashCode(), room2.hashCode());
    }
}
