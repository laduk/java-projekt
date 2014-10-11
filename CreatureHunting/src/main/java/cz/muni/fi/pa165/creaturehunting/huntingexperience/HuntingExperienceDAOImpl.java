/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.DAOException;
import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author laduska
 */
public class HuntingExperienceDAOImpl implements HuntingExperienceDAO {

    private EntityManager entityManager;

    public HuntingExperienceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException {
        if (exp.getId() > 0) {
            throw new IllegalArgumentException("Experience is already created, its ID is > 0.");
        }
        try {
            entityManager.persist(exp);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    public void updateHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException {
        if (exp.getId() <= 0) {
            throw new IllegalArgumentException("Experience is not created yet, its ID is not > 0.");
        }
        try {
            entityManager.persist(exp);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    public void deleteHuntingExperience(HuntingExperience exp) throws DAOException {
        try {
            entityManager.remove(exp);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }

    }

//    public HuntingExperience findHuntingExperience(long id) throws DAOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public List<HuntingExperience> findAllHuntingExperience() throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT exp FROM HuntingExperience exp ORDER BY exp.id", HuntingExperience.class);         
            List<HuntingExperience> exps = query.getResultList();
            return exps;
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    /* Bude hledat zbran nejmene se zadanou efficiency, nebo vyssi, zadava se tedy nejnizsi mira ucinnosti, ktera nas zajima       */
    public List<Weapon> findEfficientWeapons(Creature creature, int minimalEfficiency) {
        try {
            Query query = entityManager.createQuery("SELECT exp FROM HuntingExperience exp WHERE exp.efficiency >= :minEfficiency", HuntingExperience.class);
            query.setParameter("minEfficiency", minimalEfficiency);
            //List<Weapon> weapons = query.getResultList();
            //return weapons;
            return query.getResultList();
            
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
        
    }
    
    
}
