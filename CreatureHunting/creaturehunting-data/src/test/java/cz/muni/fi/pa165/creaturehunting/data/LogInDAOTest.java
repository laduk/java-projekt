/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.data;

import cz.muni.fi.pa165.creaturehunting.data.dao.LogInDAO;
import cz.muni.fi.pa165.creaturehunting.data.daoimpl.LogInDAOImpl;
import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author laduska
 */
public class LogInDAOTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entManFact;

    @BeforeClass
    public static void setUpClass() {
        entManFact = Persistence.createEntityManagerFactory("testUnit");
    }

    @AfterClass
    public static void tearDownClass() {
        if (entManFact.isOpen()) {
            entManFact.close();
        }
    }

    @Before //every test
    public void setUp() {
        entityManager = entManFact.createEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    /**
     * Test of create of LogIn method from class LogInDAO.
     */
    @Test
    public void testCreateLogIn() {
        LogInDAO logInDao = new LogInDAOImpl(entityManager);

        LogIn login = new LogIn();
        login.setName("User1");
        login.setPassword("alfa123");
        login.setRole("ROLE_SURVIVOR");

        LogIn loginRoot = new LogIn();
        loginRoot.setName("Root1");
        loginRoot.setPassword("beta456");
        loginRoot.setRole("ROLE_ADMIN");

        entityManager.getTransaction().begin();
        logInDao.create(login);
        logInDao.create(loginRoot);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        assertFalse("Error in creating login of user", login.getId() < 0);
        assertFalse("Error in creating login of root", loginRoot.getId() < 0);

        LogIn logInUserFounded = logInDao.findLogIn(login.getId());
        LogIn logInRootFounded = logInDao.findLogIn(loginRoot.getId());

        Assert.assertEquals(logInUserFounded.getName(), login.getName());
        Assert.assertEquals(logInUserFounded.getPassword(), login.getPassword());
        Assert.assertTrue(logInUserFounded.getRole().equals("ROLE_SURVIVOR"));

        Assert.assertEquals(logInRootFounded.getName(), loginRoot.getName());
        Assert.assertEquals(logInRootFounded.getPassword(), loginRoot.getPassword());
        Assert.assertTrue(logInRootFounded.getRole().equals("ROLE_ADMIN"));

        entityManager.getTransaction().begin();
        logInDao.delete(login);
        logInDao.delete(loginRoot);
        entityManager.getTransaction().commit();
    }

    /**
     * Test of update of LogIn method from class LogInDAO.
     */
    @Test
    public void testUpdateLogIn() {

        LogInDAO logInDao = new LogInDAOImpl(entityManager);

        LogIn login = new LogIn();
        login.setName("User1");
        login.setPassword("alfa123");
        login.setRole("ROLE_ADMIN");

        entityManager.getTransaction().begin();
        logInDao.create(login);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        assertFalse("Error in creating login of user before update", login.getId() < 0);
        //login = logInDao.findLogIn(login.getId());

        login.setName("changedName");
        login.setPassword("newPassword123");
        login.setRole("ROLE_ADMIN");

        entityManager.getTransaction().begin();
        logInDao.update(login);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        LogIn logInUserFounded = logInDao.findLogIn(login.getId());
        Assert.assertEquals(logInUserFounded.getName(), login.getName());
        Assert.assertEquals(logInUserFounded.getPassword(), login.getPassword());
        Assert.assertTrue(logInUserFounded.getRole().equals("ROLE_ADMIN"));

        entityManager.getTransaction().begin();
        logInDao.delete(login);
        entityManager.getTransaction().commit();


    }

    /**
     * Test of delete of LogIn method from class LogInDAO.
     */
    @Test
    public void testDeleteLogIn() {
        LogInDAO logInDao = new LogInDAOImpl(entityManager);

        LogIn login = new LogIn();
        login.setName("User1");
        login.setPassword("alfa123");
        login.setRole("ROLE_SURVIVOR");

        entityManager.getTransaction().begin();
        logInDao.create(login);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        assertFalse("Error in creating login of user before deleting", login.getId() < 0);

        entityManager.getTransaction().begin();
        logInDao.delete(login);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        Assert.assertNull(logInDao.findLogIn(login.getId()));


    }

    /**
     * Test of finding of LogIn method from class LogInDAO.
     */
    @Test
    public void testFindLogIn() {
        LogInDAO logInDao = new LogInDAOImpl(entityManager);

        LogIn login = new LogIn();
        login.setName("User3");
        login.setPassword("pass1234");
        login.setRole("ROLE_SURVIVOR");

        entityManager.getTransaction().begin();
        logInDao.create(login);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        assertFalse("Error in creating login of user before looking up.", login.getId() < 0);

        LogIn logInUserFounded = logInDao.findLogIn(login.getId());
        Assert.assertEquals(logInUserFounded.getName(), login.getName());
        Assert.assertEquals(logInUserFounded.getPassword(), login.getPassword());
        Assert.assertTrue(logInUserFounded.getRole().equals(login.getRole()));
        Assert.assertEquals(logInUserFounded.getId(), login.getId());

        entityManager.getTransaction().begin();
        logInDao.delete(login);
        entityManager.getTransaction().commit();
       

    }

     /**
     * Test of finding all of LogIns method from class LogInDAO.
     */
    @Test
    public void testFindAll() {

        LogInDAO logInDao = new LogInDAOImpl(entityManager);

        LogIn login = new LogIn();
        login.setName("User1");
        login.setPassword("alfa123");
        login.setRole("ROLE_SURVIVOR");

        LogIn loginRoot = new LogIn();
        loginRoot.setName("Root1");
        loginRoot.setPassword("beta456");
        loginRoot.setRole("ROLE_ADMIN");

        entityManager.getTransaction().begin();
        logInDao.create(login);
        logInDao.create(loginRoot);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entManFact.createEntityManager();
        logInDao = new LogInDAOImpl(entityManager);

        assertFalse("Error in creating login of user", login.getId() < 0);
        assertFalse("Error in creating login of root", loginRoot.getId() < 0);

        List<LogIn> listOfLogins = logInDao.findAll();

        Assert.assertEquals(listOfLogins.size(), 2);

        Assert.assertTrue(listOfLogins.contains(login));
        Assert.assertTrue(listOfLogins.contains(loginRoot));

        entityManager.getTransaction().begin();
        logInDao.delete(login);
        logInDao.delete(loginRoot);
        entityManager.getTransaction().commit();


    }
}
