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
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
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
    public void initialize(){
        
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);
        CreatureDAO creatureDAO = new CreatureDAOImpl(entityManager);
               
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
        
//        Creature creature3 = new Creature();
//        creature3.setName("Giantic Troll");
//        creature3.setHeight(520);
//        creature3.setWeight(650);
//        creature3.setAgility(5);
       
        Area area = new Area();
        area.setName("Phobos");
        area.setDescription("Month of Mars");
        area.setAcreage(33.44);
        
        Area area2 = new Area();
        area2.setName("Pandora");
        area2.setDescription("Endless waterfalls");
        area2.setAcreage(22.55);
        
//        Area area3 = new Area();
//        area3.setName("Hell Mountains");
//        area3.setDescription("Vulcanic tundra");
//        area3.setAcreage(11.66);
        
        
        //creatura jedna je ve dvou areas
        List<Area> listOfAreas = new ArrayList();
        listOfAreas.add(area);
        listOfAreas.add(area2);
        creature.setListOfAreas(listOfAreas);
    
        //area2 ma nastaven vyskyt creatury 2
//        List<Creature> listOfCreatures = new ArrayList();
//        listOfCreatures.add(creature2);
//        area2.setListOfCreatures(listOfCreatures);
        
//        entityManager.getTransaction().begin();
//        areaDAO.createArea(area);
//        areaDAO.createArea(area2);
//        creatureDAO.createCreature(creature);
//        creatureDAO.createCreature(creature2);
//        creatureDAO.createCreature(creature3);
//        entityManager.getTransaction().commit();

        
        entityManager.persist(area);
        entityManager.persist(area2);
        entityManager.persist(creature);
        entityManager.persist(creature2);
    
    }    
    
    @AfterClass
    public static void close() {
        entityManager.close();
        entityManager.getEntityManagerFactory().close();
    }
     
    
    //otestuje jestli se zalozila area bez priser
    @Test
    public void testCreateArea(){ 
        AreaDAO areaDAO = new AreaDAOImpl(entityManager);
        
        Area area3 = new Area();
        area3.setName("Hell Mountains");
        area3.setDescription("Vulcanic tundra");
        area3.setAcreage(11.66);
        
        entityManager.getTransaction().begin();
        areaDAO.createArea(area3);
        entityManager.getTransaction().commit();
        assertFalse("Text assert", area3.getId()<=0);
        
//        entityManager.getTransaction().begin();
//                
//        entityManager.getTransaction().commit();
    
    }
    
     
    
    
    
    
    
}
