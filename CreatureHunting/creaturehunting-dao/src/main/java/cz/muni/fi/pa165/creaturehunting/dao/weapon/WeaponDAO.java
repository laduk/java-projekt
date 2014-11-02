/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.dao.weapon;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
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
     * @throws DAOException if there is error with database.
     */
    public void createWeapon(Weapon weapon) throws IllegalArgumentException, 
            DAOException;
    
    /**
     * Update given weapon.
     * @param weapon The weapon that should be updated.
     * @throws IllegalArgumentException when argument is wrong.
     * @throws DAOException if there is error with database.
     */
    public void updateWeapon(Weapon weapon) throws IllegalArgumentException,
            DAOException;
    
    /**
     * Delete the given weapon from database.
     * @param weapon The weapon that should be deleted.
     * @throws DAOException if there is error with database.
     */
    public void deleteWeapon(Weapon weapon) throws DAOException;
    
    /**
     * Search weapon by given id. If function is succesful then returns
     * weapon of given id.
     * @param id
     * @return Weapon if it was found.
     * @throws DAOException if there is error with database.
     */
    public Weapon findWeapon(long id) throws DAOException;
    
    /**
     * Search for all weapons in the database. In the end the list of existing
     * weapons will be store.
     * @return List of weapons if in database is any present.
     * @throws DAOException if there is error with database.
     */
    public List<Weapon> findAllWeapons() throws DAOException;
}
