package cz.muni.fi.pa165.creaturehunting.dao.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import java.util.List;


/**
 * Interface of Data Access Object for HuntingExperience.
 * @author laduska
 */
public interface HuntingExperienceDAO {
    
    /**
     * Creating the new experience.
     * @param exp Experience that should be created and saved.
     * @throws IllegalArgumentException If parameter is wrong exception is thrown.
     */
    public void createHuntingExperience(HuntingExperience exp) throws IllegalArgumentException;
    
    /**
     * Update the existing experience.
     * @param exp Experience that should be updated and saved.
     * @throws IllegalArgumentException If parameter is wrong exception is thrown.
     */
    public void updateHuntingExperience(HuntingExperience exp) throws IllegalArgumentException;
    
    /**
     * Deleting specific hunting experience.
     * @param exp Experience which should be deleted.
     */
    public void deleteHuntingExperience(HuntingExperience exp);
    
    /**
     * Find the specified experience by id.
     * @param id Identifier to find an specific experience.
     * @return Experience if it is found.
     */
    public HuntingExperience findHuntingExperience(long id);
    
    /**
     * Find all existing experience.
     * @return List of all experience that were stored.
     */
    public List<HuntingExperience> findAllHuntingExperience();
    
    /**
     * Find list of weapons that is efficient for the creature.
     * @param creature For this creature should be found weapon.
     * @param minimalEfficiency Value of efficiency for the weapon. 
     * @return List of weapons.
     */
    public List<Weapon> findEfficientWeapons(Creature creature, int minimalEfficiency);

}
