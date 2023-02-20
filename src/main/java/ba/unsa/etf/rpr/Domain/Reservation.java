package ba.unsa.etf.rpr.Domain;
import ba.unsa.etf.rpr.Domain.Idable;

import java.sql.Date;
import java.util.Objects;

public class Reservation implements Idable {

    private int id;
    private int userId;
    private int roomId;

    private Date checkIn;
    private Date checkOut;

    public Reservation(int id, int userId, int roomId, Date checkIn, Date checkOut) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reservation() {

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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public java.sql.Date getCheckIn() {
        return (java.sql.Date) checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public java.sql.Date getCheckOut() {
        return (java.sql.Date) checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(roomId, that.roomId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roomId, checkIn, checkOut);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }


    public void setRoomId(Object room_id) {
    }
}

