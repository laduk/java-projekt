package cz.muni.fi.pa165.creaturehunting;

import cz.muni.fi.pa165.creaturehunting.area.Area;
import cz.muni.fi.pa165.creaturehunting.area.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.area.AreaDAOImpl;
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
        
        Area area = new Area();
        area.setName("Phobos");
        area.setDescription("Month of Mars");
        area.setAcreage(33.44);
        
        em.getTransaction().begin();
        areaDAO.createArea(area);
        em.getTransaction().commit();
        Area area2 = areaDAO.findArea(1);
        
        System.out.println(area);
        System.out.println(area2);
        
        em.close();
        emf.close();
    }
}
