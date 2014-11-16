package cz.muni.fi.pa165.creaturehunting.dao;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAOImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests methods from WeaponDAO interface.
 * 
 * @author Radoslav Zajonc
 */
public class WeaponDAOTest {
    
    private static EntityManager entityManager;
    private static EntityManagerFactory entManFact; 
       
    @BeforeClass
    public static void setUpClass() {        
        entManFact = Persistence.createEntityManagerFactory("myUnit");
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(entManFact.isOpen()) entManFact.close(); 
    }
    
    @Before
    public void setUp() {
        entityManager = entManFact.createEntityManager();
    }
    
    @After
    public void tearDown() {        
        entityManager.close();
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
     * Test createWeapon and findWeapon for create new weapon.
     */
    @Test
    public void testCreateFindWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon rock = new Weapon();
        rock.setName("Rock");
        rock.setGunReach(10);
        rock.setAmmunition("Any rock");
        weaponDAO.createWeapon(rock);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entManFact.createEntityManager();
        weaponDAO = new WeaponDAOImpl(entityManager);
        Weapon weapon = weaponDAO.findWeapon(rock.getId());
        Assert.assertTrue("Wrong id of created weapon.",
                weapon.getId() > 0);
        Assert.assertEquals("Wrong name of created weapon.",
                weapon.getName(), "Rock");
        Assert.assertEquals("Wrong runReach of created weapon.",
                weapon.getGunReach(), 10);
        Assert.assertEquals("Wrong ammunition of created weapon",
                weapon.getAmmunition(), "Any rock");
    }

    /**
     * Test updateWeapon and findWeapon for update exist weapon.
     */
    @Test
    public void testUpdateFindWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon riffle = new Weapon();
        riffle.setName("Rifle");
        riffle.setGunReach(25);
        riffle.setAmmunition("12 mm");
        weaponDAO.createWeapon(riffle);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entManFact.createEntityManager();
        weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        riffle.setName("New Riffle");
        riffle.setGunReach(50);
        riffle.setAmmunition("9 mm");
        weaponDAO.updateWeapon(riffle);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entManFact.createEntityManager();
        weaponDAO = new WeaponDAOImpl(entityManager);
        Weapon weapon = weaponDAO.findWeapon(riffle.getId());
        Assert.assertEquals("Wrong name of updated weapon.",
                weapon.getName(), "New Riffle");
        Assert.assertEquals("Wrong runReach of updated weapon.",
                weapon.getGunReach(), 50);
        Assert.assertEquals("Wrong ammunition of updated weapon",
                weapon.getAmmunition(), "9 mm");
    }

    /**
     * Test deleteWeapon for delete created weapon.
     */
    @Test
    public void testDeleteWeapon() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        entityManager.getTransaction().begin();
        Weapon chainsaw = new Weapon();
        chainsaw.setName("Chainsaw");
        weaponDAO.createWeapon(chainsaw);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entManFact.createEntityManager();
        weaponDAO = new WeaponDAOImpl(entityManager);
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
