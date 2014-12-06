package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.data.entity.Area;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.CreatureTransformation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests methods from CreatureTransformation.
 *
 * @author Radoslav Zajonc
 */
public class CreatureTransformationTest {
    
    /**
     * Test creature transform from DTO to entity.
     */
    @Test
    public void testTransformToEntity() {
        CreatureDTO creatureDTO = new CreatureDTO();
        creatureDTO.setId(42);
        creatureDTO.setName("Batman");
        creatureDTO.setHeight(140);
        creatureDTO.setWeight(35);
        creatureDTO.setAgility(99);
        
        List<Long> idAreas = new ArrayList();
        idAreas.add(Long.parseLong("4242"));
        creatureDTO.setIdAreas(idAreas);
        
        Creature creature = CreatureTransformation.transformToEntity(creatureDTO);
        Assert.assertTrue("Wrong transform id of creature to entity.",
                creature.getId() == 42);
        Assert.assertTrue("Wrong transform name of creature to entity.",
                creature.getName().equals("Batman"));
        Assert.assertTrue("Wrong transform height of creature to entity.",
                creature.getHeight() == 140);
        Assert.assertTrue("Wrong transform weight of creature to entity.",
                creature.getWeight() == 35);
        Assert.assertTrue("Wrong transform agility of creature to entity.",
                creature.getAgility() == 99);
        Assert.assertTrue("Wrong transform listOfAreas of creature to entity.",
                creature.getListOfAreas().get(0).getId() == 4242);
    }
    
    /**
     * Test creature transform from entity to DTO.
     */
    @Test
    public void testTransformToDTO() {
        Creature creature = new Creature();
        creature.setId(420);
        creature.setName("Spiderman");
        creature.setHeight(150);
        creature.setWeight(60);
        creature.setAgility(100);
        
        Area area = new Area();
        area.setId(51);
        List<Area> areas = new ArrayList();
        areas.add(area);
        creature.setListOfAreas(areas);
        
        CreatureDTO creatureDTO = CreatureTransformation.transformToDTO(creature);
        Assert.assertTrue("Wrong transform id of creature to DTO.",
                creatureDTO.getId() == 420);
        Assert.assertTrue("Wrong transform name of creature to DTO.",
                creatureDTO.getName().equals("Spiderman"));
        Assert.assertTrue("Wrong transform height of creature to DTO.",
                creatureDTO.getHeight() == 150);
        Assert.assertTrue("Wrong transform weight of creature to DTO.",
                creatureDTO.getWeight() == 60);
        Assert.assertTrue("Wrong transform agility of creature to DTO.",
                creatureDTO.getAgility() == 100);
        Assert.assertTrue("Wrong transform listOfAreas of creature to DTO.",
                creatureDTO.getListOfAreas().get(0).getId() == 51);
        Assert.assertTrue("Wrong transform idAreas of creature to DTO.",
                creatureDTO.getIdAreas().get(0) == 51);
    }
    
}
