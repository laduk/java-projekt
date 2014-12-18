package cz.muni.fi.pa165.creaturehunting.data.dao;

import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import java.util.List;

/**
 * Interface of Data Access Object for Weapon.
 * 
 * @author Fita
 */
public interface WeaponDAO {
    
    /**
     * Store given weapon into database. Id must be lesser than zero otherwise 
     * exception is thrown. 
     * @param weapon The weapon that should be created.
     * @throws IllegalArgumentException when argument is wrong.
     */
    public void createWeapon(Weapon weapon) throws IllegalArgumentException;
    
    /**
     * Update given weapon. Check whether is id greater than zero if it is not
     * then Weapon was not set so exception is thrown.
     * @param weapon The weapon that should be updated.
     * @throws IllegalArgumentException when argument is wrong.
     */
    public void updateWeapon(Weapon weapon) throws IllegalArgumentException;
    
    /**
     * Delete the given weapon from database.
     * @param weapon The weapon that should be deleted.
     */
    public void deleteWeapon(Weapon weapon);
    
    /**
     * Search weapon by given id. If function is successful then returns
     * weapon of given id.
     * @param id Parametr id of type long that should be find.
     * @return Weapon if it was found or null if it is not.
     */
    public Weapon findWeapon(long id);
    
    /**
     * Search for all weapons in the database. In the end the list of existing
     * weapons will be store.
     * @return List of weapons if in database is any present.
     */
    public List<Weapon> findAllWeapons();
}
