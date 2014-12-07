package cz.muni.fi.pa165.creaturehunting.data;

import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.data.dao.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.data.daoimpl.CreatureDAOImpl;
import cz.muni.fi.pa165.creaturehunting.data.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.data.dao.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.data.daoimpl.HuntingExperienceDAOImpl;
import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.data.dao.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.data.daoimpl.WeaponDAOImpl;
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
    @Test
    public void testfindEfficientWeaponExperiences() throws ParseException {
    System.out.println("findEfficientWeaponExperiences");
        HuntingExperienceDAO huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        WeaponDAO weaponDAO = new WeaponDAOImpl(entMan);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entMan);
        
        Creature creature1 = new Creature();
        creature1.setAgility(25);
        creature1.setHeight(180);
        creature1.setWeight(80);
        creature1.setName("Green zombie");
        creature1.setListOfAreas(null); //this one has not been seen yet
        
        Weapon weapon = new Weapon();
        weapon.setName("Rifle");
        weapon.setAmmunition("0.30 cal");
        weapon.setGunReach(100);
  
        Weapon weapon2 = new Weapon();
        weapon2.setName("Bow");
        weapon2.setAmmunition("Arrows");
        weapon2.setGunReach(200);
        
        HuntingExperience exp = new HuntingExperience();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-11-2014";
        Date date = sdf.parse(dateStr);
        exp.setWeapon(weapon);
        exp.setCreature(creature1);
        exp.setDateOfExperience(date);
        exp.setDescription("Killing a Forest Ogre.");
        exp.setEfficiency(20);
        
        HuntingExperience exp2 = new HuntingExperience();
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
        String dateStr2 = "25-11-2013";
        Date date2 = sdf2.parse(dateStr2);
        exp2.setWeapon(weapon);
        exp2.setCreature(creature1);
        exp2.setDateOfExperience(date2);
        exp2.setDescription("Killing an Orfen.");
        exp2.setEfficiency(100);
        
        HuntingExperience exp3 = new HuntingExperience();
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-M-yyyy");
        String dateStr3 = "06-06-2006";
        Date date3 = sdf3.parse(dateStr3);
        exp3.setWeapon(weapon2);
        exp3.setCreature(creature1);
        exp3.setDateOfExperience(date3);
        exp3.setDescription("Killing an Evil Spirit.");
        exp3.setEfficiency(70);
        
        entMan.getTransaction().begin();
        creatureDAO.createCreature(creature1);
        weaponDAO.createWeapon(weapon);
        weaponDAO.createWeapon(weapon2);
        huntingExpDAO.createHuntingExperience(exp);
        huntingExpDAO.createHuntingExperience(exp2);
        huntingExpDAO.createHuntingExperience(exp3);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        huntingExpDAO = new HuntingExperienceDAOImpl(entMan);
        
       
        List<HuntingExperience> exps = huntingExpDAO.findEfficientWeaponExperiences(creature1,70);
        
        assertTrue("For efficiency of 70%, two experiences were found", exps.size() ==  2);
        
        List<HuntingExperience> exps2 = huntingExpDAO.findEfficientWeaponExperiences(creature1,100);
        
        assertTrue("For efficiency of 100%, only one experience was found", exps2.size() ==  1);
        assertTrue("Found experience contain th right weapon", exps2.get(0).getWeapon().equals(weapon));
        
         List<HuntingExperience> exps3 = huntingExpDAO.findEfficientWeaponExperiences(creature1,20);
        
        assertTrue("For efficiency of 20%, all of three experiences were found", exps3.size() ==  3);
    }
    
}
