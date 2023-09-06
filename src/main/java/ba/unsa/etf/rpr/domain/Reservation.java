package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Reservation class that represents each reservation a user has made
 */
public class Reservation implements Idable {

    private int id;
    private User username;
    private Room_Bungalow roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int total;

    public Reservation() { }
    public Reservation(int total, User username, Room_Bungalow roomId, LocalDate checkIn, LocalDate checkOut) {
        this.username = username;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUsername() { return username; }
    public void setUsername(User username) { this.username = username; }
    public Room_Bungalow getRoomId() { return roomId; }
    public void setRoomId(Room_Bungalow roomId) { this.roomId = roomId; }
    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && total == that.total && username.equals(that.username) && roomId.equals(that.roomId) && checkIn.equals(that.checkIn) && checkOut.equals(that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, roomId, checkIn, checkOut, total);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", username=" + username +
                ", roomId=" + roomId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", total=" + total +
                '}';
    }
}

