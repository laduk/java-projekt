package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.area.Area;
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
        em.getTransaction().begin();
        
        Area area = new Area();
        area.setName("Phobos");
        area.setDescription("Month of Mars");
        
        em.persist(area);
        System.out.println("ID: " + area.getId());
        
        em.getTransaction().commit();
        em.close();
        
        emf.close();
    }
}
