package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAOImpl;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAOImpl;
import java.util.ArrayList;
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
import static org.junit.Assert.*;

/**
 *
 * @author Fita
 */
public class CreatureDAOTest {
    
    private EntityManager entMan;
    private static EntityManagerFactory entManFact;
       
    @BeforeClass
    public static void setUpClass() {        
        entManFact = Persistence.createEntityManagerFactory("myUnit");
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(entManFact.isOpen()) entManFact.close();
    }
    
    @Before //toto se dela pred kazdym testem
    public void setUp() {
        entMan = entManFact.createEntityManager();
    }
    
    @After
    public void tearDown() {        
        entMan.close();
    }

    
    /**
     * Test of right parameter.
     */
    @Test
    public void testAgilityPec(){
        System.out.println("setingAgility");
        Creature skeleton = new Creature();
        skeleton.setName("DEATH");
        skeleton.setHeight(200);
        skeleton.setWeight(15);
        int agility = 15;
        if(agility<0 ||agility>100){
            throw new IllegalArgumentException("Out of interval");}
        else{
            skeleton.setAgility(agility);
        }
        CreatureDAO creat = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creat.createCreature(skeleton);
        entMan.getTransaction().commit();
        assertFalse("Test whether creature was sez and id set",skeleton.getId()<=0);        
    }
    
    /**
     * Test of createCreature method, of class CreatureDAO.
     */
    @Test
    public void testCreateCreature() {
        System.out.println("createCreature");
        Creature zombie = new Creature();
        zombie.setName("Zombie");
        zombie.setHeight(180);
        zombie.setWeight(73);
        zombie.setAgility(70);
        assertNotNull("Testing whether is Zombie null",zombie);
        
        Area area = new Area();
        area.setName("Area51");
        area.setDescription("US laboratory and test field");
        area.setAcreage(50000);
        assertNotNull("Testing whether is Area null",zombie);
        
        AreaDAO areas = new AreaDAOImpl(entMan);
        entMan.getTransaction().begin();
        areas.createArea(area);
        entMan.getTransaction().commit();
        entMan.close();
        
        entMan = entManFact.createEntityManager();
        areas = new AreaDAOImpl(entMan);
        Area area2 = new Area();
        area2.setName("Brno");
        area2.setDescription("Place for odd beings.");
        area2.setAcreage(24000);
        assertNotNull("Testing whether is Area2 null",zombie);
        
        entMan.getTransaction().begin();
        areas.createArea(area2);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        
        List<Area> places = new ArrayList<Area>();
        places.add(area);
        places.add(area2);
        assertNotNull("Testing whether is list null",places);
        
        zombie.setListOfAreas(places);
        
        entMan.getTransaction().begin();
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        creature.createCreature(zombie);
        entMan.getTransaction().commit();
        assertFalse("Test whether creature was sez and id set",zombie.getId()<=0);
    }

    /**
     * Test of updateCreature method, of class CreatureDAO.
     */
    @Test
    public void testUpdateCreature() {
        System.out.println("updateCreature");
        Area area =  new Area();
        area.setName("Brašov");
        area.setDescription("Do not go out at night! Do not visit castle even "
                + "if you are welcome.");
        area.setAcreage(1000);
        
        AreaDAO areas = new AreaDAOImpl(entMan);
        entMan.getTransaction().begin();
        areas.createArea(area);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.createCreature(vampire);
        entMan.getTransaction().commit();
        entMan.close();
        
        entMan = entManFact.createEntityManager();
        creature = new CreatureDAOImpl(entMan);
        vampire.setName("Dracula");
        entMan.getTransaction().begin();
        creature.updateCreature(vampire);
        entMan.getTransaction().commit();        
        Assert.assertEquals("Whether is Dracule name set and updated.","Dracula",
                vampire.getName());
    }

    /**
     * Test of deleteCreature method, of class CreatureDAO.
     */
    @Test
    public void testDeleteCreature() {
        System.out.println("deleteCreature");
        Area area =  new Area();
        area.setName("Brašov");
        area.setDescription("Do not go out at night! Do not visit castle even if"
                + " you are welcome.");
        area.setAcreage(1000);
        
        AreaDAO areas = new AreaDAOImpl(entMan);
        entMan.getTransaction().begin();
        areas.createArea(area);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.createCreature(vampire);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        long id = vampire.getId();
        
        creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.deleteCreature(vampire);
        entMan.getTransaction().commit();
        
        assertNull("Whether object with id is deleted.", creature.findCreature(id));
    }

    /**
     * Test of findCreature method, of class CreatureDAO.
     */
    @Test
    public void testFindCreature() {
        System.out.println("findCreature");
        Area area =  new Area();
        area.setName("Brašov");
        area.setDescription("Do not go out at night! Do not visit castle even if "
                + "you are welcome.");
        area.setAcreage(1000);
        
        AreaDAO areas = new AreaDAOImpl(entMan);
        entMan.getTransaction().begin();
        areas.createArea(area);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.createCreature(vampire);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        
        long id = vampire.getId();
        
        creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        Creature vampireFind = creature.findCreature(id);
        entMan.getTransaction().commit();
        assertEquals("Whether has been found proper creature.",vampire, vampireFind);
    }

    /**
     * Test of findAllCreatures method, of class CreatureDAO.
     */
    @Test
    public void testFindAllCreatures() {
        System.out.println("findAllCreatures");
        Area area =  new Area();
        area.setName("Brašov");
        area.setDescription("Do not go out at night! Do not visit castle even "
                + "if you are welcome.");
        area.setAcreage(1000);
        
        AreaDAO areas = new AreaDAOImpl(entMan);
        entMan.getTransaction().begin();
        areas.createArea(area);
        entMan.getTransaction().commit();
        entMan.close();
        entMan = entManFact.createEntityManager();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        Creature slave = new Creature();
        slave.setName("Ighor");
        slave.setHeight(160);
        slave.setWeight(45);
        slave.setAgility(33);
        slave.setListOfAreas(place);
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.createCreature(vampire);
        entMan.getTransaction().commit();
        entMan.close();
        
        entMan = entManFact.createEntityManager();
        creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.createCreature(slave);
        entMan.getTransaction().commit();
        entMan.close();
        
        entMan = entManFact.createEntityManager();
        creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        List<Creature> creat = new ArrayList<Creature>();
        creat.addAll(creature.findAllCreatures());
        entMan.getTransaction().commit();       
        
        assertTrue("If list has proper size", 1 < creat.size());
    }
    
}
