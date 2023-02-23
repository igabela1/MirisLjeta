package ba.unsa.etf.rpr.Domain;
import ba.unsa.etf.rpr.Domain.Idable;


import java.time.LocalDate;
import java.util.Objects;

public class Reservation implements Idable {

    private int id;
    private int userId;
    private User username;
    private int roomId;

    private LocalDate checkIn;
    private LocalDate checkOut;
    public Reservation() { }
    public Reservation(int id, int userId, int roomId, LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reservation(LocalDate checkIn, LocalDate checkOut, User username) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.username = username;
    }

    public Reservation(LocalDate checkIn, LocalDate checkOut, Room_Bungalow room, User user2) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }


    //public int getRoomId() {
      //  return roomId;
    //}

    //public void setRoomId(int roomId) {
      //  this.roomId = roomId;
    //}

    public LocalDate getCheckIn() {
        return  checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, checkIn, checkOut, username);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", username=" + username +
                '}';
    }



}

