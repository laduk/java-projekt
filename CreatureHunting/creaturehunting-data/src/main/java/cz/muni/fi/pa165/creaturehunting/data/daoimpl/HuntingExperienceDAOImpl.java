package cz.muni.fi.pa165.creaturehunting.data.daoimpl;

import cz.muni.fi.pa165.creaturehunting.data.dao.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.data.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementation of interface of Data Access Object for HunntingExperience.
 *
 * @author laduska
 */
@Repository
public class HuntingExperienceDAOImpl implements HuntingExperienceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Construct a Creature DAO for the given Entity Manager.
     *
     * @param entityManager The entity manager to construct.
     */
    public HuntingExperienceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createHuntingExperience(HuntingExperience exp) throws IllegalArgumentException {
        if (exp.getId() > 0) {
            throw new IllegalArgumentException("Experience is already created, its ID is > 0.");
        }
        entityManager.persist(exp);
    }

    public void updateHuntingExperience(HuntingExperience exp) throws IllegalArgumentException {
        if (exp.getId() <= 0) {
            throw new IllegalArgumentException("Experience is not created yet, its ID is not > 0.");
        }
        entityManager.merge(exp);
    }

    public void deleteHuntingExperience(HuntingExperience exp) {
        HuntingExperience managedExp = entityManager.find(HuntingExperience.class, exp.getId());
        entityManager.remove(managedExp);
    }

    public HuntingExperience findHuntingExperience(long id) {
        Query query = entityManager.createQuery("SELECT a FROM HuntingExperience a WHERE a.id=:id", HuntingExperience.class);
        query.setParameter("id", id);
        List<HuntingExperience> exps = query.getResultList();
        if (!exps.isEmpty() && exps.size() > 0) { //toto je divne
            return exps.get(0);
        } else {
            return null;
        }
    }

    public List<HuntingExperience> findAllHuntingExperience() {
        Query query = entityManager.createQuery("SELECT exp FROM HuntingExperience exp ORDER BY exp.id", HuntingExperience.class);
        List<HuntingExperience> exps = query.getResultList();
        return exps;
    }

    
    
    public List<HuntingExperience> findEfficientWeaponExperiences(Creature creature, int minimalEfficiency) {
        Query query = entityManager.createQuery("SELECT exp FROM HuntingExperience exp WHERE exp.efficiency >= :minEfficiency AND exp.creature.id = :creatureID ORDER BY exp.efficiency DESC",
                HuntingExperience.class);

        query.setParameter("minEfficiency", minimalEfficiency);
        query.setParameter("creatureID", creature.getId());

        return query.getResultList();

    }
}
