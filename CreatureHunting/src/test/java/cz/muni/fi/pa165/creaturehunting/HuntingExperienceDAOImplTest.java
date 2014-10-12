/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.creature.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.creature.CreatureDAOImpl;
import cz.muni.fi.pa165.creaturehunting.huntingexperience.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.huntingexperience.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.huntingexperience.HuntingExperienceDAOImpl;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.weapon.WeaponDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matej Čižik
 */
public class HuntingExperienceDAOImplTest {
    
    public HuntingExperienceDAOImplTest() {
    }
    
    private EntityManager entMan;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        EntityManagerFactory entManFact = Persistence.createEntityManagerFactory("myUnit");
        entMan = entManFact.createEntityManager();
    }
    
    @After
    public void tearDown() {
        entMan.close();
        entMan.getEntityManagerFactory().close();
    }

    /**
     * Test of createHuntingExperience method, of class HuntingExperienceDAOImpl.
     * @throws java.text.ParseException
     */
    @Test
    public void testCreateHuntingExperience() throws ParseException {
        System.out.println("createHuntingExperience");
        
        Creature creature = new Creature();
        creature.setAgility(25);
        creature.setHeight(180);
        creature.setWeight(80);
        creature.setName("Dead zombie");
        creature.setListOfAreas(null); //this one has not been seen yet
        
        Weapon weapon = new Weapon();
        weapon.setName("Rifle");
        weapon.setAmmunition("0.30 cal");
        weapon.setGunReach(100);
        
        HuntingExperience exp = new HuntingExperience();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = sdf.parse(dateStr);
        exp.setDateOfExperience(date);
        exp.setDescription("Killing a dead zombie.");
        exp.setEfficiency(20);
        exp.setCreature(creature);
        exp.setWeapon(weapon);
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        WeaponDAO weaponDAO = new WeaponDAOImpl(entMan);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entMan);
        
        entMan.getTransaction().begin();
        weaponDAO.createWeapon(weapon);
        creatureDAO.createCreature(creature);
        huntingExpDAO.createHuntingExperience(exp);
        entMan.getTransaction().commit();
        
        assertFalse("Test whether HuntingExperience was saved and id is set", exp.getId()<=0);
    }

    /**
     * Test of updateHuntingExperience method, of class HuntingExperienceDAOImpl.
     */
    @Test
    public void testUpdateHuntingExperience() throws ParseException {
        System.out.println("updateHuntingExperience");
        HuntingExperience exp = new HuntingExperience();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = sdf.parse(dateStr);
        exp.setDateOfExperience(date);
        exp.setDescription("Killing a deadly zombie.");
        exp.setEfficiency(20);
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        entMan.getTransaction().begin();
        huntingExpDAO.createHuntingExperience(exp);
        entMan.getTransaction().commit();
        
        assertFalse("Test whether HuntingExperience was saved and id is set", exp.getId()<=0);
        
        HuntingExperience foundExp = huntingExpDAO.findHuntingExperience(exp.getId());
        
        entMan.getTransaction().begin();
        String altDesc = "Killing a living zombie.";
        foundExp.setDescription(altDesc);
        int altEff = 35;
        foundExp.setEfficiency(altEff);
        huntingExpDAO.updateHuntingExperience(foundExp);
        entMan.getTransaction().commit();
        
        HuntingExperience exp2 = huntingExpDAO.findHuntingExperience(foundExp.getId());
        assertEquals("Has the description been updated.", exp.getDescription(), exp2.getDescription());
        assertEquals("Has the efficiency been updated.", exp.getEfficiency(), exp2.getEfficiency());
    }

    /**
     * Test of deleteHuntingExperience method, of class HuntingExperienceDAOImpl.
     */
    @Test
    public void testDeleteHuntingExperience() throws ParseException {
        System.out.println("deleteHuntingExperience");
        HuntingExperience exp = new HuntingExperience();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = sdf.parse(dateStr);
        exp.setDateOfExperience(date);
        exp.setDescription("Killing a dead zombie.");
        exp.setEfficiency(20);
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        WeaponDAO weaponDAO = new WeaponDAOImpl(entMan);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entMan);
        
        entMan.getTransaction().begin();
        huntingExpDAO.createHuntingExperience(exp);
        entMan.getTransaction().commit();
        long id = exp.getId();
        
        entMan.getTransaction().begin();
        huntingExpDAO.deleteHuntingExperience(exp);
        entMan.getTransaction().commit();
        
        assertNull("Whether object with id is deleted.", huntingExpDAO.findHuntingExperience(id));
    }

    /**
     * Test of findHuntingExperience method, of class HuntingExperienceDAOImpl.
     */
    @Test
    public void testFindHuntingExperience() throws ParseException {
        System.out.println("findHuntingExperience");
        HuntingExperience exp = new HuntingExperience();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = sdf.parse(dateStr);
        exp.setDateOfExperience(date);
        exp.setDescription("Killing a dead zombie.");
        exp.setEfficiency(20);
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        entMan.getTransaction().begin();
        huntingExpDAO.createHuntingExperience(exp);
        entMan.getTransaction().commit();
        
        long id = exp.getId();
        
        HuntingExperience foundExp = huntingExpDAO.findHuntingExperience(id);
        assertEquals("Whether has been found proper experience.",exp, foundExp);
    }

    /**
     * Test of findAllHuntingExperience method, of class HuntingExperienceDAOImpl.
     */
    @Test
    public void testFindAllHuntingExperience() throws ParseException {
        System.out.println("findAllHuntingExperience");
        HuntingExperience exp = new HuntingExperience();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = sdf.parse(dateStr);
        exp.setDateOfExperience(date);
        exp.setDescription("Killing a dead zombie.");
        exp.setEfficiency(20);
        
        HuntingExperience exp2 = new HuntingExperience();
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
        String dateStr2 = "15-10-2014";
        Date date2 = sdf2.parse(dateStr2);
        exp2.setDateOfExperience(date2);
        exp2.setDescription("Killing an imp.");
        exp2.setEfficiency(100);
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        entMan.getTransaction().begin();
        huntingExpDAO.createHuntingExperience(exp);
        huntingExpDAO.createHuntingExperience(exp2);
        entMan.getTransaction().commit();
        
        List<HuntingExperience> exps = new ArrayList<HuntingExperience>();
        exps = huntingExpDAO.findAllHuntingExperience();
        
        assertTrue("If list has proper size", 1 < exps.size());
    }

    /**
     * Test of findEfficientWeapons method, of class HuntingExperienceDAOImpl.
     */
    /*@Test
    public void testFindEfficientWeapons() {
        System.out.println("findEfficientWeapons");
        Creature creature = null;
        int minimalEfficiency = 0;
        HuntingExperienceDAOImpl instance = null;
        List<Weapon> expResult = null;
        List<Weapon> result = instance.findEfficientWeapons(creature, minimalEfficiency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
