package cz.muni.fi.pa165.creaturehunting.area;

import cz.muni.fi.pa165.creaturehunting.DAOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * This class is implementation of AreaDAO by JDBC derby.
 * Class will serve until close the entity manager.
 *
 * @author Radoslav Zajonc
 */
public class AreaDAOImpl implements AreaDAO {
    
    private EntityManager entityManager;

    /**
     * Construct an Area DAO for the given Entity Manager.
     * @param entityManager the entity manager to construct this Area DAO for.
     */
    public AreaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void createArea(Area area) throws IllegalArgumentException, DAOException {
        if (area.getId() > 0) {
            throw new IllegalArgumentException("Area is already created, the area ID is > 0.");
        }
        try {
            entityManager.persist(area);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateArea(Area area) throws IllegalArgumentException, DAOException {
        if (area.getId() <= 0) {
            throw new IllegalArgumentException("Area is not created yet, the area ID is not > 0.");
        }
        try {
            entityManager.persist(area);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteArea(Area area) throws DAOException {
        try {
            entityManager.remove(area);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Area findArea(long id) throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT a FROM Area a WHERE a.id=:id", Area.class);
            query.setParameter("id", id);
            List <Area> areas = query.getResultList();
            if (!areas.isEmpty() && areas.size() > 0) {
                return areas.get(0);
            } else {
                return null;
            }
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Area> findAllAreas() throws DAOException {
        try {
            Query query = entityManager.createQuery("SELECT a FROM Area a ORDER BY a.id", Area.class);
            List <Area> areas = query.getResultList();
            return areas;
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }
    
}