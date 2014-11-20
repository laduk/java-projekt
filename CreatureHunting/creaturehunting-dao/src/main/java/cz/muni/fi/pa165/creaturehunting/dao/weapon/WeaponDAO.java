package cz.muni.fi.pa165.creaturehunting.dao.weapon;

import java.util.List;

/**
 * Interface of Data Access Object for Weapon.
 * @author Fita
 */
public interface WeaponDAO {
    
    /**
     * Store given weapon into database.
     * @param weapon The weapon that should be created.
     * @throws IllegalArgumentException when argument is wrong.
     */
    public void createWeapon(Weapon weapon) throws IllegalArgumentException;
    
    /**
     * Update given weapon.
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
     * @param id
     * @return Weapon if it was found.
     */
    public Weapon findWeapon(long id);
    
    /**
     * Search for all weapons in the database. In the end the list of existing
     * weapons will be store.
     * @return List of weapons if in database is any present.
     */
    public List<Weapon> findAllWeapons();
}
