/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureService;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureTransformation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

/**
 * This class tests methods from CreatureServiceImpl.
 *
 * @author Radoslav Zajonc
 */
public class CreatureServiceImplTest {
    
    private CreatureDTO creatureDTO;
    private CreatureDAO creatureDAO;
    private CreatureService creatureService;
    
    @Before
    public void beforClass() {
        creatureDTO = new CreatureDTO();
        creatureDTO.setId(42);
        creatureDTO.setName("Balrog");
        creatureDTO.setHeight(300);
        creatureDTO.setWeight(2500);
        creatureDTO.setAgility(5);
        
        List<AreaDTO> areasDTO = new ArrayList();
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(101);
        areasDTO.add(areaDTO);
        creatureDTO.setListOfAreas(areasDTO);
        
        creatureDAO = mock(CreatureDAO.class);
        creatureService = new CreatureServiceImpl(creatureDAO);
    }
    
    /**
     * Test create creature.
     */
    @Test (expected = DataAccessException.class)
    public void testCreateCreature() {
        creatureService.create(creatureDTO);
        
        verify(creatureDAO).
                createCreature(CreatureTransformation.transformToEntity(creatureDTO));
        doThrow(DAOException.class).when(creatureDAO).
                createCreature(any(Creature.class));

        creatureService.create(creatureDTO);
    }
    
    /**
     * Test update creature.
     */
    @Test (expected = DataAccessException.class)
    public void testUpdateCreature() {
        creatureService.update(creatureDTO);
        
        verify(creatureDAO).
                updateCreature(CreatureTransformation.transformToEntity(creatureDTO));
        doThrow(DAOException.class).when(creatureDAO).
                updateCreature(any(Creature.class));

        creatureService.update(creatureDTO);
    }
    
    /**
     * Test delete creature.
     */
    @Test (expected = DataAccessException.class)
    public void testDeleteCreature() {
        creatureService.delete(creatureDTO);
        
        verify(creatureDAO).
                deleteCreature(CreatureTransformation.transformToEntity(creatureDTO));
        doThrow(DAOException.class).when(creatureDAO).
                deleteCreature(any(Creature.class));

        creatureService.delete(creatureDTO);
    }
    
    /**
     * Test find creature by id.
     */
    @Test (expected = DataAccessException.class)
    public void testFindCreature() {
        when(creatureDAO.findCreature(any(Long.class))).
                thenReturn(CreatureTransformation.transformToEntity(creatureDTO));
        
        Assert.assertTrue("Wrong creature was found.", 
                creatureService.findCreature(42).equals(creatureDTO));
        
        doThrow(DAOException.class).when(creatureDAO).
                findCreature(anyInt());

        creatureService.findCreature(42);
    }
    
    /**
     * Test find all creatures by id.
     */
    @Test (expected = DataAccessException.class)
    public void testFindAllCreatures() {
        List<Creature> creatures = new ArrayList();
        creatures.add(CreatureTransformation.transformToEntity(creatureDTO));
        when(creatureDAO.findAllCreatures()).thenReturn(creatures);
        
        Assert.assertTrue("Wrong list of creatures was found.", 
                creatureService.findAllCreatures().get(0).equals(creatureDTO));

        doThrow(DAOException.class).when(creatureDAO).findAllCreatures();

        creatureService.findAllCreatures();
    }
    
    
    /**
     * Test find all creatures by name.
     */
    @Test (expected = DataAccessException.class)
    public void testFindAllCreaturesByName() {
        List<Creature> creatures = new ArrayList();
        CreatureDTO fooCreatureDTO = new CreatureDTO();
        fooCreatureDTO.setName("foo");
        creatures.add(CreatureTransformation.transformToEntity(fooCreatureDTO));
        creatures.add(CreatureTransformation.transformToEntity(creatureDTO));
        when(creatureDAO.findAllCreatures()).thenReturn(creatures);
        
        Assert.assertTrue("Wrong creature by name was found.", 
                creatureService.findAllCreaturesByName("Balrog").get(0).
                equals(creatureDTO));
        Assert.assertTrue("Wrong list of creatures by name was found.",
                creatureService.findAllCreaturesByName("Balrog").size() == 1);

        doThrow(DAOException.class).when(creatureDAO).findAllCreatures();

        creatureService.findAllCreaturesByName("Balrog");
    }
}
