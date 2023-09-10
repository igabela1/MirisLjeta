package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.dao.Room_BungalowDao;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoomMockTest {

    private Room_Bungalow room = new Room_Bungalow(1,100,2,true);

    private RoomBungManager rm;
    @Mock private Room_BungalowDao rmdao;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Postavljanje ponašanja mock objekta
        rm = new RoomBungManager();
    }

    @Test
    void constructorTest() {
        assertEquals(1, room.getId());
        assertEquals(100, room.getPricePerNight());
        assertEquals(2, room.getCapacity());
        assertEquals(true, room.getStatus());
    }

    @Test
    void equalsTest() {
        Room_Bungalow otherRoom = new Room_Bungalow(1, 100, 2, true);
        // Provjera jednakosti sa drugom sobom istih svojstava
        assertEquals(otherRoom, room);
    }

    @Test
    void addTest() throws Room_BungalowException {
        rmdao.add(room);
        verify(rmdao).add(room);
    }

    @Test
    void updateTest() throws Room_BungalowException {
        room.setStatus(false);
        rmdao.update(room);
        verify(rmdao).update(room);
    }

}
