package cz.muni.fi.pa165.creaturehunting.dao;

import cz.muni.fi.pa165.creaturehunting.dao.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.dao.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.dao.daoimpl.CreatureDAOImpl;
import cz.muni.fi.pa165.creaturehunting.dao.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.dao.dao.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.dao.daoimpl.HuntingExperienceDAOImpl;
import cz.muni.fi.pa165.creaturehunting.dao.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.dao.dao.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.dao.daoimpl.WeaponDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static EntityManagerFactory entManFact; 
       
    @BeforeClass
    public static void setUpClass() {        
        entManFact = Persistence.createEntityManagerFactory("testUnit");
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(entManFact.isOpen()) entManFact.close(); 
    }
    
    @Before
    public void setUp() {
        entMan = entManFact.createEntityManager();
    }
    
    @After
    public void tearDown() {        
        entMan.close();
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
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        assertFalse("Test whether HuntingExperience was saved and id is set", exp.getId()<=0);
        
        HuntingExperience foundExp = huntingExpDAO.findHuntingExperience(exp.getId());
        
        entMan.getTransaction().begin();
        String altDesc = "Killing a living zombie.";
        foundExp.setDescription(altDesc);
        int altEff = 35;
        foundExp.setEfficiency(altEff);
        huntingExpDAO.updateHuntingExperience(foundExp);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        HuntingExperience exp2 = huntingExpDAO.findHuntingExperience(foundExp.getId());
        assertEquals("Has the description been updated.", foundExp.getDescription(), exp2.getDescription());
        assertEquals("Has the efficiency been updated.", foundExp.getEfficiency(), exp2.getEfficiency());
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
        
        entMan.getTransaction().begin();
        huntingExpDAO.createHuntingExperience(exp);
        entMan.getTransaction().commit();
        long id = exp.getId();
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        entMan.getTransaction().begin();
        huntingExpDAO.deleteHuntingExperience(exp);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
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
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
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
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        List<HuntingExperience> expsOld = huntingExpDAO.findAllHuntingExperience();
        
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
        
        entMan.getTransaction().begin();
        huntingExpDAO.createHuntingExperience(exp);
        huntingExpDAO.createHuntingExperience(exp2);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
        List<HuntingExperience> exps = huntingExpDAO.findAllHuntingExperience();
        
        assertTrue("If exactly 2 HuntingExperience-s were created", expsOld.size()+2 ==  exps.size());
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
