/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.dao.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
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
     * @throws IllegalArgumentException If paramentr is wrong exception is thrown.
     * @throws DAOException The DAOException is thrown there must be some mistake
     * on database.
     */
    public void createHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException;
    
    /**
     * Update the existing experience.
     * @param exp Experience that should be updated and saved.
     * @throws IllegalArgumentException If paramentr is wrong exception is thrown.
     * @throws DAOException The DAOException is thrown there must be some mistake
     * on database.
     */
    public void updateHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException;
    
    /**
     * Deleting specific hunting experience.
     * @param exp Experience which should be deleted.
     * @throws DAOException The DAOException is thrown there must be some mistake
     * on database.
     */
    public void deleteHuntingExperience(HuntingExperience exp) throws DAOException;
    
    /**
     * Find the specified experience by id.
     * @param id Identifier to find an specific experience.
     * @return Experience if it is found.
     * @throws DAOException The DAOException is thrown there must be some mistake
     * on database.
     */
    public HuntingExperience findHuntingExperience(long id) throws DAOException;
    
    /**
     * Find all existing experience.
     * @return List of all experience that were stored.
     * @throws DAOException The DAOException is thrown there must be some mistake
     * on database.
     */
    public List<HuntingExperience> findAllHuntingExperience() throws DAOException;
    
    /**
     * Find list of weapons that is efficient for the creature.
     * @param creature For this creature should be found weapon.
     * @param minimalEfficiency Value of effeciency for the weapon. 
     * @return List of weapons.
     */
    public List<Weapon> findEfficientWeapons(Creature creature, int minimalEfficiency);
    
   
    
}
