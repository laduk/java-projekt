/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureTransformation;
import cz.muni.fi.pa165.creaturehunting.service.huntingexperience.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.service.huntingexperience.HuntingExperienceServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.huntingexperience.HuntingExperienceTransformation;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponTransformation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Fita
 */
public class HuntingExperienceServiceImplTest {
    
    @InjectMocks
    private HuntingExperienceServiceImpl huntExpSerImpl;
    
    @Mock
    private HuntingExperienceDAO huntingExperienceDAO;
    
    private HuntingExperienceDTO huntExperienceDTO;
    private CreatureDTO creatureDTO;
    private WeaponDTO weaponDTO;
    
    @Before
    public void initMock(){
        MockitoAnnotations.initMocks(this);
        
        huntExperienceDTO = new HuntingExperienceDTO();
        creatureDTO =  new CreatureDTO();
        weaponDTO = new WeaponDTO();
        
        List<Creature> listC = new ArrayList();
        Creature creature = new Creature();
        creature.setId(1);
        creature.setName("Witch");
        creature.setAgility(25);
        creature.setHeight(180);
        creature.setWeight(75);
        creature.setListOfAreas(null);
        listC.add(creature);
        
        Area area = new Area();
        area.setId(1);
        area.setName("Brasov");
        area.setDescription("Romania");
        area.setAcreage(100);
        area.setListOfCreatures(listC);
        
        List<Area> listA = new ArrayList();
        listA.add(area);
        Creature creature2 = new Creature();
        creature2.setId(2);
        creature2.setName("Dracula");
        creature2.setAgility(25);
        creature2.setHeight(180);
        creature2.setWeight(80);
        creature2.setListOfAreas(listA);
        creatureDTO = CreatureTransformation.transformToDTO(creature2);
        
        Weapon weapon = new Weapon();
        weapon.setId(1);
        weapon.setName("Rifle");
        weapon.setAmmunition("0.30 cal");
        weapon.setGunReach(100);
        weaponDTO = WeaponTransformation.transformToDTO(weapon);
        
        huntExperienceDTO.setId(1);
        huntExperienceDTO.setWeapon(weaponDTO);
        huntExperienceDTO.setCreature(creatureDTO);
        huntExperienceDTO.setEfficiency(100);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(HuntingExperienceServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        huntExperienceDTO.setDateOfExperience(date);
        huntExperienceDTO.setDescription("Headshot!");  
    }
    
    @Test
    public void testFindByIdExcept(){
        System.out.println("--- Testing finding by id Exception ---"); 
        try {
            doThrow(DAOException.class).when(huntingExperienceDAO).
                    findHuntingExperience(huntExperienceDTO.getId());
        } catch (DAOException da) {
            fail("Wrong exception, covering in not functional.");
        } catch(DataAccessException ex){
            System.out.println("Awaiting exception: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testFindAll(){
        System.out.println("--- Testing findingAll Exception ---"); 
        try {
            doThrow(DAOException.class).when(huntingExperienceDAO).
                    findAllHuntingExperience();
        } catch (DAOException da) {
            fail("Wrong exception, covering in not functional.");
        } catch(DataAccessException ex){
            System.out.println("Awaiting exception: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testCreateExcept(){
        System.out.println("--- Testing creating Exception ---"); 
        try {
            doThrow(DAOException.class).when(huntingExperienceDAO).
                    createHuntingExperience(HuntingExperienceTransformation.
                            transformToEntity(huntExperienceDTO));
        } catch (DAOException da) {
            fail("Wrong exception, covering in not functional.");
        } catch(DataAccessException ex){
            System.out.println("Awaiting exception: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testUpdateExcept(){
        System.out.println("--- Testing updating Exception ---"); 
        try {
            doThrow(DAOException.class).when(huntingExperienceDAO).
                    updateHuntingExperience(HuntingExperienceTransformation.
                            transformToEntity(huntExperienceDTO));
        } catch (DAOException da) {
            fail("Wrong exception, covering in not functional.");
        } catch(DataAccessException ex){
            System.out.println("Awaiting exception: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testDeleteExcept(){
        System.out.println("--- Testing deleting Exception ---"); 
        try {
            doThrow(DAOException.class).when(huntingExperienceDAO).
                    deleteHuntingExperience(HuntingExperienceTransformation.
                            transformToEntity(huntExperienceDTO));
        } catch (DAOException da) {
            fail("Wrong exception, covering in not functional.");
        } catch(DataAccessException ex){
            System.out.println("Awaiting exception: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testFindByIdNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            huntExpSerImpl.findHuntExp(null);       
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testCreateNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            huntExpSerImpl.create(null);       
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        }        
    }
    
    @Test
    public void testUpdateNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            huntExpSerImpl.update(null);       
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        } 
    }
    
    @Test
    public void testDeleteNull(){
        System.out.println("--- Testing Null parametr Exception ---");
        try {
            huntExpSerImpl.delete(null);       
            fail("Nothing throwned!");
        } catch(NullPointerException ex){
            System.out.println("Awaiting exception Null: "+ex.getMessage());
        } 
    }
    
    
    
    
}
