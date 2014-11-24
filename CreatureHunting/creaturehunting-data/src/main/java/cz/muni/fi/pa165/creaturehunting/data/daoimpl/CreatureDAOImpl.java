package cz.muni.fi.pa165.creaturehunting.data.daoimpl;

import cz.muni.fi.pa165.creaturehunting.data.dao.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matej Čižik
 */
@Repository
public class CreatureDAOImpl implements CreatureDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Construct a Creature DAO for the given Entity Manager.
     * @param entityManager the entity manager to construct this Creature DAO for.
     */
    public CreatureDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createCreature(Creature creature) throws IllegalArgumentException {
        if (creature.getId() > 0) {
            throw new IllegalArgumentException("Creature is already created, the creature ID is > 0.");
        }
        entityManager.persist(creature);
    }

    @Override
    public void updateCreature(Creature creature) throws IllegalArgumentException {
        if (creature.getId() <= 0) {
            throw new IllegalArgumentException("Creature is not created yet, the creature ID is not > 0.");
        }
        entityManager.merge(creature);
    }

    @Override
    public void deleteCreature(Creature creature) {
        Creature managedCreature = entityManager.find(Creature.class, creature.getId());
        entityManager.remove(managedCreature);
    }

    @Override
    public Creature findCreature(long id) {
        Query query = entityManager.createQuery("SELECT a FROM Creature a WHERE a.id=:id", Creature.class);
        query.setParameter("id", id);
        List <Creature> creatures = query.getResultList();
        if (!creatures.isEmpty() && creatures.size() > 0) {
            return creatures.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Creature> findAllCreatures() {
        Query query = entityManager.createQuery("SELECT a FROM Creature a ORDER BY a.id", Creature.class);
        List <Creature> creatures = query.getResultList();
        return creatures;
    }
    
}
