package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.weapon.WeaponDAOImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests methods from WeaponDAO interface.
 * 
 * @author Radoslav Zajonc
 */
public class WeaponDAOTest {
    
    private static EntityManager entityManager;
    
    @BeforeClass
    public static void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    @AfterClass
    public static void close() {
        entityManager.close();
        entityManager.getEntityManagerFactory().close();
    }
    
    /**
     * Test createWeapon when set id.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testExistIdCreateWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        Weapon weapon = new Weapon();
        weapon.setId(100);
        weaponDAO.createWeapon(weapon);
    }
    
    /**
     * Test createWeapon generate id.
     */
    @Test
    public void testGenerateIdCreateWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon weapon = new Weapon();
        weapon.setName("Any weapon");
        weaponDAO.createWeapon(weapon);
        entityManager.getTransaction().commit();
        Assert.assertTrue(weapon.getId() > 0);
    }
    
    /**
     * Test for attribute name can not be not null.
     */
    @Test (expected = DAOException.class)
    public void testNotNullNameWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon weapon = new Weapon();
        try {
            weaponDAO.createWeapon(weapon);
        } catch (DAOException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException(e);
        }
        entityManager.getTransaction().commit();
    }

    /**
     * Test createWeapon for attribute name.
     */
    @Test
    public void testStoreNameWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon rock = new Weapon();
        rock.setName("Rock");
        weaponDAO.createWeapon(rock);
        entityManager.getTransaction().commit();
        Weapon weapon = weaponDAO.findWeapon(rock.getId());
        Assert.assertEquals(weapon.getName(), "Rock");
    }

    /**
     * Test updateWeapon for attribute name.
     */
    @Test
    public void testUpdateNameWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon gun = new Weapon();
        gun.setName("Gun");
        weaponDAO.createWeapon(gun);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        gun.setName("Rifle");
        weaponDAO.updateWeapon(gun);
        entityManager.getTransaction().commit();
        Weapon weapon = weaponDAO.findWeapon(gun.getId());
        Assert.assertEquals(weapon.getName(), "Rifle");
    }

    /**
     * Test createWeapon for attribute gunReach.
     */
    @Test
    public void testStoreGunReachWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon granade = new Weapon();
        granade.setName("Granade");
        granade.setGunReach(20);
        weaponDAO.createWeapon(granade);
        entityManager.getTransaction().commit();
        Weapon weapon = weaponDAO.findWeapon(granade.getId());
        Assert.assertEquals(weapon.getGunReach(), 20);
    }
    
    /**
     * Test updateWeapon for attribute gunReach.
     */
    @Test
    public void testUpdateGunReachWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon gun = new Weapon();
        gun.setName("No name");
        gun.setGunReach(25);
        weaponDAO.createWeapon(gun);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        gun.setGunReach(50);
        weaponDAO.updateWeapon(gun);
        entityManager.getTransaction().commit();
        Weapon weapon = weaponDAO.findWeapon(gun.getId());
        Assert.assertEquals(weapon.getGunReach(), 50);
    }
    
    /**
     * Test createWeapon for attribute ammunition.
     */
    @Test
    public void testStoreAmmunitionWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon revolver = new Weapon();
        revolver.setName("Revolver");
        revolver.setAmmunition(".357 Magnum");
        weaponDAO.createWeapon(revolver);
        entityManager.getTransaction().commit();
        Weapon weapon = weaponDAO.findWeapon(revolver.getId());
        Assert.assertEquals(weapon.getAmmunition(), ".357 Magnum");
    }

    /**
     * Test updateWeapon for attribute ammunition.
     */
    @Test
    public void testUpdateAmmunitionWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon gun = new Weapon();
        gun.setName("Weapon");
        gun.setAmmunition("12 mm");
        weaponDAO.createWeapon(gun);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        gun.setAmmunition("9 mm");
        weaponDAO.updateWeapon(gun);
        entityManager.getTransaction().commit();
        Weapon weapon = weaponDAO.findWeapon(gun.getId());
        Assert.assertEquals(weapon.getAmmunition(), "9 mm");
    }
    
    /**
     * Test deleteWeapon. When delete weapon, weapon must not have id > 0.
     */
    @Test
    public void testDeleteWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon chainsaw = new Weapon();
        chainsaw.setName("Chainsaw");
        weaponDAO.createWeapon(chainsaw);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        long id = chainsaw.getId();
        weaponDAO.deleteWeapon(chainsaw);
        entityManager.getTransaction().commit();
        Assert.assertNull(weaponDAO.findWeapon(id));
    }
    
    /**
     * Test listWeapon. When old list of weapons increment 2 weapon,
     * new list of weapons must to be greater of 2.
     */
    @Test
    public void testListWeapons() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon bazooka = new Weapon();
        bazooka.setName("Bazooka");
        Weapon holyWater = new Weapon();
        holyWater.setName("Holy Water");
        List <Weapon> oldWeapons = weaponDAO.findAllWeapons();
        weaponDAO.createWeapon(bazooka);
        weaponDAO.createWeapon(holyWater);
        entityManager.getTransaction().commit();
        List <Weapon> newWeapons = weaponDAO.findAllWeapons();
        Assert.assertTrue(oldWeapons.size() + 2 == newWeapons.size());
    }

}
