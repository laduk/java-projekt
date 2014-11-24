package cz.muni.fi.pa165.creaturehunting.dao.daoimpl;

import cz.muni.fi.pa165.creaturehunting.dao.dao.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.dao.entity.Area;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * This class is implementation of AreaDAO by JDBC derby.
 * Class will serve until close the entity manager.
 *
 * @author Radoslav Zajonc
 */
@Repository
public class AreaDAOImpl implements AreaDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Construct an Area DAO for the given Entity Manager.
     * @param entityManager the entity manager to construct this Area DAO for.
     */
    public AreaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void createArea(Area area) throws IllegalArgumentException {
        if (area.getId() > 0) {
            throw new IllegalArgumentException("Area is already created, the area ID is > 0.");
        }
        entityManager.persist(area);
    }

    @Override
    public void updateArea(Area area) throws IllegalArgumentException {
        if (area.getId() <= 0) {
            throw new IllegalArgumentException("Area is not created yet, the area ID is not > 0.");
        }
        entityManager.merge(area);
    }

    @Override
    public void deleteArea(Area area) {
        Area managedArea = entityManager.find(Area.class, area.getId());
        entityManager.remove(managedArea);
        area.setId(-1);
    }

    @Override
    public Area findArea(long id) {
        Query query = entityManager.createQuery("SELECT a FROM Area a WHERE a.id=:id", Area.class);
        query.setParameter("id", id);
        List <Area> areas = query.getResultList();
        if (!areas.isEmpty() && areas.size() > 0) {
            return areas.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Area> findAllAreas() {
        Query query = entityManager.createQuery("SELECT a FROM Area a ORDER BY a.id", Area.class);
        List <Area> areas = query.getResultList();
        return areas;
    }
    
}
