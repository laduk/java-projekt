package cz.muni.fi.pa165.creaturehunting.dao;

import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAOImpl;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAOImpl;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperienceDAOImpl;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAOImpl;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testUnit");
        EntityManager em = emf.createEntityManager();
        
        AreaDAO areaDAO = new AreaDAOImpl(em);
        CreatureDAO creatureDAO = new CreatureDAOImpl(em);
        WeaponDAO weapDAO = new WeaponDAOImpl(em);
        HuntingExperienceDAO expDAO = new HuntingExperienceDAOImpl(em);
        
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
        
        Weapon weap = new Weapon();
        weap.setName("Draconic bow");
        weap.setGunReach(100);
        weap.setAmmunition("Arrows");
        
        Weapon weap2 = new Weapon();
        weap2.setName("Kris dagger");
        weap2.setGunReach(1);
        weap2.setAmmunition("none");
        
        
        HuntingExperience exp = new HuntingExperience();
        exp.setCreature(creature);
        exp.setWeapon(weap);
        exp.setDateOfExperience(null);
        exp.setEfficiency(100);
        exp.setDescription("Quick headshot");
        
        HuntingExperience exp2 = new HuntingExperience();
        exp2.setCreature(creature);
        exp2.setWeapon(weap);
        exp2.setDateOfExperience(null);
        exp2.setEfficiency(70);
        exp2.setDescription("Serious injury");
        
        HuntingExperience exp3 = new HuntingExperience();
        exp3.setCreature(creature);
        exp2.setWeapon(weap2);
        exp2.setDateOfExperience(null);
        exp2.setEfficiency(80);
        exp2.setDescription("Extra Serious injury");
                
        
        
        List<Area> listOfAreas = new ArrayList();
        listOfAreas.add(area);
        creature.setListOfAreas(listOfAreas);
        
        List<Creature> listOfCreatures = new ArrayList();
        listOfCreatures.add(creature);
        listOfCreatures.add(creature2);
        area.setListOfCreatures(listOfCreatures);
        
        List<Weapon> listOfWeapons = new ArrayList();
        listOfWeapons.add(weap);
        listOfWeapons.add(weap2);
        
        em.getTransaction().begin();
        areaDAO.createArea(area);
        creatureDAO.createCreature(creature);
        creatureDAO.createCreature(creature2);
        em.getTransaction().commit();
        Area area2 = areaDAO.findArea(1);
        Creature creatureDB = creatureDAO.findCreature(1);
        Creature creatureDB2 = creatureDAO.findCreature(2);
        
        
        
        
        em.getTransaction().begin();    //snad uz ok
        weapDAO.createWeapon(weap);
        weapDAO.createWeapon(weap2);
        em.getTransaction().commit();
        List<Weapon> listOfWeaponsDB = weapDAO.findAllWeapons();
        
              
        //test create
        em.getTransaction().begin();
        expDAO.createHuntingExperience(exp);
        expDAO.createHuntingExperience(exp2);
        expDAO.createHuntingExperience(exp3);
        em.getTransaction().commit();
        
        exp.setDescription("Zcela zmeneny description");
        
        //test update
        em.getTransaction().begin();
        expDAO.updateHuntingExperience(exp);
        em.getTransaction().commit();
        
        //test delete
//        em.getTransaction().begin();
//        expDAO.deleteHuntingExperience(exp);
//        em.getTransaction().commit();
        
        List<HuntingExperience> listOfExps = expDAO.findAllHuntingExperience();
        
        List<Weapon> efficientWeapons = expDAO.findEfficientWeapons(creature,75);
        
        
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
        
        System.out.println("Weapon localy: "+listOfWeapons.get(0));
        System.out.println("Weapon db: "+listOfWeaponsDB.get(0));
        System.out.println("Weapon2 localy: "+listOfWeapons.get(1));
        System.out.println("Weapon2 db: "+listOfWeaponsDB.get(1));
        
        if (listOfExps.isEmpty()){
            System.out.println("Experiences: "+ "JE to smazane");
        } else{
        
            System.out.println("Experiences: "+listOfExps.get(0));
            System.out.println("Experiences: "+listOfExps.get(1));
            System.out.println("Experiences: "+listOfExps.get(2));
        }
        
        
         if (efficientWeapons.isEmpty()){
            System.out.println("No WEAPONS");
        } else{
        
            System.out.println("Vhodna zbran: " + efficientWeapons.get(0));
            System.out.println("Vhodna zbran: " + efficientWeapons.get(1));
            //System.out.println("Vhodna zbran: " + efficientWeapons.get(2));
        }
        
        em.close();
        emf.close();
    }
}
