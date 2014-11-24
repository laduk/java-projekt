package cz.muni.fi.pa165.creaturehunting.dao.daoimpl;

import cz.muni.fi.pa165.creaturehunting.dao.dao.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.dao.entity.Weapon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementation of interface of Data Access Object for weapon.
 * @author Fita
 */
@Repository
public class WeaponDAOImpl implements WeaponDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Create a Weapon DAO for the given Entity Manager.
     * @param entityManager The entity manager to construct.
     */
    public WeaponDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public void createWeapon(Weapon weapon) throws IllegalArgumentException {
        if(weapon.getId()>0){
            throw new IllegalArgumentException("The weapon was already created and id was set.");
        }
        entityManager.persist(weapon);
    }

    public void updateWeapon(Weapon weapon) throws IllegalArgumentException {
        if(weapon.getId()<=0){
            throw new IllegalArgumentException("The weapon was not even created and id was not set.");
        }
        entityManager.merge(weapon);
    }

    public void deleteWeapon(Weapon weapon) {
        Weapon managedWeapon = entityManager.find(Weapon.class, weapon.getId());
        entityManager.remove(managedWeapon);
    }

    public Weapon findWeapon(long id) {
        Query query = entityManager.createQuery("SELECT wea FROM Weapon wea WHERE wea.id=:id",Weapon.class);
        query.setParameter("id", id);
        List<Weapon> weaponList = query.getResultList();
        if (weaponList.isEmpty()) {
            return null;
        } else {
            return weaponList.get(0);
        }
    }

    public List<Weapon> findAllWeapons() {
        Query query = entityManager.createQuery("SELECT wea FROM Weapon wea ORDER BY wea.id",Weapon.class);
        return query.getResultList();
    }
    
}
