/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.creature;

import cz.muni.fi.pa165.creaturehunting.DAOException;
import cz.muni.fi.pa165.creaturehunting.area.Area;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Matej Čižik
 */
public class CreatureDAOImpl implements CreatureDAO {
    
    private EntityManager entityManager;

    /**
     * Construct a Creature DAO for the given Entity Manager.
     * @param entityManager the entity manager to construct this Creature DAO for.
     */
    public CreatureDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createCreature(Creature creature) throws IllegalArgumentException, DAOException {
        if (creature.getId() > 0) {
            throw new IllegalArgumentException("Creature is already created, the creature ID is > 0.");
        }
        try {
            entityManager.persist(creature);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateCreature(Creature creature) throws IllegalArgumentException, DAOException {
        if (creature.getId() <= 0) {
            throw new IllegalArgumentException("Creature is not created yet, the creature ID is not > 0.");
        }
        try {
            entityManager.persist(creature);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteCreature(Creature creature) throws DAOException {
        try {
            entityManager.remove(creature);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Creature findCreature(long id) throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT a FROM Creature a WHERE a.id=:id", Creature.class);
            query.setParameter("id", id);
            List <Creature> creatures = query.getResultList();
            if (!creatures.isEmpty() && creatures.size() > 0) {
                return creatures.get(0);
            } else {
                return null;
            }
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Creature> findAllCreatures() throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT a FROM Creature a ORDER BY a.id", Creature.class);
            List <Creature> creatures = query.getResultList();
            return creatures;
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }
    
}
