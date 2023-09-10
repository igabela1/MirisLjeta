package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Room class that represents a room/bungalow as a part of the autocamp
 */
public class Room_Bungalow implements Idable {

    private int id;
    private int pricePerNight;
    private int capacity;
    private Boolean status;


    public Room_Bungalow(int id, int pricePerNight,  int capacity, Boolean status) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.status = status;

    }

    public Room_Bungalow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room_Bungalow that = (Room_Bungalow) o;
        return id == that.id && pricePerNight == that.pricePerNight && capacity == that.capacity && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pricePerNight, capacity, status);
    }

    @Override
    public String toString() {
        return "Room_Bungalow{" +
                "id=" + id +
                ", pricePerNight=" + pricePerNight +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }
}
