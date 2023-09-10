package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Room_BungalowDaoSQLImpl extends AbstractDao<Room_Bungalow> implements Room_BungalowDao {
    private static Room_BungalowDaoSQLImpl instance = null;
    private Room_BungalowDaoSQLImpl() {
        super("rooms");
    }


    public static Room_BungalowDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new Room_BungalowDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Room_Bungalow row2object(ResultSet rs) throws Room_BungalowException {
        try {
            Room_Bungalow room = new Room_Bungalow();
            room.setId(rs.getInt("id"));
            room.setCapacity(rs.getInt("capacity"));
            room.setStatus(rs.getBoolean("status"));
            room.setPricePerNight(rs.getInt("pricePerNight"));
            return room;
        } catch (Exception e) {
            throw new Room_BungalowException(e.getMessage(), e);
        }
    }


    @Override
    public Map<String, Object> object2row(Room_Bungalow object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("capacity", object.getCapacity());
        item.put("status",object.getStatus());
        item.put("pricePerNight", object.getPricePerNight());
        return item;
    }

    public int totalRooms() throws SQLException {
        int total = 0;
        String query = "SELECT count(id) AS total_rooms FROM rooms";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total_rooms");
        }
        return total;
    }
}








