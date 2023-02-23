package ba.unsa.etf.rpr.Domain;
import ba.unsa.etf.rpr.Domain.Idable;

import java.util.Objects;

public class Room_Bungalow implements Idable {

    private int id;

    private boolean isAvailable;
    private String roomDescription;


    public Room_Bungalow(int id, boolean isAvailable, String roomDescription, int numberOfBeds) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.roomDescription = roomDescription;

    }

    public Room_Bungalow() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getisAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room_Bungalow room = (Room_Bungalow) o;
        return id == room.id && isAvailable == room.isAvailable  && roomDescription.equals(room.roomDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isAvailable, roomDescription);
    }

    @Override
    public String toString() {
        return "Room_Bungalow{" +
                "id=" + id +
                ", isAvailable=" + isAvailable +
                ", roomDescription='" + roomDescription + '\'' + '}';
    }
}
