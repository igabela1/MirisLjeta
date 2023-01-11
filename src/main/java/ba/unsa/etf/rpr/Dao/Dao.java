package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Room_Bungalow;

import java.sql.ResultSet;
import java.util.List;

/**
 * Root interface for all DAO classes
 *
 */
public interface Dao<T> {

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     */
    T add(T item);

    /**
     * Fully updates entity in database based on id (primary) match.
     * @param item - bean to be updated. id must be populated
     * @return updated version of bean
     */
    T update(T item);

    /**
     * Hard delete of item from database with given id
     * @param id - primary key of entity
     */
    void delete(int id);

    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     */
    List<T> getAll();

}