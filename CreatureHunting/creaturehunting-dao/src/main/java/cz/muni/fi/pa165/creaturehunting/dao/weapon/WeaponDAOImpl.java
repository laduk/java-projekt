/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.dao.weapon;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Implementation of interface of Data Access Object for weapon.
 * @author Fita
 */
public class WeaponDAOImpl implements WeaponDAO {
    
    
    private EntityManager entityManager;
    
    /**
     * Create a Weapon DAO for the given Entity Manager.
     * @param entityManager The entity manager to construct.
     */
    public WeaponDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public void createWeapon(Weapon weapon) throws IllegalArgumentException, DAOException {
        if(weapon.getId()>0){
            throw new IllegalArgumentException("The weapon was already created and id was set.");
        }
        try {
            entityManager.persist(weapon);
        } catch (PersistenceException pe) {
            throw new DAOException(pe);
        }
    }

    public void updateWeapon(Weapon weapon) throws IllegalArgumentException, DAOException {
        if(weapon.getId()<=0){
            throw new IllegalArgumentException("The weapon was not even created and id was not set.");
        }
        try {
            entityManager.persist(weapon);
        } catch (PersistenceException pe) {
            throw new DAOException(pe);
        }
    }

    public void deleteWeapon(Weapon weapon) throws DAOException {
        try {
            entityManager.remove(weapon);
        } catch (PersistenceException pe) {
            throw new DAOException(pe);
        }
    }

    public Weapon findWeapon(long id) throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT wea FROM Weapon wea WHERE wea.id=:id",Weapon.class);
            query.setParameter("id", id);
            List<Weapon> weaponList = query.getResultList();
            if (weaponList.isEmpty()) {
                return null;
            } else {
                return weaponList.get(0);
            }
        } catch (PersistenceException pe) {
            throw new DAOException(pe);
        }
    }

    public List<Weapon> findAllWeapons() throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT wea FROM Weapon wea ORDER BY wea.id",Weapon.class);
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new DAOException(pe);
        }
    }
    
}
