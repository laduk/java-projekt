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
        entityManager.getTransaction().begin();
        
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        
        Weapon revolver = new Weapon();
        revolver.setName("Revolver");
        revolver.setGunReach(30);
        revolver.setAmmunition(".357 Magnum");
        
        Weapon chainsaw = new Weapon();
        chainsaw.setName("Chainsaw");
        chainsaw.setGunReach(1);
        chainsaw.setAmmunition("fuel");
        
        Weapon bazooka = new Weapon();
        bazooka.setName("Bazooka");
        bazooka.setGunReach(100);
        bazooka.setAmmunition("3.5 in M20");
        
        Weapon holyWater = new Weapon();
        holyWater.setName("Holy Water");
        holyWater.setGunReach(2);
        holyWater.setAmmunition("water");

        weaponDAO.createWeapon(revolver);
        weaponDAO.createWeapon(chainsaw);
        weaponDAO.createWeapon(bazooka);
        weaponDAO.createWeapon(holyWater);
        
        entityManager.getTransaction().commit();
    }
    
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
    public void testExistWeaponId1() {
        WeaponDAO weaponDAO = new WeaponDAOImpl(entityManager);
        Weapon weapon = weaponDAO.findWeapon(1);
        Assert.assertEquals(1, weapon.getId());
    }
}
