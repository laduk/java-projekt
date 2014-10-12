/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.area.Area;
import cz.muni.fi.pa165.creaturehunting.area.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.area.AreaDAOImpl;
import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.creature.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.creature.CreatureDAOImpl;
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
              
        
        Area area2 = new Area();
        area2.setName("Brno");
        area2.setDescription("Place for odd beings.");
        area2.setAcreage(24000);
        assertNotNull("Testing whether is Area2 null",zombie);
        
        entMan.getTransaction().begin();
        areas.createArea(area2);
        entMan.getTransaction().commit();
        
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
        long id = vampire.getId();
        
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
        
        long id = vampire.getId();
        
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
        
        entMan.getTransaction().begin();
        creature.createCreature(slave);
        entMan.getTransaction().commit();
       
        entMan.getTransaction().begin();
        List<Creature> creat = new ArrayList<Creature>();
        creat.addAll(creature.findAllCreatures());
        entMan.getTransaction().commit();       
        
        assertTrue("If list has proper size", 1 < creat.size());
    }
    
}
