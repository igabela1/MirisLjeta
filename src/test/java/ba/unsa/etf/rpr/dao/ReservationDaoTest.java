package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservationDaoTest {

    @Test
    void gettersAndSettersTest() {
        LocalDate checkIn = LocalDate.of(2022, Month.FEBRUARY, 15);
        LocalDate checkOut = LocalDate.of(2022, Month.FEBRUARY, 23);
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        reservation.setRoomId(new Room_Bungalow(2, 0, 1, false));
        reservation.setUsername(new User());

        assertEquals(1, reservation.getId());
        assertEquals(LocalDate.of(2022, Month.FEBRUARY, 15), reservation.getCheckIn());
        assertEquals(LocalDate.of(2022, Month.FEBRUARY, 23), reservation.getCheckOut());
        assertNotNull(reservation.getRoomId());
        assertNotNull(reservation.getUsername());
    }

}

