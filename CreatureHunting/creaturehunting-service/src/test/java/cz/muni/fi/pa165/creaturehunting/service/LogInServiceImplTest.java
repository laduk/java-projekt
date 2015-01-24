/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.data.dao.LogInDAO;
import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.LogInTransformation;
import cz.muni.fi.pa165.creaturehunting.service.serviceimpl.LogInServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Fita
 */
public class LogInServiceImplTest {
    
    @InjectMocks
    private LogInServiceImpl logInService;
    
    @Mock
    private LogInDAO logInDAO;
    
    private LogInDTO logIn1;
    private LogInDTO logIn2;
    
    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        
        logInService = new LogInServiceImpl(logInDAO);
        logIn1 = new LogInDTO();
        logIn2 = new LogInDTO();
        
        logIn1.setId(1);
        logIn1.setIsRoot(Boolean.TRUE);
        logIn1.setName("God");
        logIn1.setPassword("In_nomine:Patris&Filii&Spiritus_sancti");
        
        logIn2.setId(2);
        logIn2.setIsRoot(Boolean.FALSE);
        logIn2.setName("Survivor");
        logIn2.setName("Got1ofTWO");
    }
    
       
    /**
     * Test of throwing NullPointerException.
     */
    @Test
    public void testCreateNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            logInService.create(null); 
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        }        
    }
    
    /**
     * Test of throwing NullPointerException.
     */
    @Test
    public void testUpdateNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            logInService.update(null);
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        } 
    }
    
    /**
     * Test of throwing NullPointerException.
     */
    @Test
    public void testDeleteNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            logInService.delete(null);
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        } 
    }
    
    /**
     * Test whether is creation functional.
     */
    @Test
    public void testCreate(){
        System.out.println("--- Test Create ---");
        logInService.create(logIn1);
        verify(logInDAO).create(LogInTransformation.transformToEntity(logIn1));
    }
    
    /**
     * Test whether is updating functional.
     */
    @Test
    public  void testUpdate(){
        System.out.println("--- Test Update ---");
        when(logInDAO.findLogIn(logIn1.getId())).
                thenReturn(LogInTransformation.transformToEntity(logIn1));
        logInService.update(logIn1);
        
        verify(logInDAO).update(LogInTransformation.transformToEntity(logIn1));
    }
    
    /**
     * Test whether is deleting functional.
     */
    @Test
    public void testDelete(){
        System.out.println("--- Test Delete ---");
        logInService.delete(logIn1);
        
        verify(logInDAO).delete(LogInTransformation.transformToEntity(logIn1));
    }
    
    /**
     * Testing of finding by id.
     */
    @Test
    public void testFindById(){
        System.out.println("--- Test Find By Id ---");
        when(logInDAO.findLogIn(logIn1.getId()))
                .thenReturn(LogInTransformation.transformToEntity(logIn1));

        logInService.findLogIn(logIn1.getId());
        verify(logInDAO).findLogIn(logIn1.getId());
        Assert.assertEquals(logInService.findLogIn(logIn1.getId()),logIn1);
    }
    
    /**
     * Testing finding all Log Ins.
     */
    @Test
    public void findAllLogIns(){
        System.out.println("--- Test Find All LogIns---");
        List<LogIn> logIns = new ArrayList<LogIn>();
        logIns.add(LogInTransformation.transformToEntity(logIn1));
        logIns.add(LogInTransformation.transformToEntity(logIn2));
        when(logInDAO.findAll()).thenReturn(logIns);
                
        Assert.assertTrue("Wrong list of creatures was found.",
                logInService.findAllLogIns().get(0).equals(logIn1)
                && logIns.size()==2);
    }
    
    /**
     * Testing functionality of finding Log In by name.
     */
    @Test
    public void findLogInByName(){
        System.out.println("--- Test Find By Name LogIn---");
        List<LogIn> logIns = new ArrayList<LogIn>();
        
        logIns.add(LogInTransformation.transformToEntity(logIn1));
        logIns.add(LogInTransformation.transformToEntity(logIn2));
        when(logInDAO.findAll()).thenReturn(logIns);
        
        Assert.assertTrue("Wrong weapon by name was found.",
            logInService.findByName("God").equals(logIn1));
    }
}
