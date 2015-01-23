package cz.muni.fi.pa165.creaturehunting.data.daoimpl;

import cz.muni.fi.pa165.creaturehunting.data.dao.LogInDAO;
import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fita
 */
@Repository
public class LogInDAOImpl implements LogInDAO{
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Construct an LogInDAO for the given Entity Manager.
     * @param entityManager The entity manager to construct DAO.
     */
    public LogInDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void create(LogIn logIn) throws IllegalArgumentException {
        if (logIn.getId() > 0) {
            throw new IllegalArgumentException("LogIn is already created,"
                    + " the LogIn ID is > 0.");
        }
        entityManager.persist(logIn);
    }

    @Override
    public void update(LogIn logIn) throws IllegalArgumentException {
        if (logIn.getId() <= 0) {
            throw new IllegalArgumentException("LogIn is not created yet, "
                    + "the LogIn ID is not > 0.");
        }
        entityManager.merge(logIn);
    }

    @Override
    public void delete(LogIn logIn) {
        LogIn managedLogIn = entityManager.find(LogIn.class, logIn.getId());
        entityManager.remove(managedLogIn);
    }

    @Override
    public LogIn findLogIn(long id) {
        Query query = entityManager.createQuery("SELECT a FROM LogIn a WHERE a.id=:id",
                LogIn.class);
        query.setParameter("id", id);
        List <LogIn> logIns = query.getResultList();
        if (!logIns.isEmpty() && logIns.size() > 0) {
            return logIns.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LogIn> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM LogIn a ORDER BY a.id", 
                LogIn.class);
        List <LogIn> logIns = query.getResultList();
        return logIns;
    }    
}
