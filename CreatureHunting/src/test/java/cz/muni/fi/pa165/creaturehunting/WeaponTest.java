package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.weapon.WeaponDAOImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class test Weapon and WeaponDAO.
 * 
 * @author Radoslav Zajonc
 */
public class WeaponTest {
    
    private static EntityManager entityManager;
    
    @BeforeClass
    public static void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }
        /*
        
        Weapon bazooka = new Weapon();
        bazooka.setName("");
        bazooka.setGunReach(100);
        bazooka.setAmmunition("3.5 in M20");
        
        Weapon holyWater = new Weapon();
        holyWater.setName("Holy Water");
        holyWater.setGunReach(2);
        holyWater.setAmmunition("water");
        */
    
    @AfterClass
    public static void close() {
        entityManager.close();
        entityManager.getEntityManagerFactory().close();
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testExistIdCreateWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        Weapon weapon = new Weapon();
        weapon.setId(100);
        weaponDAO.createWeapon(weapon);
    }
    
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
    
    @Test
    public void testListWeapons() {
        // TODO
    }

}
