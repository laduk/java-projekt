/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.DAOException;
import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import java.util.List;


/**
 *
 * @author laduska
 */
public interface HuntingExperienceDAO {
    
    
    public void createHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException;
    
    
    public void updateHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException;
    
    
    public void deleteHuntingExperience(HuntingExperience exp) throws DAOException;
    
    public HuntingExperience findHuntingExperience(long id) throws DAOException;
    
    
    public List<HuntingExperience> findAllHuntingExperience() throws DAOException;
    
    /* Bude hledat zbran nejmene se zadanou efficiency, nebo vyssi, zadava se tedy nejnizsi mira ucinnosti, ktera nas zajima     */
    public List<Weapon> findEfficientWeapons(Creature creature, int minimalEfficiency);
    
   
    
}
