package cz.muni.fi.pa165.creaturehunting.dao.area;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
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
     * @throws DAOException If something fails at database level.
     */
    public void createArea(Area area) throws IllegalArgumentException, DAOException;
    
    /**
     * Update the given area in the database. The area ID must be > 0,
     * otherwise it will throw IllegalArgumentException.
     * @param area The area to be updated in the database.
     * @throws IllegalArgumentException If the area ID is null.
     * @throws DAOException If something fails at database level.
     */
    public void updateArea(Area area) throws IllegalArgumentException, DAOException;
    
    /**
     * Delete the given area from the database. After deleting,
     * the DAO will set the ID of the given area to not > 0.
     * @param area The area to be deleted from the database.
     * @throws DAOException If something fails at database level.
     */
    public void deleteArea(Area area) throws DAOException;
    
    /**
     * Returns the area from the database matching the given ID, otherwise null.
     * @param id The ID of the area to be returned.
     * @return The area from the database matching the given ID, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    public Area findArea(long id) throws DAOException;
    
    /**
     * Returns a list of all areas from the database ordered by area ID. The list
     * is never null and is empty when the database does not contain any area.
     * @return A list of all areas from the database ordered by area ID.
     * @throws DAOException If something fails at database level.
     */
    public List<Area> findAllAreas() throws DAOException;
    
}
