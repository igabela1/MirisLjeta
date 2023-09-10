package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room_Bungalow;

import java.sql.SQLException;

public interface Room_BungalowDao extends Dao<Room_Bungalow> {

    public int totalRooms() throws SQLException;
}