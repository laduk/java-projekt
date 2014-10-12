/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.DAOException;
import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import java.util.ArrayList;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Implementation of interface of Data Access Object for HunntingExperience.
 * @author laduska
 */
public class HuntingExperienceDAOImpl implements HuntingExperienceDAO {

    private EntityManager entityManager;

    /**
     * Construct a Creature DAO for the given Entity Manager.
     * @param entityManager The entity manager to construct.
     */
    public HuntingExperienceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createHuntingExperience(HuntingExperience exp) throws IllegalArgumentException, DAOException {
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

    public HuntingExperience findHuntingExperience(long id) throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT a FROM HuntingExperience a WHERE a.id=:id", HuntingExperience.class);
            query.setParameter("id", id);
            List<HuntingExperience> exps = query.getResultList();
            if (!exps.isEmpty() && exps.size() > 0) { //toto je divne
                return exps.get(0);
            } else {
                return null;
            }
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    public List<HuntingExperience> findAllHuntingExperience() throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT exp FROM HuntingExperience exp ORDER BY exp.id", HuntingExperience.class);
            List<HuntingExperience> exps = query.getResultList();
            return exps;
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    
    //tato metoda by chtela jeste upravit a promyslet, mozna by mela precejen vracet HuntingExp a ty by zpracovaly uz metody na te service tier
    
    /* Bude hledat zbran nejmene se zadanou efficiency, nebo vyssi, zadava se tedy nejnizsi mira ucinnosti, ktera nas zajima       */
    public List<Weapon> findEfficientWeapons(Creature creature, int minimalEfficiency) {
        try {
            Query query = entityManager.createQuery("SELECT exp.weapon FROM HuntingExperience exp WHERE exp.efficiency >= :minEfficiency ORDER BY exp.efficiency DESC", 
                    Weapon.class);

            query.setParameter("minEfficiency", minimalEfficiency);
            return query.getResultList();

        } catch (PersistenceException e) {
            throw new DAOException(e);
        }

    }
}
