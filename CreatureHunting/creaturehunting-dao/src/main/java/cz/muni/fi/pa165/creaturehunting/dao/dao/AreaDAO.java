package cz.muni.fi.pa165.creaturehunting.dao.dao;

import cz.muni.fi.pa165.creaturehunting.dao.entity.Area;
import java.util.List;

/**
 * This interface represents a contract for a DAO for class Area.
 *
 * @author Radoslav Zajonc
 */
public interface AreaDAO {
    
    /**
     * Create the given area in the database. The area ID must not be > 0,
     * otherwise it will throw IllegalArgumentException. After creating,
     * the DAO will set the obtained ID in the given area.
     * @param area The area to be created in the database.
     * @throws IllegalArgumentException If the area ID is not null.
     */
    public void createArea(Area area) throws IllegalArgumentException;
    
    /**
     * Update the given area in the database. The area ID must be > 0,
     * otherwise it will throw IllegalArgumentException.
     * @param area The area to be updated in the database.
     * @throws IllegalArgumentException If the area ID is null.
     */
    public void updateArea(Area area) throws IllegalArgumentException;
    
    /**
     * Delete the given area from the database. After deleting,
     * the DAO will set the ID of the given area to not > 0.
     * @param area The area to be deleted from the database.
     */
    public void deleteArea(Area area);
    
    /**
     * Returns the area from the database matching the given ID, otherwise null.
     * @param id The ID of the area to be returned.
     * @return The area from the database matching the given ID, otherwise null.
     */
    public Area findArea(long id);
    
    /**
     * Returns a list of all areas from the database ordered by area ID. The list
     * is never null and is empty when the database does not contain any area.
     * @return A list of all areas from the database ordered by area ID.
     */
    public List<Area> findAllAreas();
    
}
