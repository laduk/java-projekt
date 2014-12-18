package cz.muni.fi.pa165.creaturehunting.data;

import cz.muni.fi.pa165.creaturehunting.data.entity.Area;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.data.dao.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.data.daoimpl.CreatureDAOImpl;
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
 * Class for testing methods defined in CreatureDAO interface.
 * 
 * @author Fita
 */
public class CreatureDAOTest {
    
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
    
    @Before //toto se dela pred kazdym testem
    public void setUp() {
        entMan = entManFact.createEntityManager();
    }
    
    @After
    public void tearDown() {
        entMan.clear();
        entMan.close();
    }

    
    /**
     * Test of right parameter.
     */
    @Test
    public void testAgilityPercent(){
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
        entMan.getTransaction().begin();
        entMan.persist(skeleton);
        entMan.getTransaction().commit();        
        assertFalse("Test whether creature was set and id set",skeleton.getId()<=0);
        entMan.getTransaction().begin();
        entMan.remove(skeleton);
        entMan.getTransaction().commit();
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
        
        entMan.getTransaction().begin();
        entMan.persist(area);
        entMan.getTransaction().commit();        

        Area area2 = new Area();
        area2.setName("Brno");
        area2.setDescription("Place for odd beings.");
        area2.setAcreage(24000);
        assertNotNull("Testing whether is Area2 null",zombie);
        
        entMan.getTransaction().begin();
        entMan.persist(area2);
        entMan.getTransaction().commit();
        
        List<Area> places = new ArrayList<Area>();
        places.add(area);
        places.add(area2);
        assertNotNull("Testing whether is list null",places);
        
        zombie.setListOfAreas(places);
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        creature.createCreature(zombie);
        entMan.getTransaction().commit();
        assertFalse("Test whether creature was sez and id set",zombie.getId()<=0);
        assertTrue("Creature doesn't associate areas.",
        creature.findCreature(zombie.getId()).getListOfAreas().size() == 2);
        entMan.getTransaction().begin();
        entMan.remove(zombie);
        entMan.getTransaction().commit();
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
        
        entMan.getTransaction().begin();
        entMan.persist(area);
        entMan.getTransaction().commit();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        entMan.getTransaction().begin();
        entMan.persist(vampire);
        entMan.getTransaction().commit();

        CreatureDAO creature = new CreatureDAOImpl(entMan);
        vampire.setName("Dracula");
        entMan.getTransaction().begin();
        creature.updateCreature(vampire);
        entMan.getTransaction().commit();        
        Assert.assertEquals("Whether is Dracule name set and updated.","Dracula",
                vampire.getName());
        entMan.getTransaction().begin();
        entMan.remove(vampire);
        entMan.getTransaction().commit();
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
        
        entMan.getTransaction().begin();
        entMan.persist(area);
        entMan.getTransaction().commit();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        entMan.getTransaction().begin();
        entMan.persist(vampire);
        entMan.getTransaction().commit();
        long id = vampire.getId();
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
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
        
        entMan.getTransaction().begin();
        entMan.persist(area);
        entMan.getTransaction().commit();
        
        List<Area> place = new ArrayList<Area>();
        place.add(area);
        
        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);
        vampire.setListOfAreas(place);
        
        entMan.getTransaction().begin();
        entMan.persist(vampire);
        entMan.getTransaction().commit();
        
        long id = vampire.getId();
        
        CreatureDAO creature = new CreatureDAOImpl(entMan);
        entMan.getTransaction().begin();
        Creature vampireFind = creature.findCreature(id);
        entMan.getTransaction().commit();
        assertEquals("Whether has been found proper creature.",vampire, vampireFind);
        entMan.getTransaction().begin();
        entMan.remove(vampire);
        entMan.getTransaction().commit();
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
        
        entMan.getTransaction().begin();
        entMan.persist(area);
        entMan.getTransaction().commit();
        
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
        entMan.persist(vampire);
        entMan.persist(slave);
        entMan.getTransaction().commit();
        
        entMan.getTransaction().begin();
        List<Creature> creat = new ArrayList<Creature>();
        creat.addAll(creature.findAllCreatures());
        entMan.getTransaction().commit();
        System.out.println("Velikost");
        System.out.println(creat.size());
        assertTrue("If list has proper size",creat.size()==2);
        entMan.getTransaction().begin();
        entMan.remove(vampire);
        entMan.remove(slave);
        entMan.getTransaction().commit();
    }
    
}
