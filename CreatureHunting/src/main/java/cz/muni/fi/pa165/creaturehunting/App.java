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

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        
        System.out.println(" ****** STARTING APPLICATOIN ****** ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
        EntityManager em = emf.createEntityManager();
        
        AreaDAO areaDAO = new AreaDAOImpl(em);
        CreatureDAO creatureDAO = new CreatureDAOImpl(em);
        
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
        
        List<Area> listOfAreas = new ArrayList();
        listOfAreas.add(area);
        creature.setListOfAreas(listOfAreas);
        
        List<Creature> listOfCreatures = new ArrayList();
        listOfCreatures.add(creature);
        listOfCreatures.add(creature2);
        area.setListOfCreatures(listOfCreatures);
        
        em.getTransaction().begin();
        areaDAO.createArea(area);
        creatureDAO.createCreature(creature);
        creatureDAO.createCreature(creature2);
        em.getTransaction().commit();
        Area area2 = areaDAO.findArea(1);
        Creature creatureDB = creatureDAO.findCreature(1);
        Creature creatureDB2 = creatureDAO.findCreature(2);
        
        System.out.println(area);
        System.out.println("  Creatures in this area: " + area.getListOfCreatures());
        System.out.println(area2);
        System.out.println("  Creatures in this area: " + area2.getListOfCreatures());
        System.out.println(creature);
        System.out.println("  Areas where this creature is: " + creature.getListOfAreas());
        System.out.println(creatureDB);
        System.out.println("  Areas where this creature is: " + creatureDB.getListOfAreas());
        System.out.println(creature2);
        System.out.println("  Areas where this creature is: " + creature2.getListOfAreas());
        System.out.println(creatureDB2);
        System.out.println("  Areas where this creature is: " + creatureDB2.getListOfAreas());
        
        em.close();
        emf.close();
    }
}
