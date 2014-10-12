/*
 * To change this template, choose Tools | Templates
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
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests methods from AreaDAO interface.
 *
 * @author laduska
 */
public class AreaDAOTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
        entityManager = entityManagerFactory.createEntityManager();

    }

    @Before
    public void initialize() {


        Creature creature = new Creature();
        creature.setName("Imp");
        creature.setHeight(30);
        creature.setWeight(2);
        creature.setAgility(40);

        Creature creature2 = new Creature();
        creature2.setName("Dragon");
        creature2.setHeight(220);
        creature2.setWeight(150);
        creature2.setAgility(20);

        Area area = new Area();
        area.setName("Phobos");
        area.setDescription("Month of Mars");
        area.setAcreage(33.44);

        Area area2 = new Area();
        area2.setName("Pandora");
        area2.setDescription("Endless waterfalls");
        area2.setAcreage(22.55);


        //creatura jedna je ve dvou areas
        List<Area> listOfAreas = new ArrayList();
        listOfAreas.add(area);
        listOfAreas.add(area2);
        creature.setListOfAreas(listOfAreas);

        entityManager.persist(area);
        entityManager.persist(area2);


    }

    @AfterClass
    public static void close() {
        entityManager.close();
        entityManager.getEntityManagerFactory().close();
    }

    /**
     * Test createArea() method, specifically wheter area without creatures is
     * correctly created and all parameters except listOfCreatures are stored
     * right way.
     */
    @Test
    public void testCreatePlainArea() {
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);

        Area area3 = new Area();
        area3.setName("Hell Mountains");
        area3.setDescription("Vulcanic tundra");
        area3.setAcreage(11.66);

        entityManager.getTransaction().begin();
        areaDAO.createArea(area3);
        entityManager.getTransaction().commit();
        assertFalse("Error in creating area", area3.getId() <= 0);

        Area testArea = areaDAO.findArea(area3.getId());
        Assert.assertEquals("Wrong name of created area", testArea.getName(), "Hell Mountains");
        Assert.assertEquals("Wrong description of created area", testArea.getDescription(), "Vulcanic tundra");
        Assert.assertEquals("Wrong acreage of created area", testArea.getAcreage(), 11.66, 0.001);

    }

    /**
     * Test createArea() method, specifically wheter area with creatures is
     * correctly created and parameter listOfCreatures is stored correctly.
     */
    @Test
    public void testCreateAreaWithCreatures() {
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entityManager);

        Area area4 = new Area();
        area4.setName("Angel Mountains");
        area4.setDescription("Frozen watterfalls");
        area4.setAcreage(88.77);

        Creature creature = new Creature();
        creature.setName("Imp");
        creature.setHeight(30);
        creature.setWeight(2);
        creature.setAgility(40);

        Creature creature2 = new Creature();
        creature2.setName("Dragon");
        creature2.setHeight(220);
        creature2.setWeight(150);
        creature2.setAgility(20);

        List<Creature> listOfCreatures = new ArrayList();
        listOfCreatures.add(creature);
        listOfCreatures.add(creature2);
        area4.setListOfCreatures(listOfCreatures);


        entityManager.getTransaction().begin();
        creatureDAO.createCreature(creature);
        creatureDAO.createCreature(creature2);
        areaDAO.createArea(area4);
        entityManager.getTransaction().commit();
        assertFalse("Error in creating area with creatures", area4.getId() <= 0);

        Area testedArea = areaDAO.findArea(area4.getId());
        List<Creature> creatures = testedArea.getListOfCreatures();

        Assert.assertEquals("Wrong name of created area", creatures.get(0), creature);
        Assert.assertEquals("Wrong name of created area", creatures.get(1), creature2);
    }

    /**
     * Test updateArea() method - wheter all atributes are updated correctly.
     */
    @Test
    public void testUpdateArea() {
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entityManager);

        entityManager.getTransaction().begin();
        Creature creature3 = new Creature();
        creature3.setName("Giantic Troll");
        creature3.setHeight(520);
        creature3.setWeight(650);
        creature3.setAgility(5);

        creatureDAO.createCreature(creature3);
        entityManager.getTransaction().commit();

        Area foundArea = areaDAO.findArea(1);
        assertNotNull("Testing whether area was found", foundArea);

        entityManager.getTransaction().begin();
        foundArea.setName("Updated name");
        foundArea.setDescription("Updated description");
        foundArea.setAcreage(100.1);

        List<Creature> creatures = new ArrayList<Creature>();
        creatures.add(creature3);

        foundArea.setListOfCreatures(creatures);

        areaDAO.updateArea(foundArea);
        entityManager.getTransaction().commit();

        Area updatedArea = areaDAO.findArea(1);
        assertNotNull("Testing whether area was found", updatedArea);

        Assert.assertEquals("Wrong name of created area", updatedArea.getName(), "Updated name");
        Assert.assertEquals("Wrong description of created area", updatedArea.getDescription(), "Updated description");
        Assert.assertEquals("Wrong acreage of created area", updatedArea.getAcreage(), 100.1, 0.001);

        List<Creature> updatedCreatures = updatedArea.getListOfCreatures();
        Assert.assertEquals("Wrong name of created area", updatedCreatures.get(0), creature3);


    }

    /**
     * Test deleteArea() method - wheter area is deleted correctly.
     */
    @Test
    public void testDeleteArea() {
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);

        Area foundArea = areaDAO.findArea(2);
        assertNotNull("Testing whether area was found", foundArea);

        Assert.assertEquals("Wrong name of founded area", foundArea.getName(), "Pandora"); //prepared in section @Before, tests wheter name is ok

        entityManager.getTransaction().begin();
        long id = foundArea.getId();
        areaDAO.deleteArea(foundArea);
        entityManager.getTransaction().commit();
        Assert.assertNull(areaDAO.findArea(id));

    }

    /**
     * Test findArea() method - wheter stored area was subsequently found
     * correctly.
     */
    @Test
    public void testFindArea() {

        AreaDAO areaDAO = new AreaDAOImpl(entityManager);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entityManager);

        Creature vampire = new Creature();
        vampire.setName("Vampire");
        vampire.setHeight(189);
        vampire.setWeight(65);
        vampire.setAgility(90);

        entityManager.getTransaction().begin();
        creatureDAO.createCreature(vampire);
        entityManager.getTransaction().commit();

        Area area = new Area();
        area.setName("Brašov");
        area.setDescription("Do not go out at night! Do not visit castle even if "
                + "you are welcome.");
        area.setAcreage(1000);

        List<Creature> creatures = new ArrayList<Creature>();
        creatures.add(vampire);

        area.setListOfCreatures(creatures);

        entityManager.getTransaction().begin();
        areaDAO.createArea(area);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        long id = area.getId();
        Area foundArea = areaDAO.findArea(id);
        entityManager.getTransaction().commit();

        assertEquals("Whether has been found proper creature.", area, foundArea);

    }

    /**
     * Test findAllAreas() method - wheter all stored areas were found
     * correctly.
     */
    @Test
    public void testFindAllAreas() {
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);
        
        Area area = new Area();
        area.setName("Dark Wood");
        area.setDescription("Realy dark area");
        area.setAcreage(100.7);
        
        Area area1 = new Area();
        area1.setName("Wood of death");
        area1.setDescription("Don't even think of visit it");
        area1.setAcreage(666.7);
        
        
        entityManager.getTransaction().begin();  
        int oldSize = areaDAO.findAllAreas().size();        
        areaDAO.createArea(area);
        areaDAO.createArea(area1);
        int newSize = areaDAO.findAllAreas().size();
        entityManager.getTransaction().commit();
        
        assertTrue("Not found proper number of areas", newSize == oldSize + 2 );
    }
}
